package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.cqrs.ICommand;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;
import es.malvarez.microservices.cqrs.event.CollisionFound;

/**
 * Identify the particles inside a collision
 */
public class IdentifyParticles implements ICommand {

    private final CollisionFound collision;

    public IdentifyParticles(CollisionFound collision) {
        this.collision = collision;
    }

    public CollisionFound getCollision() {
        return collision;
    }

    @Override
    public String getAggregateId() {
        return ParticleDetector.class.getName(); // single instance
    }
}
