package com.wootechco.Makey.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender; // 닉네임
    private String content; // 메시지 내용

}
