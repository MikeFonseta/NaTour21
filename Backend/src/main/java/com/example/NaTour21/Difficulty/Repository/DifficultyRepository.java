package com.example.NaTour21.Difficulty.Repository;

import com.example.NaTour21.Difficulty.Entity.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DifficultyRepository extends JpaRepository<Difficulty,Long> {

    List<Difficulty>findAll();

    @Query(value = "SELECT * FROM difficulty where post_id = ?1",nativeQuery = true)
    List<Difficulty> findAllBy (Long post_id);


}
