package es.malvarez.microservices.monolith.domain;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.Snapshot;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * So this is where we save the booms!
 */
@Entity
public class Collision {

    @Id
    private UUID id;
    private UUID snapshot;
    @Enumerated(EnumType.STRING)
    private Experiment experiment;
    @Temporal(TemporalType.TIMESTAMP)
    private Date when;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", name = "COLLISION")
    private List<Particle> particles;

    protected Collision() {
    }

    private Collision(final UUID id,  final UUID snapshot, final Experiment experiment, final Date when, final List<Particle> particles) {
        this.id = id;
        this.snapshot = snapshot;
        this.experiment = experiment;
        this.when = when;
        this.particles = particles;
    }

    public UUID getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(final UUID snapshot) {
        this.snapshot = snapshot;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(final Experiment experiment) {
        this.experiment = experiment;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(final Date when) {
        this.when = when;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(final List<Particle> particles) {
        this.particles = particles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collision collision = (Collision) o;

        if (snapshot != null ? !snapshot.equals(collision.snapshot) : collision.snapshot != null) return false;
        return experiment == collision.experiment;
    }

    @Override
    public int hashCode() {
        int result = snapshot != null ? snapshot.hashCode() : 0;
        result = 31 * result + (experiment != null ? experiment.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private UUID snapshot;
        private Experiment experiment = null;
        private Date when;
        private final List<Particle> particles = new LinkedList<>();

        public Collision build() {
            return new Collision(id, snapshot, experiment, when, particles);
        }

        public Builder setId(UUID id) {
            this.id = id;
            this.particles.forEach(it -> it.setCollision(this.id));
            return this;
        }

        public Builder setSnapshot(final Snapshot snapshot) {
            return setSnapshot(snapshot.getId(), snapshot.getWhen());
        }

        public Builder setSnapshot(final UUID snapshot, final Date when) {
            this.snapshot = snapshot;
            this.when = when;
            return this;
        }

        public Builder setExperiment(final Experiment experiment) {
            this.experiment = experiment;
            return this;
        }

        public Builder addParticle(final DetectedParticle particle, final ParticleType type) {
            this.particles.add(
                    new Particle(
                            particle.getId(), id, type, particle.getSpin(), particle.getCharge(), particle.getMassInMevC2()
                    )
            );
            return this;
        }
    }
}
