package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.*;
import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.cqrs.command.IdentifyParticles;
import es.malvarez.microservices.cqrs.event.CollisionFound;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * And this is the detector for our beloved particles.
 */
public class ParticleDetector implements IAggregate {

    @Override
    public String getId() {
        return ParticleDetector.class.getName(); // single instance
    }

    public ParticlesIdentified identifyParticles(final IdentifyParticles command) {
        CollisionFound collision = command.getCollision();
        return new ParticlesIdentified(
                collision.getSnapshot(),
                collision.getWhen(),
                collision.getExperiment(),
                collision.getParticles().stream()
                        .map(particle -> new DetectedParticle.Builder()
                                .from(particle)
                                .setType(detect(particle.getSpin(), particle.getCharge(), particle.getMassInMevC2()))
                                .build())
                        .collect(Collectors.toList())
        );
    }

    private ParticleType detect(final Spin spin, final Charge charge, final BigDecimal massInMevC2) {
        return Arrays.stream(ParticleType.values())
                .filter(it -> ParticleUtil.assertType(spin, charge, massInMevC2, it))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown particle type"));
    }

    private void onParticlesIdentified(final ParticlesIdentified event) {
        // nothing to do again
    }
}
