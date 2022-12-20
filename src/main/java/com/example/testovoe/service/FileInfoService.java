package com.example.testovoe.service;

import com.example.testovoe.model.entity.FileInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileInfoService {

    void saveFile(MultipartFile file) throws IOException;
    FileInfoEntity getFileByName(String name);
}
