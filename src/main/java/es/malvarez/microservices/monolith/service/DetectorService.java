package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.api.*;
import es.malvarez.microservices.monolith.util.CollisionDetected;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is the core detecting service, it should>
 * <p>
 * 1. Detect collisions: a collision happens when tree or more particles are detected in the same experiment.
 * 2. Detect particles: giving the properties of the thingy ... get the type.
 */
@Service
public class DetectorService {

    private static final int PARTICLES_PER_COLLISION = 3;

    public Stream<CollisionDetected> detect(final Snapshot snapshot) {
        return snapshot.getParticles().stream()
                .collect(Collectors.groupingBy(DetectedParticle::getExperiment))
                .entrySet()
                .stream()
                .filter(it -> it.getValue().size() >= PARTICLES_PER_COLLISION)
                .map(it -> new CollisionDetected(snapshot, it.getKey(), it.getValue()));
    }

    public ParticleType detect(final Spin spin, final Charge charge, final BigDecimal massInMevC2) {
        return Arrays.stream(ParticleType.values())
                .filter(it -> ParticleUtil.assertType(spin, charge, massInMevC2, it))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown particle type"));
    }

}
