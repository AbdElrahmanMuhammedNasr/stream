package com.project.stream.rest.vm.response;

import io.minio.messages.Owner;
import io.minio.messages.ResponseDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Path;

import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE )
public class FileMetaData {


     String etag;
     String objectName;
     ResponseDate lastModified;
     Owner owner;
     long size;
     String storageClass;
     boolean isLatest;
     String versionId;
     Map<String, String> userMetadata;
     boolean isDir = false;
     String encodingType = null;

}
