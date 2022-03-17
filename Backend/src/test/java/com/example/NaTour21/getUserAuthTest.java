package com.example.NaTour21;

import com.example.NaTour21.User.Controller.UserController;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class getUserAuthTest {

    @Autowired
    private UserController userController;

    String t1 = "mike.fonseta";
    String t2 = "invalidUsername";

    @Test
    public void test_1_2_3_4_7()
    {
        assertEquals(userController.getUserAuth(t1),
                ResponseEntity
                        .ok()
                        .body(new BasicResponse("NATOUR21", "OK")));
    }

    @Test
    public void test_1_2_3_5_6_7()
    {
        assertEquals(userController.getUserAuth(t2),
                ResponseEntity
                        .ok()
                        .body(new BasicResponse("Nome utente non registrato", "FAILED")));
    }

}
