package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.ParticleUtil;
import es.malvarez.microservices.cqrs.command.IdentifyParticles;
import es.malvarez.microservices.cqrs.event.CollisionFound;
import es.malvarez.microservices.cqrs.event.ParticlesIdentified;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Don't forge to test the detector as well!!!!
 */
@RunWith(JUnitParamsRunner.class)
public class ParticleDetectorTest {

    private final ParticleDetector particleDetector = new ParticleDetector();

    @Test
    @Parameters
    public void detectParticle(final IdentifyParticles command, final ParticleType type) throws Exception {
        assertThat(
                getParticle(particleDetector.identifyParticles(command)).map(DetectedParticle::getType).orElse(null)
        ).isEqualTo(type);
    }

    @Test
    @Parameters
    public void detectGluonOrPhoton(final IdentifyParticles command) throws Exception {
        assertThat(
                getParticle(particleDetector.identifyParticles(command)).map(DetectedParticle::getType).orElse(null)
        ).isIn(ParticleType.GLUON, ParticleType.PHOTON);
    }


    private Object[] parametersForDetectParticle() {
        return list(ParticleType.values().length, Experiment.HAWKING)
                .stream()
                .filter(it -> it.getType() != ParticleType.GLUON && it.getType() != ParticleType.PHOTON)
                .map(it -> new Object[]{buildCommand(it), it.getType()})
                .collect(Collectors.toList())
                .toArray(new Object[ParticleType.values().length - 2]);
    }

    private Object[] parametersForDetectGluonOrPhoton() {
        return Stream.of(ParticleType.GLUON, ParticleType.PHOTON)
                .map(it -> ParticleUtil.build(it, null))
                .map(it -> new Object[]{buildCommand(it)})
                .collect(Collectors.toList())
                .toArray(new Object[2]);
    }

    private List<DetectedParticle> list(int size, final Experiment experiment) {
        return IntStream.range(0, size)
                .mapToObj(i -> ParticleUtil.build(ParticleType.values()[i % ParticleType.values().length], experiment))
                .collect(Collectors.toList());
    }

    private IdentifyParticles buildCommand(final DetectedParticle particle) {
        return new IdentifyParticles(new CollisionFound(UUID.randomUUID(), new Date(), Experiment.EINSTEIN, Collections.singletonList(particle)));
    }

    private Optional<DetectedParticle> getParticle(final ParticlesIdentified event) {
        return event.getParticles().stream().findFirst();
    }
}
