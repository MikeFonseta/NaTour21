package com.example.NaTour21.Difficulty.Service;

import com.example.NaTour21.Difficulty.Repository.DifficultyRepository;
import com.example.NaTour21.Difficulty.Entity.Difficulty;
import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j

public class DifficultyService {

    private final DifficultyRepository difficultyRepository;
    private final PostRepository postRepository;

    public List<Difficulty> getDifficulty(){
        return difficultyRepository.findAll();
    }

    public Difficulty saveDifficulty(Difficulty difficulty){
        difficulty.setPost_id(postRepository.findFirstByOrderByIdDesc().getId());
        return difficultyRepository.save(difficulty);

    }

    public List<Difficulty> getDifficultyById(Long post_id){
        return difficultyRepository.findAllBy(post_id);
    }

    public Difficulty saveDifficulties(Difficulty difficulty){
        return difficultyRepository.save(difficulty);
    }

}
