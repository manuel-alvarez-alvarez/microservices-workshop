package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.cqrs.command.FindCollisions;
import es.malvarez.microservices.cqrs.event.CollisionFound;

import java.util.List;

/**
 * This is the guy telling you if there is a collision or not ...
 */
public class CollisionDetector implements IAggregate {

    @Override
    public String getId() {
        return CollisionDetector.class.getName(); // single instance
    }

    /**
     * TODO 4. So lets detect collisions my friend!
     */
    public List<CollisionFound> findCollisions(final FindCollisions command) {
        throw new UnsupportedOperationException();
    }

    private void onCollisionFound(final CollisionFound found) {
        // nothing to do here
    }
}
