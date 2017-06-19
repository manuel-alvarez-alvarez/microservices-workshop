package es.malvarez.microservices.monolith;

import es.malvarez.microservices.api.AcceleratorSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * Behold your MONOlith!!!
 */
@SpringBootApplication
@ComponentScan({"es.malvarez.microservices.web", "es.malvarez.microservices.monolith"})
// TODO 2. Enable spring streams for the Monolith
// @EnableBinding(AcceleratorSink.class)
public class MonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
    }

}
