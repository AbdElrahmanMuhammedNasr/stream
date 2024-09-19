package com.project.stream.repository;

import com.project.stream.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> {
    @Query(value = "SELECT pg_sleep(5), * FROM user_videos" , nativeQuery = true)
    List<Video> findAllSlow();

}
