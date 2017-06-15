package es.malvarez.microservices.collision;

import es.malvarez.microservices.cqrs.EventStoreProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * And this is the application for our very first microservice :)
 */
@SpringBootApplication
@EnableBinding(EventStoreProcessor.class)
@EnableDiscoveryClient
@ComponentScan({"es.malvarez.microservices.web", "es.malvarez.microservices.collision"})
public class CollisionReadModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollisionReadModelApplication.class, args);
    }

}
