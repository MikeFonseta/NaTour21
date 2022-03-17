package com.example.NaTour21.Duration.Repository;

import com.example.NaTour21.Duration.Entity.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DurationRepository extends JpaRepository<Duration,Long>{

    List<Duration>findAll();


    @Query(value = "SELECT * FROM duration where post_id = ?1",nativeQuery = true)
    List<Duration> findAllBy (Long post_id);

}
