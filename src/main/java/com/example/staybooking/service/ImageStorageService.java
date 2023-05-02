package com.example.staybooking.service;

import com.example.staybooking.exception.GCSUploadException;
import org.springframework.stereotype.Service;
import com.google.cloud.storage.*;
import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService {
    @Value("${gcs.bucket}")
    private String bucketName;
    private final Storage storage;

    public ImageStorageService(Storage storage) {
        this.storage = storage;
    }

    public String save(MultipartFile file) throws GCSUploadException {
        String filename = UUID.randomUUID().toString();
        BlobInfo blobInfo = null;
        try {
            blobInfo = storage.createFrom(
                    BlobInfo
                            .newBuilder(bucketName, filename)
                            .setContentType("image/jpeg")
                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(),
                    file.getInputStream());
        } catch (IOException exception) {
            throw new GCSUploadException("Failed to upload file to GCS!");
        }
        return blobInfo.getMediaLink();
    }
}