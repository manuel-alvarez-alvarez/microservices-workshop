package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.cqrs.ICommand;
import es.malvarez.microservices.cqrs.aggregate.CollisionDetector;

/**
 * Find me your collisions mate.
 */
public class FindCollisions implements ICommand {

    private final Snapshot snapshot;

    public FindCollisions(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public String getAggregateId() {
        return CollisionDetector.class.getName(); // single instance
    }
}
