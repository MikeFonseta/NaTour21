package com.example.NaTour21.Difficulty.Controller;

import com.example.NaTour21.Difficulty.Entity.Difficulty;
import com.example.NaTour21.Difficulty.Service.DifficultyService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor

public class DifficultyController {

    private final DifficultyService difficultyService;

    @GetMapping("/difficulty")
    public ResponseEntity<BasicResponse>getDifficulty(){
        BasicResponse response = new BasicResponse(difficultyService.getDifficulty().toArray(),"OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/insert/difficulty")
    public ResponseEntity<BasicResponse>savePost(@RequestBody Difficulty difficulty){
        BasicResponse response= null;
        try{
            response = new BasicResponse(difficultyService.saveDifficulty(difficulty),"OK");

        }catch (Exception e){
            response = new BasicResponse(e.getMessage(),"FAILED");
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/insert/difficulties")
    public ResponseEntity<BasicResponse>saveDifficulty(@RequestBody Difficulty difficulty){
        BasicResponse response= null;
        try{
            response = new BasicResponse(difficultyService.saveDifficulties(difficulty),"OK");

        }catch (Exception e){
            response = new BasicResponse(e.getMessage(),"FAILED");
        }

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/difficulty/id")
    public ResponseEntity<BasicResponse>getDifficultyById(@Param("post_id")Long post_id){
        BasicResponse response = new BasicResponse(difficultyService.getDifficultyById(post_id).toArray(),"OK");
        return ResponseEntity.ok().body(response);

    }


}
