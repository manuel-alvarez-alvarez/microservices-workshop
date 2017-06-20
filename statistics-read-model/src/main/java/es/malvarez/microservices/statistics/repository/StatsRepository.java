package es.malvarez.microservices.statistics.repository;

import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.statistics.domain.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repo ...
 */
public interface StatsRepository extends JpaRepository<Stats, ParticleType> {
}
