package es.malvarez.microservices.wm.aggregate;

import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.ul.CollisionFound;
import es.malvarez.microservices.wm.command.FindCollisions;

import java.util.List;

/**
 * This is the guy telling you if there is a collision or not ...
 */
public class CollisionDetector implements IAggregate {

    @Override
    public String getId() {
        return "CollisionDetector"; // single instance
    }

    /**
     * TODO 1. So lets detect collisions my friend!
     */
    public List<CollisionFound> findCollisions(final FindCollisions command) {
        throw new UnsupportedOperationException();
    }

    private void onCollisionFound(final CollisionFound found) {
        // nothing to do here
    }
}