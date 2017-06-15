package es.malvarez.microservices.cqrs.readmodel;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;
import es.malvarez.microservices.web.config.DetectorSettings;
import es.malvarez.microservices.web.domain.Collision;
import es.malvarez.microservices.web.service.CollisionService;
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
    private final DetectorSettings detectorSettings;

    @Autowired
    public CollisionReadModel(final CollisionService collisionService,
                              final DetectorSettings detectorSettings) {
        this.collisionService = collisionService;
        this.detectorSettings = detectorSettings;
    }

    @StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.cqrs.event.ParticlesIdentified'"
    )
    public void onParticlesIdentified(final ParticlesIdentified event) {
        this.collisionService.newCollision(new Collision.Builder()
                .setId(UUID.randomUUID())
                .setSnapshot(event.getSnapshot(), event.getWhen())
                .setName(detectorSettings.getName())
                .addParticles(event.getParticles())
                .build()
        );
    }
}
