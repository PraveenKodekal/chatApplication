package com.webSocket.chatApp.pChat.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.webSocket.chatApp.pChat.model.ChatMessage;
import com.webSocket.chatApp.pChat.model.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Admin- Praveen
 * This configuration is to listen the session which is disconnect event
 *  it means any user who is left the session it indicates the other users about the perticular disconnect user
 *  Eg: if X is left the chat from the application the other users can get notify that X is left the chat
 *  
 *
 */

@Component
@RequiredArgsConstructor
// log the message when the participant leave the chat
@Slf4j
public class WebSocketEventListener {
	
	private final SimpMessageSendingOperations messageTemplate;
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		
		StompHeaderAccessor accessor=StompHeaderAccessor.wrap(event.getMessage());
		String username=(String) accessor.getSessionAttributes().get("username");
		
		if(username!=null) {
			log.info("User Disconnected : {}",username);
			var message=ChatMessage.builder()
									.type(MessageType.LEAVE)
									.sender(username)
									.build();
			messageTemplate.convertAndSend("/topic/public",message);
			
		}
		
		
		
		
	}

	
}
