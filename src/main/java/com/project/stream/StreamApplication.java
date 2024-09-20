package com.project.stream;

import com.project.stream.config.MinioConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(value = {MinioConfig.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableScheduling
@EnableAsync
@EnableDiscoveryClient
public class StreamApplication {

	public static void main(String[] args)  {
		SpringApplication.run(StreamApplication.class, args);
	}

}
