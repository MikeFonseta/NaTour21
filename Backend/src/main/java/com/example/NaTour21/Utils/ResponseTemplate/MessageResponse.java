package com.example.NaTour21.Utils.ResponseTemplate;

import lombok.Data;

@Data
public class MessageResponse {

    private String from;
    private String content;

    public MessageResponse(String from, String content) {
        this.from = from;
        this.content = content;
    }
}
