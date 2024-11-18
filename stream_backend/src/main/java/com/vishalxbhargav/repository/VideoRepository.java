package com.vishalxbhargav.repository;

import com.vishalxbhargav.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, String> {
    // You can add custom queries here if needed

    Optional<Object> findByTitle(String title);
}
