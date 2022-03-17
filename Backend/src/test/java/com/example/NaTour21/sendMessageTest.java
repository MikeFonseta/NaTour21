package com.example.NaTour21;

import com.example.NaTour21.Message.Controller.MessageController;
import com.example.NaTour21.Utils.RequestTemplate.MessageRequest;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Utils.ResponseTemplate.MessageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class sendMessageTest {

    @Autowired
    private MessageController messageController;

    String validUsername1 = "mike.fonseta";
    String validUsername2 = "mike13";
    String invalidUsername = "invalidUsername";
    String message = "Ciao!";

    @Test
    public void test_1_10()
    {
        assertEquals(messageController.sendMessage(new MessageRequest(validUsername1,invalidUsername,message)),
                ResponseEntity.ok(new BasicResponse("Impossibile trovare invalidUsername", "FAILED")));
    }

    @Test
    public void test_1_2_9()
    {
        assertEquals(messageController.sendMessage(new MessageRequest(invalidUsername,validUsername1,message)),
                ResponseEntity.ok(new BasicResponse("Impossibile inviare il messagio", "FAILED")));
    }

    @Test
    public void test_1_2_3_4_5_6_7_8()
    {
        assertEquals(messageController.sendMessage(new MessageRequest(validUsername1,validUsername2,message)),
                ResponseEntity.ok(new BasicResponse(new MessageResponse("mike.fonseta", message), "OK")));
    }

}
