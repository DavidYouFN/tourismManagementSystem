package com.felix.project.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadImg(MultipartFile file, String id);
}
