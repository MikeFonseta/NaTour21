package com.example.NaTour21.ChatRoom.Service;

import com.example.NaTour21.ChatRoom.Entity.ChatRoom;
import com.example.NaTour21.ChatRoom.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<?> getUserChatRooms(String username)
    {
        return chatRoomRepository.findByUsername(username);
    }

    public ChatRoom saveChatRoom(String to, String from, String content, Long time) {

        ChatRoom chatRoom = chatRoomRepository.findById(to,from);

        if(chatRoom == null)
        {
            return chatRoomRepository.save(new ChatRoom("_"+from+"_"+to+"_",content,time));
        }
        else
        {
            chatRoom.setLastMessage(content);
            chatRoom.setTime(time);
            return chatRoomRepository.save(chatRoom);
        }
    }
}