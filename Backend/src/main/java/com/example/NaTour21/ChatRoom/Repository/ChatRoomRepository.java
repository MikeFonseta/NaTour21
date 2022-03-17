package com.example.NaTour21.ChatRoom.Repository;

import com.example.NaTour21.ChatRoom.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    @Query(value = "SELECT REPLACE(REPLACE(C.id,CONCAT('_',?1,'_'), ''),'_','') AS username, last_message,time FROM chatroom AS C WHERE C.id LIKE CONCAT('%',CONCAT('_',?1,'_'),'%') ORDER BY time DESC;", nativeQuery = true)
    List<?> findByUsername(String username);

    @Query(value = "SELECT * FROM chatroom WHERE id=CONCAT('_',CONCAT(?1,'_',?2),'_') OR id=CONCAT('_',CONCAT(?2,'_',?1),'_')", nativeQuery = true)
    ChatRoom findById(String username1,String username2);
}
