package com.vishalxbhargav.service.impl;

import com.vishalxbhargav.model.Video;
import com.vishalxbhargav.repository.VideoRepository;
import com.vishalxbhargav.service.VideoService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Value("${files.video}")
    private String DIR;  // Path to store the video files
    @Autowired
    private  VideoRepository videoRepository;

    @PostConstruct
    public void init() {
        // Debugging line to ensure the property is loaded
        System.out.println("Configured video directory: " + DIR);

        File file = new File(DIR);
        if (!file.exists()) {
            file.mkdir();  // Create folder if it doesn't exist
            System.out.println("Folder Created at: " + DIR);
        } else {
            System.out.println("Folder already exists at: " + DIR);
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("No file uploaded.");
            }

            // Validate file type (video only)
            String contentType = file.getContentType();
            if (!contentType.startsWith("video/")) {
                throw new IllegalArgumentException("Invalid file type. Only video files are allowed.");
            }

            // Handle the file
            String filename = file.getOriginalFilename();
            String cleanFilename = StringUtils.cleanPath(filename);
            Path path = Paths.get(DIR, cleanFilename);

            if (Files.exists(path)) {
                throw new IllegalArgumentException("File with the same name already exists.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }

            video.setContentType(contentType);
            video.setFilePath(path.toString());
            return videoRepository.save(video);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while saving the video file.", e);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid file: " + e.getMessage(), e);
        }
    }

    @Override
    public Video get(String videoId) {
        return videoRepository.findById(videoId).orElse(null);
    }

    @Override
    public Video getByTitle(String title) {
        return (Video) videoRepository.findByTitle(title).orElse(null);
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}
