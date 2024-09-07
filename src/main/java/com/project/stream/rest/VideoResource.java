package com.project.stream.rest;

import com.project.stream.config.MinioManager;
import com.project.stream.rest.vm.request.VideoMetaDataVM;
import com.project.stream.rest.vm.request.VideoRequestVM;
import com.project.stream.service.VideoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/videos")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class VideoResource {

    VideoService videoService;
    MinioManager minioManager;


    @GetMapping("/test")
    public Object test() throws Exception {
        System.out.println("tamera");
         return null;
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@ModelAttribute VideoMetaDataVM videoMetaData, @RequestParam("file") MultipartFile file)  {
        try {
            videoService.uploadVideo(videoMetaData, file);
            return ResponseEntity.ok().body("success");
        }catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @PostMapping(value="/url")
    public String getFileUrl(@RequestBody VideoRequestVM videoRequest)  {
        try {
            String url = videoService.getVideoUrl(videoRequest);
            return url;
        }catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
