package es.malvarez.microservices.statistics.readmodel;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import es.malvarez.microservices.statistics.service.StatsService;
import es.malvarez.microservices.ul.ParticleIdentified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Yet another read model
 */
@Component
public class StatisticsReadModel {

    private final StatsService statsService;

    @Autowired
    public StatisticsReadModel(StatsService statsService) {
        this.statsService = statsService;
    }

    @StreamListener(
            value = EventStoreProcessor.INPUT,
            condition = "headers['EVENT_TYPE']=='es.malvarez.microservices.ul.ParticleIdentified'"
    )
    public void onParticleIdentified(final ParticleIdentified event) {
        this.statsService.incrementParticle(event.getType());
    }
}
