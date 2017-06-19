package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.ParticleUtil;
import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.cqrs.command.IdentifyParticle;
import es.malvarez.microservices.cqrs.event.ParticleIdentified;

import java.util.Arrays;

/**
 * And this is the detector for our beloved particles.
 */
public class ParticleDetector implements IAggregate {

    @Override
    public String getId() {
        return ParticleDetector.class.getName(); // single instance
    }

    public ParticleIdentified identifyParticle(final IdentifyParticle command) {
        DetectedParticle particle = command.getDetectedParticle();
        ParticleType type = Arrays.stream(ParticleType.values())
                .filter(it -> ParticleUtil.assertType(particle.getSpin(), particle.getCharge(), particle.getMassInMevC2(), it))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown particle type"));
        return new ParticleIdentified(command.getCollision(), particle, type);
    }

    private void onParticleIdentified(final ParticleIdentified event) {
        // nothing to do again
    }
}
