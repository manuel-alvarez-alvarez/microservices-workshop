package es.malvarez.microservices.wm.aggregate;

import es.malvarez.microservices.cqrs.IAggregate;
import es.malvarez.microservices.ul.ParticlesIdentified;
import es.malvarez.microservices.wm.command.IdentifyParticles;

/**
 * And this is the detector for our beloved particles.
 */
public class ParticleDetector implements IAggregate {

    @Override
    public String getId() {
        return "ParticleDetector"; // single instance
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
