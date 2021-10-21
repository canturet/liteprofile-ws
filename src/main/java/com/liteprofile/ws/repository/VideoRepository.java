package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.Text;
import com.liteprofile.ws.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByUserId(Long id);

}
