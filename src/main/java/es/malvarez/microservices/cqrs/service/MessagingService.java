package es.malvarez.microservices.cqrs.service;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.command.FindCollisions;
import es.malvarez.microservices.cqrs.command.FindCollisionsHandler;
import es.malvarez.microservices.cqrs.command.IdentifyParticle;
import es.malvarez.microservices.cqrs.command.IdentifyParticleHandler;
import es.malvarez.microservices.cqrs.event.CollisionFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * This is the Man-in-the-middle orchestrating all the cra... stuff
 */
@Service
public class MessagingService {

    private final FindCollisionsHandler findCollisionsHandler;
    private final IdentifyParticleHandler identifyParticleHandler;

    @Autowired
    public MessagingService(
            final FindCollisionsHandler findCollisionsHandler,
            final IdentifyParticleHandler identifyParticleHandler
    ) {
        this.findCollisionsHandler = findCollisionsHandler;
        this.identifyParticleHandler = identifyParticleHandler;
    }

    /**
     * TODO 5. Don't leave the accelerator alone man! it's dangerous
     */
    /*@StreamListener(AcceleratorSink.INPUT)
    public void onSnapshot(final Snapshot snapshot) {
        this.findCollisionsHandler.handle(new FindCollisions(snapshot));
    }*/

    /**
     * TODO 6. Link the particle detector with the collision finder
     */
    /*@StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound found) {
        found.getParticles().forEach(it -> this.identifyParticleHandler.handle(new IdentifyParticle(found.getCollision(), it)));
    }*/
}
