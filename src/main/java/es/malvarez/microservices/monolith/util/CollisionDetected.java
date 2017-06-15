package es.malvarez.microservices.monolith.util;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.api.Snapshot;

import java.util.List;

/**
 * It looks like we've found some colliding particles, hurray!
 */
public class CollisionDetected {

    private final Snapshot snapshot;
    private final Experiment experiment;
    private final List<DetectedParticle> particles;

    public CollisionDetected(final Snapshot snapshot, final Experiment experiment, final List<DetectedParticle> particles) {
        this.snapshot = snapshot;
        this.experiment = experiment;
        this.particles = particles;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public List<DetectedParticle> getParticles() {
        return particles;
    }
}