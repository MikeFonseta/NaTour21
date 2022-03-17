package com.example.NaTour21.Utils.RequestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MessageRequest {

    private String from;
    private String to;
    private String content;
}
