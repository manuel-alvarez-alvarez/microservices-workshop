package es.malvarez.microservices.cqrs;

import es.malvarez.microservices.api.AcceleratorSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * One step more in the right direction, proud of you!
 */
@SpringBootApplication
@ComponentScan({"es.malvarez.microservices.web", "es.malvarez.microservices.cqrs"})
// TODO 2. Enable spring streams for the CQRS/ES Monolith
// @EnableBinding({AcceleratorSink.class, EventStoreProcessor.class})
public class CqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsApplication.class, args);
    }
}
