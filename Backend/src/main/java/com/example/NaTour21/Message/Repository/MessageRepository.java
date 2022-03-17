package com.example.NaTour21.Message.Repository;

import com.example.NaTour21.Message.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM message WHERE chat_id =CONCAT('_',CONCAT(?1,'_',?2),'_') OR chat_id = CONCAT('_',CONCAT(?2,'_',?1),'_') ORDER BY time DESC", nativeQuery = true)
    List<Message> getChatRoomBy(String email1, String email2);
}
