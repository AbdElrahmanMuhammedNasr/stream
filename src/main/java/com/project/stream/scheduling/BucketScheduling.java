package com.project.stream.scheduling;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class BucketScheduling {

    @Qualifier("customMinioClient")
    MinioClient minioClient;

    @Scheduled(fixedRate = 50000)
    public void getAllBucketAndUpdateCache() throws Exception {
        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.creationDate() + ", " + bucket.name());
        }

    }
}
