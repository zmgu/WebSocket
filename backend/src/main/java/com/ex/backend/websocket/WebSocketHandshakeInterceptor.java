package com.ex.backend.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 웹소켓 Handshake 발생 전과 후에 작업할 수 있는 클래스
 */
@Slf4j
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    /**
     *  WebSocket 연결이 시작되기 전(핸드셰이크 전)에 호출
     *  이 메서드에서 요청의 헤더를 검사하여, 유효한 인증 토큰이 포함되었는지 확인할 수 있다.
     *  true 반환 시 연결을 허용, false 반환하면 연결을 차단
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("WebSocketHandshakeInterceptor - beforeHandshake 동작");
        return true;
    }

    /**
     * 핸드셰이크가 완료된 후 호출
     * 이 메서드는 핸드셰이크 후 후속 작업이 필요할 경우 사용할 수 있다.
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("WebSocketHandshakeInterceptor - afterHandshake 동작");
    }
}
