package com.ex.backend.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // WebSocket 메시지 브로커를 활성화하는 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {  // WebSocket 메시지 브로커를 구성하기 위한 인터페이스 구현

    /**
     * 메시지 브로커를 구성하는 메서드
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/stomp/sub");  // 구독 엔드포인트를 활성화
        config.setApplicationDestinationPrefixes("/stomp/pub");  // 발행 엔드포인트를 설정
    }

    /**
     * STOMP 엔드포인트를 등록하는 메서드
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/stomp/ws")  // 웹소켓의 엔드포인트 설정
                .setAllowedOriginPatterns("*")  // CORS 설정으로 모든 출처 허용
                .addInterceptors(new WebSocketHandshakeInterceptor())  // 웹소켓 Handshake 발생 전과 후에 작업할 수 있는 인터셉터 추가
                .withSockJS();  // SockJS 지원을 추가
    }

    /**
     * ChannelInterceptor 등록하는 메서드
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new WebSocketChannelInterceptor());
    }
}

