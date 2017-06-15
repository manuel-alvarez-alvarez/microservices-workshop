package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.cqrs.command.IdentifyParticles;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;

/**
 * And this is the detector for our beloved particles.
 */
public class ParticleDetector implements IAggregate {

    @Override
    public String getId() {
        return ParticleDetector.class.getName(); // single instance
    }

    /**
     * TODO 5. Now it's time to figure it out the type of each particle!
     */
    public ParticlesIdentified identifyParticles(final IdentifyParticles command) {
        throw new UnsupportedOperationException();
    }

    private void onParticlesIdentified(final ParticlesIdentified event) {
        // nothing to do again
    }
}
