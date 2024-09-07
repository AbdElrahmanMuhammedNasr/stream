package com.project.stream.config;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MinioConfig {
    String service;
    String endpoint;
    String accessKey;
    String secretKey;
}