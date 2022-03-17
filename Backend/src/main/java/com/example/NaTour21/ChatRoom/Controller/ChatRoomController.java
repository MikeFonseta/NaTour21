package com.example.NaTour21.ChatRoom.Controller;

import com.example.NaTour21.ChatRoom.Service.ChatRoomService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/chatrooms")
    public ResponseEntity<BasicResponse> getCharRooms(@Param("username") String username) {
        BasicResponse response = new BasicResponse(chatRoomService.getUserChatRooms(username.toLowerCase(Locale.ROOT)), "OK");
        return ResponseEntity.ok().body(response);
    }

}
