package es.malvarez.microservices.wm.command;

import es.malvarez.microservices.cqrs.ICommand;
import es.malvarez.microservices.ul.CollisionFound;

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
        return "ParticleDetector"; // single instance
    }
}
