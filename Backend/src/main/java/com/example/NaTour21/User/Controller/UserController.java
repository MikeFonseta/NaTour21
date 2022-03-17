package com.example.NaTour21.User.Controller;

import com.example.NaTour21.Enumeration.Auth;
import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Service.UserService;
import com.example.NaTour21.Utils.RequestTemplate.RegisterFacebookRequest;
import com.example.NaTour21.Utils.RequestTemplate.RegisterGoogleRequest;
import com.example.NaTour21.Utils.RequestTemplate.RegisterNaTour21Request;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Utils.ResponseTemplate.EmailResponse;
import com.example.NaTour21.Utils.ResponseTemplate.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private final JavaMailSender javaMailSender;

    @GetMapping("/users")
    public ResponseEntity<BasicResponse>getUsers() {
        BasicResponse response = new BasicResponse(userService.getUsers().toArray(), "OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/auth")
    public ResponseEntity<BasicResponse>getUserAuth(@Param("username") String username) {
        User user = userService.getUser(username.toLowerCase(Locale.ROOT));
        BasicResponse response;
        if(user != null) {
            response = new BasicResponse(user.getAuth(), "OK");
        }
        else
        {
            response = new BasicResponse("Nome utente non registrato", "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/facebook/login")
    public ResponseEntity<BasicResponse>checkFacebookUser(@Param("id") String id, @Param("email") String email) {
        User user = userService.getUserByFacebookId(id);
        BasicResponse response;
        if(user != null) {
            if (user.getAuth().equals(Auth.FACEBOOK.toString())) {
                if (!email.equals(user.getEmail())) {
                    user.setEmail(email);
                    userService.updateEmail(user);
                }
                response = new BasicResponse(new LoginResponse("LOGIN", user.getUsername()), "OK");
            }else
            {
                response = new BasicResponse("Email già registrata", "FAILED");
            }
        }
        else
        {
            response = new BasicResponse(new LoginResponse("REGISTER", email), "OK");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/google/login")
    public ResponseEntity<BasicResponse>checkGoogleUser(@Param("email") String email) {
        User user = userService.getUserByEmail(email);
        BasicResponse response;
        if(user != null) {
            if (user.getAuth().equals(Auth.GOOGLE.toString())) {
                response = new BasicResponse(new LoginResponse("LOGIN", user.getUsername()), "OK");
            }else
            {
                response = new BasicResponse("Email già registrata", "FAILED");
            }
        }
        else
        {
            response = new BasicResponse(new LoginResponse("REGISTER", email), "OK");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/register/natour21")
    public ResponseEntity<BasicResponse>registerNaTour21(@RequestBody RegisterNaTour21Request request)
    {
        BasicResponse response = null;
        User user = new User(request.getEmail().toLowerCase(Locale.ROOT),
                request.getUsername().toLowerCase(Locale.ROOT),
                request.getPassword(),
                Auth.NATOUR21.toString(),
                "ROLE_USER",null);
        try {
            response = new BasicResponse(userService.saveUser(user), "OK");
        } catch (Exception e) {
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/register/facebook")
    public ResponseEntity<BasicResponse>registerFacebook(@RequestBody RegisterFacebookRequest request)
    {
        BasicResponse response = null;
        User user = new User(request.getEmail().toLowerCase(Locale.ROOT),
                request.getUsername().toLowerCase(Locale.ROOT),
                request.getUser_id(),
                Auth.FACEBOOK.toString(),
                "ROLE_USER", request.getUser_id());
        try {
            response = new BasicResponse(userService.saveUser(user), "OK");
        } catch (Exception e) {
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/register/google")
    public ResponseEntity<BasicResponse>registerGoogle(@RequestBody RegisterGoogleRequest request)
    {
        BasicResponse response = null;
        User user = new User(request.getEmail().toLowerCase(Locale.ROOT),
                request.getUsername().toLowerCase(Locale.ROOT),
                request.getEmail() + request.getUsername() + "GOOGLE_AUTH",
                Auth.GOOGLE.toString(),
                "ROLE_USER",null);
        try {
            response = new BasicResponse(userService.saveUser(user), "OK");
        } catch (Exception e) {
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/sendemail")
    public ResponseEntity<BasicResponse>sendEmail(@Param("email") String email)
    {
        BasicResponse response = null;
        User user = userService.getUserByEmail(email);
        if(user != null && user.getAuth().equals(Auth.NATOUR21.toString()))
        {
            String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int count = 6;
            StringBuilder code = new StringBuilder();
            while (count-- != 0) {
                int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                code.append(ALPHA_NUMERIC_STRING.charAt(character));
            }

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setSubject("Recupero password NaTour21");
            mail.setText("Ciao " + user.getUsername() + ", questo è il codice per procedere con la reimpostazione della password: \n\n\n " + code);

            try {
                JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl)javaMailSender;
                javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.ssl.protocols", "TLSv1.2");
                javaMailSender.send(mail);
            } catch (Exception e) {

            }
            response = new BasicResponse(new EmailResponse(user.getEmail(), user.getUsername(), code.toString()), "OK");
        }else
        {
            response = new BasicResponse(email + " non è associata a nessun account", "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/update")
    public ResponseEntity<BasicResponse>updateUser(@Param("username") String username, @Param("password") String password) {
        User user = userService.getUser(username);
        BasicResponse response;
        if(user != null && user.getAuth().equals(Auth.NATOUR21.toString()))
        {
            user.setPassword(password);
            userService.updateUser(user);
            response = new BasicResponse("Password aggiornata", "OK");
        }
        else
        {
            response = new BasicResponse("Operazione fallita", "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

}
