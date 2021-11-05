package com.liteprofile.ws.repository;

import com.liteprofile.ws.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    Platform findByPlatformName(String platformName);

}
