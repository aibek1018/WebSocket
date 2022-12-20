package com.example.testovoe.rest;


import com.example.testovoe.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/files")
public class FileInfoController {

    private final FileInfoService fileInfoService;

    @PostMapping
    void saveFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileInfoService.saveFile(file);
    }

}
