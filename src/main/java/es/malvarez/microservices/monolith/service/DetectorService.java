package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.api.*;
import es.malvarez.microservices.monolith.domain.Collision;
import es.malvarez.microservices.monolith.util.CollisionDetected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is the core detecting service
 */
@Service
public class DetectorService {

    private static final int PARTICLES_PER_COLLISION = 3;

    private final CollisionService collisionService;

    @Autowired
    public DetectorService(final CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    @StreamListener(AcceleratorSink.INPUT)
    public void onSnapshot(final Snapshot snapshot) {
        this.detect(snapshot)
                .map(collisionDetected -> {
                    Collision.Builder builder = new Collision.Builder()
                            .setSnapshot(snapshot)
                            .setExperiment(collisionDetected.getExperiment())
                            .setId(UUID.randomUUID());
                    collisionDetected.getParticles().forEach(particle ->
                            builder.addParticle(particle, this.detect(particle.getSpin(), particle.getCharge(), particle.getMassInMevC2()))
                    );
                    return builder.build();
                })
                .forEach(collisionService::newCollision);
    }

    /**
     * Detect collisions: a collision happens when tree or more particles are detected in the same experiment.
     */
    public Stream<CollisionDetected> detect(final Snapshot snapshot) {
        return snapshot.getParticles().stream()
                .collect(Collectors.groupingBy(DetectedParticle::getExperiment))
                .entrySet()
                .stream()
                .filter(it -> it.getValue().size() >= PARTICLES_PER_COLLISION)
                .map(it -> new CollisionDetected(snapshot, it.getKey(), it.getValue()));
    }

    /**
     * Detect particles: giving the properties of the thingy ... get the type.
     */
    public ParticleType detect(final Spin spin, final Charge charge, final BigDecimal massInMevC2) {
        return Arrays.stream(ParticleType.values())
                .filter(it -> ParticleUtil.assertType(spin, charge, massInMevC2, it))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown particle type"));
    }

}
