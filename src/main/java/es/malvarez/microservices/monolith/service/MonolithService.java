package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.web.config.DetectorSettings;
import es.malvarez.microservices.web.domain.Collision;
import es.malvarez.microservices.web.service.CollisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * I can't believe we are building a Monolith, you are such a waste of my time :(
 */
@Service
public class MonolithService {

    private final CollisionService collisionService;
    private final DetectorService detectorService;
    private final DetectorSettings detectorSettings;

    @Autowired
    public MonolithService(
            final CollisionService collisionService,
            final DetectorService detectorService,
            final DetectorSettings detectorSettings
    ) {
        this.collisionService = collisionService;
        this.detectorService = detectorService;
        this.detectorSettings = detectorSettings;
    }

    @StreamListener(AcceleratorSink.INPUT)
    public void onSnapshot(final Snapshot snapshot) {
        this.detectorService.detect(snapshot)
                .map(collisionDetected ->
                        new Collision.Builder()
                                .setSnapshot(snapshot)
                                .setId(UUID.randomUUID())
                                .setName(detectorSettings.getName())
                                .addParticles(
                                        collisionDetected.getParticles().stream()
                                                .map(it -> new DetectedParticle.Builder()
                                                        .from(it)
                                                        .setType(detectorService.detect(it.getSpin(), it.getCharge(), it.getMassInMevC2()))
                                                        .build())
                                                .collect(Collectors.toList()))
                                .build())
                .forEach(collisionService::newCollision);
    }
}
