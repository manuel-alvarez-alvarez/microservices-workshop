package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.cqrs.CommandHandler;
import es.malvarez.microservices.cqrs.aggregate.CollisionDetector;
import es.malvarez.microservices.cqrs.repository.CollisionDetectorRepository;
import org.springframework.stereotype.Component;

/**
 * Command handler for the find collisions.
 */
@Component
public class FindCollisionsHandler extends CommandHandler<FindCollisions, CollisionDetector> {

    public FindCollisionsHandler(final CollisionDetectorRepository repository) {
        super(CollisionDetector::findCollisions, repository);
    }
}
