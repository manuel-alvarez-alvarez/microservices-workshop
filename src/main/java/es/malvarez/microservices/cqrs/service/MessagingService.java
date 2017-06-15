package es.malvarez.microservices.cqrs.service;

import es.malvarez.microservices.cqrs.command.FindCollisionsHandler;
import es.malvarez.microservices.cqrs.command.IdentifyParticlesHandler;
import es.malvarez.microservices.cqrs.readmodel.CollisionReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the Man-in-the-middle orchestrating all the cra... stuff
 */
@Service
public class MessagingService {

    private final FindCollisionsHandler findCollisionsHandler;
    private final IdentifyParticlesHandler identifyParticlesHandler;
    private final CollisionReadModel collisionReadModel;

    @Autowired
    public MessagingService(
            final FindCollisionsHandler findCollisionsHandler,
            final IdentifyParticlesHandler identifyParticlesHandler,
            final CollisionReadModel collisionReadModel
    ) {
        this.findCollisionsHandler = findCollisionsHandler;
        this.identifyParticlesHandler = identifyParticlesHandler;
        this.collisionReadModel = collisionReadModel;
    }

    /**
     * TODO 6. Don't leave the accelerator alone man! it's dangerous
     */
    /*@StreamListener(AcceleratorSink.INPUT)
    public void onCollisionFound(final Snapshot snapshot) {
        this.findCollisionsHandler.handle(new FindCollisions(snapshot));
    }*/

    /**
     * TODO 7. Link the particle detector with the collision finder
     */
    /*@StreamListener(
            value = BroadcastSink.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound found) {
        this.identifyParticlesHandler.handle(new IdentifyParticles(found));
    }*/

    /**
     * TODO 8. Popoulate our "read model"
     */
    /*@StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.ParticlesIdentified'"
    )
    public void onParticlesIdentified(final ParticlesIdentified event) {
        this.collisionReadModel.newCollision(event.getSnapshot(), event.getWhen(), event.getParticles());
    }*/
}
