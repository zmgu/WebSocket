package com.ex.backend.controller;

import com.ex.backend.dto.SendMessage;
import com.ex.backend.dto.ReceivedMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/send")               // 발행 경로 ( 프론트에서 destination : enableSimpleBroker + 엔드포인트 )
    @SendTo("/stomp/sub/received")         // 구독 경로 ( setApplicationDestinationPrefixes + 엔드포인트 )
    public SendMessage chat(ReceivedMessage receivedMessage) throws Exception {
        return new SendMessage(HtmlUtils.htmlEscape(receivedMessage.getMessage()));
    }
}