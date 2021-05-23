package com.thread.socket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ChatMessage {

    //only getters are needed 
    @Getter 
    @Setter
    private MessageType type;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String sender;

    @Getter
    @Setter
    private String time; //(moment js is a string timeStamp)

}
