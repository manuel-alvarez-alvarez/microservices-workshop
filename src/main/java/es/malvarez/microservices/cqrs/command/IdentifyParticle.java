package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.cqrs.ICommand;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;

import java.util.UUID;

/**
 * Identify the particles inside a collision
 */
public class IdentifyParticle implements ICommand {

    private final UUID collision;
    private final DetectedParticle detectedParticle;

    public IdentifyParticle(final UUID collision, final DetectedParticle detectedParticle) {
        this.collision = collision;
        this.detectedParticle = detectedParticle;
    }

    public UUID getCollision() {
        return collision;
    }

    public DetectedParticle getDetectedParticle() {
        return detectedParticle;
    }

    @Override
    public String getAggregateId() {
        return ParticleDetector.class.getName(); // single instance
    }
}
