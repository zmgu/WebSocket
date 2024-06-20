package com.ex.backend.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

// 웹소켓 연결에서 메시지 전송, 수신 전에 호출되는 인터셉터
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        // 메시지와 STOMP 헤더 정보에 접근
        StompHeaderAccessor headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        // 연결된 경우에만 수행
        if (headerAccessor.getCommand() == StompCommand.CONNECT) {

            // 토큰 검증과 같은 로직 처리
            String token = String.valueOf(headerAccessor.getNativeHeader("Authorization").get(0));
            System.out.println("token = " + token);
        }
        return message;

    }
}

