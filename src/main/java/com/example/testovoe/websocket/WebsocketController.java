package com.example.testovoe.websocket;

import com.example.testovoe.model.dto.UserRequest;
import com.example.testovoe.model.entity.FileInfoEntity;
import com.example.testovoe.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebsocketController {

    private final FileInfoService fileInfoService;

    @MessageMapping("/user")
    @SendTo("/topic/user")
    public FileInfoEntity getUser(UserRequest userRequest) {
        return fileInfoService.getFileByName(userRequest.getFileName());
    }
}
