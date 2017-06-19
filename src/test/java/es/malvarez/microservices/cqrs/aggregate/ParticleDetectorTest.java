package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.DetectedParticle;
import es.malvarez.microservices.api.Experiment;
import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.api.ParticleUtil;
import es.malvarez.microservices.cqrs.command.IdentifyParticle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
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
    public void detectParticle(final IdentifyParticle command, final ParticleType type) throws Exception {
        assertThat(particleDetector.identifyParticle(command).getType()).isEqualTo(type);
    }

    @Test
    @Parameters
    public void detectGluonOrPhoton(final IdentifyParticle command) throws Exception {
        assertThat(particleDetector.identifyParticle(command).getType()).isIn(ParticleType.GLUON, ParticleType.PHOTON);
    }


    private Object[] parametersForDetectParticle() {
        return list(ParticleType.values().length, Experiment.HAWKING)
                .entrySet()
                .stream()
                .filter(it -> it.getValue() != ParticleType.GLUON && it.getValue() != ParticleType.PHOTON)
                .map(it -> new Object[]{buildCommand(it.getKey()), it.getValue()})
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

    private Map<DetectedParticle, ParticleType> list(int size, final Experiment experiment) {
        return IntStream.range(0, size)
                .mapToObj(i -> ParticleType.values()[i])
                .collect(Collectors.toMap(
                        type -> ParticleUtil.build(type, experiment),
                        Function.identity()
                ));
    }

    private IdentifyParticle buildCommand(final DetectedParticle particle) {
        return new IdentifyParticle(UUID.randomUUID(), particle);
    }
}
