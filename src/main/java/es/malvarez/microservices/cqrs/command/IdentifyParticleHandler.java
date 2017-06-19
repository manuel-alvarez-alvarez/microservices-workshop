package es.malvarez.microservices.cqrs.command;

import es.malvarez.microservices.cqrs.CommandHandler;
import es.malvarez.microservices.cqrs.aggregate.ParticleDetector;
import es.malvarez.microservices.cqrs.event.ParticleIdentified;
import es.malvarez.microservices.cqrs.repository.ParticleDetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * And the other guy to identify the particles.
 */
@Component
public class IdentifyParticleHandler extends CommandHandler<IdentifyParticle, ParticleDetector> {

    @Autowired
    public IdentifyParticleHandler(final ParticleDetectorRepository repository) {
        super(IdentifyParticleHandler::adapter, repository);
    }

    private static List<ParticleIdentified> adapter(final ParticleDetector aggregate, final IdentifyParticle command) {
        return Collections.singletonList(aggregate.identifyParticle(command));
    }
}
