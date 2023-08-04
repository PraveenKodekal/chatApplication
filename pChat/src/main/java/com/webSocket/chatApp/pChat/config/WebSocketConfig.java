package com.webSocket.chatApp.pChat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// we need to new stompEnd Point
		
		registry.addEndpoint("/ws").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// configure the message broker
		// enable application Desitation Prefixes
		// Enable simple broker it takes dest prefixes
		
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");

	
	}

	
	
	
}
