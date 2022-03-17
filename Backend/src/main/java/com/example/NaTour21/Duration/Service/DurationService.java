package com.example.NaTour21.Duration.Service;

import com.example.NaTour21.Duration.Entity.Duration;
import com.example.NaTour21.Duration.Repository.DurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j

public class DurationService {

    private final DurationRepository durationRepository;

    public List<Duration> getDuration(){
        return durationRepository.findAll();
    }

    public Duration saveDuration(Duration duration)throws Exception{
        if(duration.getDuration()==null||duration.getMinutes()==0) {
            throw new Exception("Campi non validi");
        }else {
            return durationRepository.save(duration);
        }
    }

    public List<Duration> getDurationById(Long post_id){
        return durationRepository.findAllBy(post_id);
    }

    public Duration saveDurations(Duration duration) throws Exception{
        if(duration.getDuration()==null||duration.getMinutes()==0) {
            throw new Exception("Campi non validi");
        }else {
            return durationRepository.save(duration);
        }
    }

}
