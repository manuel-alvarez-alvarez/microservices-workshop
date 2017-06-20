package es.malvarez.microservices.statistics.domain;

import es.malvarez.microservices.api.ParticleType;

import javax.persistence.*;

/**
 * This guy represents our stats
 */
@Entity
public class Stats {

    @Id
    @Enumerated(EnumType.STRING)
    private ParticleType particle;

    @Column
    private Integer count;

    public ParticleType getParticle() {
        return particle;
    }

    public void setParticle(ParticleType particle) {
        this.particle = particle;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        return particle == stats.particle;
    }

    @Override
    public int hashCode() {
        return particle != null ? particle.hashCode() : 0;
    }
}
