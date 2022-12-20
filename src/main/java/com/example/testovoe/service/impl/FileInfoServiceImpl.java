package com.example.testovoe.service.impl;

import com.example.testovoe.model.entity.FileInfoEntity;
import com.example.testovoe.repository.FileInfoRepository;
import com.example.testovoe.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        FileInfoEntity entity = new FileInfoEntity();
        final var fileName = file.getOriginalFilename();
        entity.setDate(getDate(fileName));
        entity.setPath(saveFileToPath(file));
        entity.setName(file.getOriginalFilename());
        fileInfoRepository.save(entity);
    }

    @Override
    public FileInfoEntity getFileByName(String name) {
        return fileInfoRepository.findByName(name);
    }

    private String saveFileToPath(MultipartFile file){
        final var path = new File("C:\\Users\\ayazb_000.aa\\Documents\\" + file.getOriginalFilename());
        try {
            var output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path.getPath();
    }

    private String getFileName(String name) {
        return name.substring(0, name.indexOf("."));
    }

    @SneakyThrows
    private LocalDateTime getDate(String name){
        final var pattern = "ddMMyyyy_HHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = simpleDateFormat.parse(getFileName(name));
        log.info("data : {}", date);
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
