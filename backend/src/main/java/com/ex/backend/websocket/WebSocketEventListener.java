package com.ex.backend.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class WebSocketEventListener {

    /**
     *  웹소켓 연결 후 동작하는 메서드
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        log.info("WebSocketEventListener - handleWebSocketConnectListener 동작");

    }

    /**
     *  웹소켓 연결이 종료된 후 동작하는 메서드
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

        log.info("WebSocketEventListener - handleWebSocketDisconnectListener 동작");

    }
}
