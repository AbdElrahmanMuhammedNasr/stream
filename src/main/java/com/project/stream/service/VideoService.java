package com.project.stream.service;

import com.project.stream.rest.vm.request.VideoMetaDataVM;
import com.project.stream.rest.vm.request.VideoRequestVM;
import org.springframework.web.multipart.MultipartFile;


public interface VideoService {

    void uploadVideo(VideoMetaDataVM videoMetaData, MultipartFile file) throws Exception;
    String getVideoUrl(VideoRequestVM videoRequest) throws Exception;
}
