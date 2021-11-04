package com.liteprofile.ws.service.impl;

import com.google.zxing.WriterException;
import com.liteprofile.ws.model.*;
import com.liteprofile.ws.repository.*;
import com.liteprofile.ws.service.EmailService;
import com.liteprofile.ws.service.QRCodeGeneratorService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import com.liteprofile.ws.utils.payload.response.JwtResponse;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import com.liteprofile.ws.utils.payload.response.UserProfileResponse;
import com.liteprofile.ws.utils.security.jwt.JwtUtils;
import com.liteprofile.ws.utils.security.user.model.UserDetailsImpl;
import com.liteprofile.ws.service.RefreshTokenService;
import com.liteprofile.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    Message message;

    @Autowired
    CustomLinkRepository customLinkRepository;

    @Autowired
    SocialLinkRepository socialLinkRepository;

    @Autowired
    TextRepository textRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    BiographyRepository biographyRepository;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse(message.getUsernameIsAlreadyTaken()));
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse(message.getEmailIsAlreadyInUse()));
        }
        User user = new User(registerDto.getUsername(), registerDto.getEmail(), encoder.encode(registerDto.getPassword()));
        Set<String> strRoles = registerDto.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(message.getRoleNotFound()));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException(message.getRoleNotFound()));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(message.getRoleNotFound()));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(message.getMailSubject());
        mailMessage.setFrom(message.getMailAddress());
        mailMessage.setText(message.getMailText()
                + "http://localhost:8081/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok(new MessageResponse(message.getUserRegisteredSuccessfully()));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    public ResponseEntity<?> getUserProfileByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        List<Biography> biographies = biographyRepository.findByUserId(user.get().getId());
        List<CustomLink> customLinks = customLinkRepository.findByUserId(user.get().getId());
        List<SocialLink> socialLinks = socialLinkRepository.findByUserId(user.get().getId());
        List<Text> texts = textRepository.findByUserId(user.get().getId());
        List<Video> videos = videoRepository.findByUserId(user.get().getId());
        return ResponseEntity.ok(new UserProfileResponse(biographies, customLinks, socialLinks, texts, videos));
    }

    public ResponseEntity<?> confirmAccount(String confirmationToken) throws IOException, WriterException {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            qrCodeGeneratorService.generateQRCode(user.getUsername());
            return ResponseEntity.ok(message.getAccountConfirmed());
        } else {
            return null;
        }
    }

}


