package es.malvarez.microservices.statistics.service;

import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.statistics.domain.Stats;
import es.malvarez.microservices.statistics.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The service ....
 */
@Service
public class StatsService {

    private final StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional(readOnly = true)
    public List<Stats> findALl() {
        return this.statsRepository.findAll();
    }

    @Transactional
    public void incrementParticle(final ParticleType type) {
        Stats stats = this.statsRepository.findOne(type);
        if (stats == null) {
            stats = new Stats();
            stats.setParticle(type);
            stats.setCount(0);
        }
        stats.setCount(stats.getCount() + 1);
        this.statsRepository.save(stats);
    }
}
