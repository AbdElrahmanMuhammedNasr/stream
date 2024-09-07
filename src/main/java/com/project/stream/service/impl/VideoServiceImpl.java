package com.project.stream.service.impl;

import com.project.stream.config.MinioManager;
import com.project.stream.model.Video;
import com.project.stream.repository.VideoRepository;
import com.project.stream.rest.vm.request.VideoMetaDataVM;
import com.project.stream.rest.vm.request.VideoRequestVM;
import com.project.stream.service.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoServiceImpl implements VideoService {

    MinioManager minioManager;
    VideoRepository videoRepository;
    @Override
    public void uploadVideo(VideoMetaDataVM videoMetaData, MultipartFile file) throws Exception {
        minioManager.createUserBucketIfNotExists(videoMetaData.userId());
        String fileUUID = minioManager.uploadFile(videoMetaData.userId(), file);
        Video video = Video.builder()
                .userId(videoMetaData.userId())
                .videoId(fileUUID)
                .thumbnailId("testm")
                .title(videoMetaData.videoTitle())
                .description(videoMetaData.videoDescription())
                .build();
//        videoRepository.save(video);
    }

    @Override
    public String getVideoUrl(VideoRequestVM videoRequest) throws Exception {
        return minioManager.getFileUrl(videoRequest.userId() , videoRequest.videoId());
    }
}
