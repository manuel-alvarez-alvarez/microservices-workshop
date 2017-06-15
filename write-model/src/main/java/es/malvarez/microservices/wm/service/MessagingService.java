package es.malvarez.microservices.wm.service;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.cqrs.BroadcastSink;
import es.malvarez.microservices.ul.CollisionFound;
import es.malvarez.microservices.wm.command.FindCollisions;
import es.malvarez.microservices.wm.command.FindCollisionsHandler;
import es.malvarez.microservices.wm.command.IdentifyParticles;
import es.malvarez.microservices.wm.command.IdentifyParticlesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * This is the Man-in-the-middle orchestrating all the cra... stuff
 */
@Service
public class MessagingService {

    private final FindCollisionsHandler findCollisionsHandler;
    private final IdentifyParticlesHandler identifyParticlesHandler;

    @Autowired
    public MessagingService(
            final FindCollisionsHandler findCollisionsHandler,
            final IdentifyParticlesHandler identifyParticlesHandler
    ) {
        this.findCollisionsHandler = findCollisionsHandler;
        this.identifyParticlesHandler = identifyParticlesHandler;
    }

    @StreamListener(AcceleratorSink.INPUT)
    public void onCollisionFound(final Snapshot snapshot) {
        this.findCollisionsHandler.handle(new FindCollisions(snapshot));
    }

    @StreamListener(
            value = BroadcastSink.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.ul.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound found) {
        this.identifyParticlesHandler.handle(new IdentifyParticles(found));
    }
}