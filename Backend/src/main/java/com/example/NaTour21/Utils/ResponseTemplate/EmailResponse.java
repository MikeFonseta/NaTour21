package com.example.NaTour21.Utils.ResponseTemplate;

import lombok.Data;

@Data
public class EmailResponse {

    private String email;
    private String username;
    private String code;

    public EmailResponse(String email, String username, String code) {
        this.email = email;
        this.username = username;
        this.code = code;
    }
}
