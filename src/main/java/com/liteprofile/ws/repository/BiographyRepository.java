package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.Biography;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiographyRepository extends JpaRepository<Biography, Long> {

    Biography findByUserId(Long id);

}
