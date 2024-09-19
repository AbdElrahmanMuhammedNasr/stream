package com.project.stream.service;

import com.project.stream.model.Video;
import com.project.stream.rest.vm.request.VideoMetaDataVM;
import com.project.stream.rest.vm.request.VideoRequestVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface VideoService {

    void uploadVideo(VideoMetaDataVM videoMetaData, MultipartFile file) throws Exception;
    String getVideoUrl(VideoRequestVM videoRequest) throws Exception;

    List<Video> getAllVideos();
}
