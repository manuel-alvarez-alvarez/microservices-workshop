package es.malvarez.microservices.cqrs.repository;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.InMemoryRepository;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Repository for the particle detector
 */
@Component
public class ParticleDetectorRepository extends InMemoryRepository<ParticleDetector> {

    @Autowired
    public ParticleDetectorRepository(final EventStoreProcessor eventStore) {
        super(ParticleDetector.class, eventStore);
    }
}
