package com.vishalxbhargav.service;

import com.vishalxbhargav.model.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    //save video
    Video save(Video video, MultipartFile file);
    //get video
    Video get(String videoId);
    //get video by title
    Video getByTitle(String title);
    List<Video> getAll();
}
