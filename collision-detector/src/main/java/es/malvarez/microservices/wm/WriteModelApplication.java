package es.malvarez.microservices.wm;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.cqrs.EventStoreProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * This is the APP where our write models live
 */
@SpringBootApplication
@EnableBinding({AcceleratorSink.class, EventStoreProcessor.class})
public class WriteModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(WriteModelApplication.class, args);
    }
}
