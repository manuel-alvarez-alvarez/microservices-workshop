package es.malvarez.microservices.cqrs.readmodel;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.web.config.DetectorSettings;
import es.malvarez.microservices.web.domain.Collision;
import es.malvarez.microservices.web.service.CollisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
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

    public void newCollision(final UUID snapshot, final Date when, final List<DetectedParticle> particles) {
        this.collisionService.newCollision(new Collision.Builder()
                .setId(UUID.randomUUID())
                .setSnapshot(snapshot, when)
                .setName(detectorSettings.getName())
                .addParticles(particles)
                .build()
        );
    }
}
