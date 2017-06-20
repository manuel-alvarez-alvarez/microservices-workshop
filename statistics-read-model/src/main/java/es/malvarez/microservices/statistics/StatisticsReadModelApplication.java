package es.malvarez.microservices.statistics;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * Some state coming in
 */
@SpringBootApplication
@EnableBinding(EventStoreProcessor.class)
@EnableDiscoveryClient
@ComponentScan({"es.malvarez.microservices.web", "es.malvarez.microservices.statistics"})
public class StatisticsReadModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsReadModelApplication.class, args);
    }

}
