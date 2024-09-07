package com.project.stream.config;

import com.project.stream.rest.vm.response.FileMetaData;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.*;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class MinioManager implements FileManagerService{

    @Qualifier("customMinioClient")
    MinioClient minioClient;

  @Override
  public void createUserBucketIfNotExists(String userId) throws Exception {
        String bucketName = NamesConfig.USER+userId;
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        boolean exists = minioClient.bucketExists(bucketExistsArgs);
        if(!exists){
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
            minioClient.makeBucket(makeBucketArgs);
        }
    }

    @Override
    public String uploadFile(String userId,MultipartFile file) throws Exception {
        InputStream fileInputStream = file.getInputStream();
        String fileUUID = UUID.randomUUID().toString();
        String bucketName = NamesConfig.USER+userId;

        PutObjectArgs uploadFile = PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object( NamesConfig.VIDEO+fileUUID +"/"+file.getOriginalFilename())
                .stream(fileInputStream, file.getSize(), 5 * 1024 * 1024).build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(uploadFile);
        return fileUUID;
    }

    @Override
    public String getFileUrl(String userId,String fileId) throws Exception {
        String bucketName = NamesConfig.USER+userId;
        String videoName = NamesConfig.VIDEO+fileId;
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET) // HTTP method for the URL
                        .bucket(bucketName) // Name of the bucket
                        .object(videoName) // Name of the object
                        .expiry(1000) // Expiry time for the URL
                        .build());
    }

    @Override
    public List<FileMetaData> getUserFiles(String userId) throws Exception {
        String bucketName = NamesConfig.USER+userId;
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        Iterator<Result<Item>> iterator = results.iterator();
        List<FileMetaData> files = new LinkedList<>();
        while (iterator.hasNext()){
            Result<Item> next = iterator.next();
            FileMetaData fileMetaData = FileMetaData
                    .builder()
                    .objectName(next.get().objectName())
                    .size(next.get().size())
                    .storageClass(next.get().storageClass())
                    .isLatest(next.get().isLatest())
                    .versionId(next.get().versionId())
                    .userMetadata(next.get().userMetadata())
                    .isDir(next.get().isDir())
                    .owner(next.get().owner())
                    .build();
            files.add(fileMetaData);
        }
        return files;
    }

    @Override
    public void deleteUserFiles(String userId,List<String> objectIds) throws Exception {
        String bucketName = NamesConfig.USER+userId;

        List<DeleteObject> deleteObjects = objectIds.stream()
                .map(objectId -> new DeleteObject(NamesConfig.VIDEO+objectId)).toList();
//
        Iterable<Result<Item>> objects = minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix("video-2314a746-5bfe-4951-b289-3aa1561177fd/")
                        .recursive(true)
                        .build());


        for (Result<Item> result : objects) {
            Item item = result.get();
            String objectName = item.objectName();
            // Remove the object
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        }

    }

}
