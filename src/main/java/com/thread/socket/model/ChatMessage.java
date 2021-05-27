package com.thread.socket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ChatMessage {

    //only getters are needed 
    @Getter 
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time; //(moment js is a string timeStamp)

}
