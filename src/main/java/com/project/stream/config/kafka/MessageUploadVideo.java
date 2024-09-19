package com.project.stream.config.kafka;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class MessageUploadVideo {
        String userId;
        String videoId;

}
