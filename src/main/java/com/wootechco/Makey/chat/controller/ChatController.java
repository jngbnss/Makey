package com.wootechco.Makey.chat.controller;

import com.wootechco.Makey.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/send/{roomId}") // /ap/send 로 들어온 메시지를 받음
    @SendTo("/topic/room/{roomId}") // /topic/messages 구독자에게 전송
    public ChatMessage sendMessage(ChatMessage message){
//        System.out.println("받은 메시지: "+message);
        System.out.println("[" + message.getSender() + "] " + message.getContent());
        return message; // 그대로 브로드캐스트
    }
}
