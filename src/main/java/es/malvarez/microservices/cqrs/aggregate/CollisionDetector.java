package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.cqrs.command.FindCollisions;
import es.malvarez.microservices.cqrs.event.CollisionFound;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This is the guy telling you if there is a collision or not ...
 */
public class CollisionDetector implements IAggregate {

    private static final int PARTICLES_PER_COLLISION = 3;

    @Override
    public String getId() {
        return CollisionDetector.class.getName(); // single instance
    }

    /**
     * TODO 3. So lets detect collisions my friend!
     */
    public List<CollisionFound> findCollisions(final FindCollisions command) {
        /*final Snapshot snapshot = command.getSnapshot();
        return snapshot.getParticles().stream()
                .collect(Collectors.groupingBy(DetectedParticle::getExperiment))
                .entrySet()
                .stream()
                .filter(it -> it.getValue().size() >= PARTICLES_PER_COLLISION)
                .map(it -> new CollisionFound(UUID.randomUUID(), snapshot.getId(), snapshot.getWhen(), it.getKey(), it.getValue()))
                .collect(Collectors.toList());*/
        throw new UnsupportedOperationException();
    }

    private void onCollisionFound(final CollisionFound found) {
        // nothing to do here
    }
}
