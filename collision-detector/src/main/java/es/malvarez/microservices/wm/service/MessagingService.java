package es.malvarez.microservices.wm.service;

import es.malvarez.microservices.api.AcceleratorSink;
import es.malvarez.microservices.api.Snapshot;
import es.malvarez.microservices.wm.command.FindCollisions;
import es.malvarez.microservices.wm.command.FindCollisionsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * This is the Man-in-the-middle orchestrating all the cra... stuff
 */
@Service
public class MessagingService {

    private final FindCollisionsHandler findCollisionsHandler;

    @Autowired
    public MessagingService(final FindCollisionsHandler findCollisionsHandler) {
        this.findCollisionsHandler = findCollisionsHandler;
    }

    @StreamListener(AcceleratorSink.INPUT)
    public void onCollisionFound(final Snapshot snapshot) {
        this.findCollisionsHandler.handle(new FindCollisions(snapshot));
    }
}