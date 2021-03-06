package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.CustomLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomLinkRepository extends JpaRepository<CustomLink, Long> {

    List<CustomLink> findByUserId(Long id);

}
