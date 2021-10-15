package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.SocialLink;
import com.liteprofile.ws.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findByUserId(Long id);
}
