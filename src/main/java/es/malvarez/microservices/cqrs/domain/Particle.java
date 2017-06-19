package es.malvarez.microservices.cqrs.domain;

import es.malvarez.microservices.api.Charge;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.Spin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * And this is what we are detecting
 */
@Entity
public class Particle {

    @Id
    private UUID id;
    @Column(name = "COLLISION")
    private UUID collision;
    @Enumerated(EnumType.STRING)
    private ParticleType type;
    @Enumerated(EnumType.STRING)
    private Spin spin;
    @Enumerated(EnumType.STRING)
    private Charge charge;
    private BigDecimal massInMevC2;

    protected Particle() {
    }

    Particle(UUID id, UUID collision, ParticleType type, Spin spin, Charge charge, BigDecimal massInMevC2) {
        this.id = id;
        this.collision = collision;
        this.type = type;
        this.spin = spin;
        this.charge = charge;
        this.massInMevC2 = massInMevC2;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public ParticleType getType() {
        return type;
    }

    public void setType(final ParticleType type) {
        this.type = type;
    }

    public Spin getSpin() {
        return spin;
    }

    public void setSpin(Spin spin) {
        this.spin = spin;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public BigDecimal getMassInMevC2() {
        return massInMevC2;
    }

    public void setMassInMevC2(BigDecimal massInMevC2) {
        this.massInMevC2 = massInMevC2;
    }

    public UUID getCollision() {
        return collision;
    }

    public void setCollision(UUID collision) {
        this.collision = collision;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        return id != null ? id.equals(particle.id) : particle.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
