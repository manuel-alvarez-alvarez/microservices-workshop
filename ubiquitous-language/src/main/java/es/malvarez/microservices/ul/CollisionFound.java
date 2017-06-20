package es.malvarez.microservices.ul;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.cqrs.IEvent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * So we found a collision in the system!!! congrats!
 */
public class CollisionFound implements IEvent {

    private final UUID collision;
    private final UUID snapshot;
    private final Date when;
    private final Experiment experiment;
    private final List<DetectedParticle> particles;

    public CollisionFound(final UUID collision, final UUID snapshot, final Date when, final Experiment experiment, final List<DetectedParticle> particles) {
        this.collision = collision;
        this.snapshot = snapshot;
        this.when = when;
        this.experiment = experiment;
        this.particles = particles;
    }

    @Override
    public String getAggregateId() {
        return "CollisionDetector"; // single instance
    }

    public UUID getCollision() {
        return collision;
    }

    public UUID getSnapshot() {
        return snapshot;
    }

    public Date getWhen() {
        return when;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public List<DetectedParticle> getParticles() {
        return particles;
    }
}
