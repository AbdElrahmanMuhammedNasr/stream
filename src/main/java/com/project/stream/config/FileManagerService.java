package com.project.stream.config;

import com.project.stream.rest.vm.response.FileMetaData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileManagerService {
    void createUserBucketIfNotExists(String userId) throws Exception;
    String uploadFile(String userId, MultipartFile file) throws Exception;

    String getFileUrl(String userId,String fileId)throws Exception;
    List<FileMetaData> getUserFiles(String userId)throws Exception;
    void deleteUserFiles(String userId,List<String> objectIds)throws Exception;


}
