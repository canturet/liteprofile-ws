package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.model.ERole;
import com.liteprofile.ws.model.Role;
import com.liteprofile.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomLinkRepository extends JpaRepository<CustomLink, Long> {

    List<CustomLink> findByUserId(Long id);

}
