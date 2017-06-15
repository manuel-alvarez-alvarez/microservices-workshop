package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.cqrs.CommandHandler;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;
import es.malvarez.microservices.cqrs.repository.ParticleDetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * And the other guy to identify the particles.
 */
@Component
public class IdentifyParticlesHandler extends CommandHandler<IdentifyParticles, ParticleDetector> {

    @Autowired
    public IdentifyParticlesHandler(final ParticleDetectorRepository repository) {
        super(IdentifyParticlesHandler::adapter, repository);
    }

    private static List<ParticlesIdentified> adapter(final ParticleDetector aggregate, final IdentifyParticles command) {
        return Collections.singletonList(aggregate.identifyParticles(command));
    }
}
