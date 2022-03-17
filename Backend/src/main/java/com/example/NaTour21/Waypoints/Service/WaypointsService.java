package com.example.NaTour21.Waypoints.Service;

import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.example.NaTour21.Waypoints.Repository.WaypointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class WaypointsService {


    private final WaypointsRepository waypointsRepository;

    public Waypoints saveWaypoints(Waypoints waypoints) {
            return waypointsRepository.save(waypoints);
    }

    public List<Waypoints> getWaypoints(){
        return waypointsRepository.findAll();
    }

}
