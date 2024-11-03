package com.ex.backend.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * 웹소켓 연결에서 메시지 전송, 수신 전에 호출되는 인터셉터
 */
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    int cnt = 0;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {


        // 메시지와 STOMP 헤더 정보에 접근
        StompHeaderAccessor headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        switch (headerAccessor.getCommand()) {
            case CONNECT -> {

                // 웹소켓 헤더에서 데이터를 가져오는 예시 ex) Jwt 등
                String token = String.valueOf(headerAccessor.getNativeHeader("Authorization").get(0));

                log.info("WebSocketChannelInterceptor CONNECT 동작");
            }
            case SUBSCRIBE -> {
                log.info("WebSocketChannelInterceptor SUBSCRIBE 동작");
            }
            case DISCONNECT -> {
                cnt += 1;
                log.info("WebSocketChannelInterceptor DISCONNECT 동작 {}번", cnt);
            }
        }

        return message;

    }
}

