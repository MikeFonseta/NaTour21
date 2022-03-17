package com.example.NaTour21.Message.Service;

import com.example.NaTour21.Message.Entity.Message;
import com.example.NaTour21.Message.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public Message newMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessageByChatRoom(String username1, String username2) {
        return  messageRepository.getChatRoomBy(username1,username2);
    }
}