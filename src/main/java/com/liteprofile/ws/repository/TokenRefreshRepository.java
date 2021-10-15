package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.RefreshToken;
import com.liteprofile.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface TokenRefreshRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

}
