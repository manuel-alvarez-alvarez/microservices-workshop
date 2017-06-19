package es.malvarez.microservices.cqrs.readmodel;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.domain.Collision;
import es.malvarez.microservices.cqrs.event.CollisionFound;
import es.malvarez.microservices.cqrs.event.ParticleIdentified;
import es.malvarez.microservices.cqrs.service.CollisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * And here is where we save some of our read models
 */
@Component
public class CollisionReadModel {

    private final CollisionService collisionService;

    @Autowired
    public CollisionReadModel(final CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    /**
     * TODO 7. Populate our "read model"
     */
    /*@StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound event) {
        Collision.Builder builder = new Collision.Builder()
                .setId(event.getCollision())
                .setExperiment(event.getExperiment())
                .setSnapshot(event.getSnapshot(), event.getWhen());
        event.getParticles().forEach(particle -> builder.addParticle(particle, null));
        this.collisionService.newCollision(builder.build());
    }*/

    /**
     * TODO 8. Update the type of the particles
     */
    /*@StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.ParticleIdentified'"
    )
    public void onParticleIdentified(final ParticleIdentified event) {
        this.collisionService.updateParticle(event.getDetectedParticle().getId(), event.getType());
    }*/
}
