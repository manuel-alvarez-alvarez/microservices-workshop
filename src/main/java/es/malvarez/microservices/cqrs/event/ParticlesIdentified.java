package es.malvarez.microservices.cqrs.event;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.cqrs.IEvent;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Finally we got the mojo, here are all the particles of the boom!
 */
public class ParticlesIdentified implements IEvent {

    private final UUID snapshot;
    private final Date when;
    private final Experiment experiment;
    private final List<DetectedParticle> particles;

    public ParticlesIdentified(UUID snapshot, Date when, Experiment experiment, List<DetectedParticle> particles) {
        this.snapshot = snapshot;
        this.when = when;
        this.experiment = experiment;
        this.particles = particles;
    }

    @Override
    public String getAggregateId() {
        return ParticleDetector.class.getName(); // single instance
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
