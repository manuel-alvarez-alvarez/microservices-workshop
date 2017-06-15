package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.api.Charge;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.api.Spin;
import es.malvarez.microservices.monolith.util.CollisionDetected;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

/**
 * This is the core detecting service, it should>
 * <p>
 * 1. Detect collisions: a collision happens when tree or more particles are detected in the same experiment.
 * 2. Detect particles: giving the properties of the thingy ... get the type.
 */
@Service
public class DetectorService {

    /**
     * TODO 5. Find the collisions from the snapshot
     */
    public Stream<CollisionDetected> detect(final Snapshot snapshot) {
       throw new UnsupportedOperationException();
    }

    /**
     * TODO 6. Detect the final particle using its properties
     */
    public ParticleType detect(final Spin spin, final Charge charge, final BigDecimal massInMevC2) {
        throw new UnsupportedOperationException();
    }

}
