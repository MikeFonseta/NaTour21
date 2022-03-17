package com.example.NaTour21.Message.Controller;

import com.example.NaTour21.ChatRoom.Entity.ChatRoom;
import com.example.NaTour21.ChatRoom.Service.ChatRoomService;
import com.example.NaTour21.Message.Entity.Message;
import com.example.NaTour21.Message.Service.MessageService;
import com.example.NaTour21.Pusher.PusherManager;
import com.example.NaTour21.User.Service.UserService;
import com.example.NaTour21.Utils.RequestTemplate.MessageRequest;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Utils.ResponseTemplate.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Locale;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    @PostMapping("/message/send")
    public ResponseEntity<BasicResponse> sendMessage(@RequestBody MessageRequest messageRequest) {
        if(userService.getUser(messageRequest.getTo()) != null)
        {
            if(userService.getUser(messageRequest.getFrom()) != null
                    && messageRequest.getContent() != null
                    && !messageRequest.getFrom().equals(messageRequest.getTo())) {
                Long time = new Date().getTime();
                ChatRoom chatRoom = chatRoomService.saveChatRoom(messageRequest.getTo().toLowerCase(Locale.ROOT),
                        messageRequest.getFrom().toLowerCase(Locale.ROOT), messageRequest.getContent(), time);
                messageService.newMessage(new Message(null, chatRoom.getId(),
                        messageRequest.getFrom().toLowerCase(Locale.ROOT), messageRequest.getContent(), time));
                MessageResponse messageResponse = new MessageResponse(messageRequest.getFrom().toLowerCase(Locale.ROOT), messageRequest.getContent());
                PusherManager.SendMessage(messageRequest.getTo().toLowerCase(Locale.ROOT), messageResponse);
                return ResponseEntity.ok(new BasicResponse(messageResponse, "OK"));
            }
            return ResponseEntity.ok(new BasicResponse("Impossibile inviare il messagio", "FAILED"));
        }
        return ResponseEntity.ok(new BasicResponse("Impossibile trovare " + messageRequest.getTo(), "FAILED"));
    }

    @PostMapping("/message/chatroom")
    public ResponseEntity<BasicResponse> getCharRooms(@Param("username1") String username1, @Param("username2") String username2) {
        BasicResponse response = new BasicResponse(messageService.getMessageByChatRoom(username1.toLowerCase(Locale.ROOT),username2.toLowerCase(Locale.ROOT)), "OK");
        return ResponseEntity.ok().body(response);
    }

}
