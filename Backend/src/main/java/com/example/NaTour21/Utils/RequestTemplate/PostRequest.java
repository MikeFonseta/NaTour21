package com.example.NaTour21.Utils.RequestTemplate;

import lombok.Data;

@Data
public class PostRequest {

    String description;
    String title;
    String startpoint;
    double lat1;
    double lng1;
    double lat2;
    double lng2;
    String duration;
    Integer minutes;
    Integer difficulty;
    String username;
}
