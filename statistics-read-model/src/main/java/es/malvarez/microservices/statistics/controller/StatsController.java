package es.malvarez.microservices.statistics.controller;

import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.statistics.domain.Stats;
import es.malvarez.microservices.statistics.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * And the controller ...
 */
@RestController
@RequestMapping(value = "/api")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public Map<ParticleType, Integer> getStats() {
        return statsService.findALl().stream().collect(Collectors.toMap(
                Stats::getParticle,
                Stats::getCount
        ));
    }
}
