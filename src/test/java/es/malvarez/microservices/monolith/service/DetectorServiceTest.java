package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.api.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * And testing dont forget about testing :)
 */
@RunWith(JUnitParamsRunner.class)
public class DetectorServiceTest {

    private final DetectorService detectorService = new DetectorService();

    @Test
    @Parameters
    public void detectParticle(final DetectedParticle particle) throws Exception {
        assertThat(detectorService.detect(particle.getSpin(), particle.getCharge(), particle.getMassInMevC2())).isEqualTo(particle.getType());
    }

    @Test
    @Parameters
    public void detectGluonOrPhoton(final DetectedParticle particle) throws Exception {
        assertThat(detectorService.detect(particle.getSpin(), particle.getCharge(), particle.getMassInMevC2())).isIn(ParticleType.GLUON, ParticleType.PHOTON);
    }

    @Test
    @Parameters
    public void detectCollision(final Snapshot snapshot, final int count) throws Exception {
        assertThat(detectorService.detect(snapshot).count()).isEqualTo(count);
    }

    private Object[] parametersForDetectParticle() {
        return list(ParticleType.values().length, Experiment.HAWKING)
                .stream()
                .filter(it -> it.getType() != ParticleType.GLUON && it.getType() != ParticleType.PHOTON)
                .collect(Collectors.toList())
                .toArray(new Object[ParticleType.values().length - 2]);
    }

    private Object[] parametersForDetectGluonOrPhoton() {
        return Stream.of(ParticleType.GLUON, ParticleType.PHOTON)
                .map(it -> ParticleUtil.build(it, null))
                .collect(Collectors.toList())
                .toArray(new Object[2]);
    }

    private Object[] parametersForDetectCollision() {
        return new Object[]{
                new Object[]{new Snapshot.Builder().build(), 0},
                new Object[]{new Snapshot.Builder().addParticles(list(2, Experiment.HAWKING)).build(), 0},
                new Object[]{new Snapshot.Builder().addParticles(list(10, Experiment.HAWKING)).build(), 1},
                new Object[]{
                        new Snapshot.Builder().addParticles(
                                Stream.concat(
                                        list(10, Experiment.HAWKING).stream(),
                                        list(2, Experiment.EINSTEIN).stream()
                                ).collect(Collectors.toList())).build(),
                        1
                },
                new Object[]{
                        new Snapshot.Builder().addParticles(
                                Stream.concat(
                                        list(10, Experiment.HAWKING).stream(),
                                        list(7, Experiment.EINSTEIN).stream()
                                ).collect(Collectors.toList())).build(),
                        2
                },
        };
    }

    private List<DetectedParticle> list(int size, final Experiment experiment) {
        return IntStream.range(0, size)
                .mapToObj(i -> ParticleUtil.build(ParticleType.values()[i%ParticleType.values().length], experiment))
                .collect(Collectors.toList());
    }
}
