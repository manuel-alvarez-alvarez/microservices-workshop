package es.malvarez.microservices.cqrs.aggregate;

import es.malvarez.microservices.api.*;
import es.malvarez.microservices.cqrs.command.FindCollisions;
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
 * Lets test our magnificent collision detector
 */
@RunWith(JUnitParamsRunner.class)
public class CollisionDetectorTest {

    private final CollisionDetector collisionDetector = new CollisionDetector();

    @Test
    @Parameters
    public void detectCollision(final FindCollisions command, final int count) throws Exception {
        assertThat(collisionDetector.findCollisions(command).size()).isEqualTo(count);
    }

    private Object[] parametersForDetectCollision() {
        return new Object[]{
                new Object[]{new FindCollisions(new Snapshot.Builder().build()), 0},
                new Object[]{new FindCollisions(new Snapshot.Builder().addParticles(list(2, Experiment.HAWKING)).build()), 0},
                new Object[]{new FindCollisions(new Snapshot.Builder().addParticles(list(10, Experiment.HAWKING)).build()), 1},
                new Object[]{
                        new FindCollisions(
                                new Snapshot.Builder().addParticles(
                                        Stream.concat(
                                                list(10, Experiment.HAWKING).stream(),
                                                list(2, Experiment.EINSTEIN).stream()
                                        ).collect(Collectors.toList())).build()
                        ),
                        1
                },
                new Object[]{
                        new FindCollisions(
                                new Snapshot.Builder().addParticles(
                                        Stream.concat(
                                                list(10, Experiment.HAWKING).stream(),
                                                list(7, Experiment.EINSTEIN).stream()
                                        ).collect(Collectors.toList())).build()
                        ),
                        2
                },
        };
    }

    private List<DetectedParticle> list(int size, final Experiment experiment) {
        return IntStream.range(0, size)
                .mapToObj(i -> ParticleUtil.build(ParticleType.values()[i % ParticleType.values().length], experiment))
                .collect(Collectors.toList());
    }
}
