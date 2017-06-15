package es.malvarez.microservices.cqrs.service;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.cqrs.BroadcastSink;
import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.command.FindCollisions;
import es.malvarez.microservices.cqrs.command.FindCollisionsHandler;
import es.malvarez.microservices.cqrs.command.IdentifyParticles;
import es.malvarez.microservices.cqrs.command.IdentifyParticlesHandler;
import es.malvarez.microservices.cqrs.event.CollisionFound;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;
import es.malvarez.microservices.cqrs.readmodel.CollisionReadModel;
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
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound found) {
        this.identifyParticlesHandler.handle(new IdentifyParticles(found));
    }
}
