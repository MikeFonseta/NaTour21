package com.example.NaTour21.Duration.Controller;

import com.example.NaTour21.Duration.Service.DurationService;
import com.example.NaTour21.Duration.Entity.Duration;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor

public class DurationController {
    private final DurationService durationService;


    @GetMapping("/duration")
    public ResponseEntity<BasicResponse>getDuration(){
        BasicResponse response = new BasicResponse(durationService.getDuration().toArray(),"OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/insert/duration")
    public ResponseEntity<BasicResponse>savePost(@RequestBody Duration duration){
        BasicResponse response= null;
        try{
            response = new BasicResponse(durationService.saveDuration(duration),"OK");

        }catch (Exception e){
            response = new BasicResponse(e.getMessage(),"FAILED");
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/insert/durations")
    public ResponseEntity<BasicResponse>saveDifficulty(@RequestBody Duration duration){
        BasicResponse response= null;
        try{
            response = new BasicResponse(durationService.saveDurations(duration),"OK");

        }catch (Exception e){
            response = new BasicResponse(e.getMessage(),"FAILED");
        }

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/duration/id")
    public ResponseEntity<BasicResponse>getDurationById(@Param("post_id")Long post_id){
        BasicResponse response = new BasicResponse(durationService.getDurationById(post_id).toArray(),"OK");
        return ResponseEntity.ok().body(response);
    }

}
