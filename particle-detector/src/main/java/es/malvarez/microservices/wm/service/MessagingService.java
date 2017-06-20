package es.malvarez.microservices.wm.service;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.ul.CollisionFound;
import es.malvarez.microservices.wm.command.IdentifyParticle;
import es.malvarez.microservices.wm.command.IdentifyParticleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * This is the Man-in-the-middle orchestrating all the cra... stuff
 */
@Service
public class MessagingService {

    private final IdentifyParticleHandler identifyParticleHandler;

    @Autowired
    public MessagingService(final IdentifyParticleHandler identifyParticleHandler) {
        this.identifyParticleHandler = identifyParticleHandler;
    }

    @StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.ul.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound found) {
        found.getParticles().forEach(it -> this.identifyParticleHandler.handle(new IdentifyParticle(found.getCollision(), it)));
    }
}