package es.malvarez.microservices.ul;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.cqrs.IEvent;

import java.util.UUID;

/**
 * Finally we got the mojo, here are all the particles of the boom!
 */
public class ParticleIdentified implements IEvent {

    private final UUID collision;
    private final DetectedParticle detectedParticle;
    private final ParticleType type;

    public ParticleIdentified(final UUID collision, final DetectedParticle detectedParticle, final ParticleType type) {
        this.collision = collision;
        this.detectedParticle = detectedParticle;
        this.type = type;
    }

    @Override
    public String getAggregateId() {
        return "ParticleDetector"; // single instance
    }

    public UUID getCollision() {
        return collision;
    }

    public DetectedParticle getDetectedParticle() {
        return detectedParticle;
    }

    public ParticleType getType() {
        return type;
    }
}