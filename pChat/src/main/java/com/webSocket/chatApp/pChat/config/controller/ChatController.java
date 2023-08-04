package com.webSocket.chatApp.pChat.config.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.webSocket.chatApp.pChat.model.ChatMessage;

@Controller
public class ChatController {

	// method
	// method to add user
	// if it is a http request for rest api calls we use request body
	// since it is a web socket we use payLoad

	
	// @Message Mapping -- Message Mapping tells what is the url to invoke the method
	// @SendTo-- where to ehen to send the message(to which topic to which user

	
	@MessageMapping("chat.sendMessage")
	@SendTo("/tpopic/public")
	public ChatMessage sendMsg(@Payload ChatMessage message) {
		
		
		return message;
		
	}
	
	@MessageMapping("chat.addUser")
	@SendTo("/tpopic/public")
	public ChatMessage user(@Payload ChatMessage message, SimpMessageHeaderAccessor header) {
		// add username web socket session
		header.getSessionAttributes().put("username", message.getSender());
		
		
		return message;
	}
	
	
	// method to send message
	
	
}
