package es.malvarez.microservices.collision.readmodel;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.collision.domain.Collision;
import es.malvarez.microservices.ul.CollisionFound;
import es.malvarez.microservices.ul.ParticleIdentified;
import es.malvarez.microservices.collision.service.CollisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

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

    @StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.ul.CollisionFound'"
    )
    public void onCollisionFound(final CollisionFound event) {
        Collision.Builder builder = new Collision.Builder()
                .setId(event.getCollision())
                .setExperiment(event.getExperiment())
                .setSnapshot(event.getSnapshot(), event.getWhen());
        event.getParticles().forEach(particle -> builder.addParticle(particle, null));
        this.collisionService.newCollision(builder.build());
    }

    @StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.ul.ParticleIdentified'"
    )
    public void onParticleIdentified(final ParticleIdentified event) {
        this.collisionService.updateParticle(event.getDetectedParticle().getId(), event.getType());
    }
}
