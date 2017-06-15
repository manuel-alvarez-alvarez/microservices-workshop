package es.malvarez.microservices.cqrs.repository;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.InMemoryRepository;
import es.malvarez.microservices.cqrs.aggregate.CollisionDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Repository for the collision detector aggregate
 */
@Component
public class CollisionDetectorRepository extends InMemoryRepository<CollisionDetector> {

    @Autowired
    public CollisionDetectorRepository(final EventStoreProcessor eventStore) {
        super(CollisionDetector.class, eventStore);
    }
}
