package com.thread.socket.controller;

import com.thread.socket.model.ChatMessage;
import com.thread.socket.model.MessageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handWebSocketConnectListener(final SessionConnectedEvent event){
        LOGGER.info("obs.: new connection");
    }

    @EventListener
    public void handWebSocketDisconnectListener(final SessionDisconnectEvent event){
        try {
            final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
            final String username = (String) headerAccessor.getSessionAttributes().get("username");            
            final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();
                sendingOperations.convertAndSend("/topic/public", chatMessage);
        } catch (Exception e) {
            e.getCause();
            e.getStackTrace();
        }
    }
}
