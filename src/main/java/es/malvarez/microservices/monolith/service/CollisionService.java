package es.malvarez.microservices.monolith.service;

import es.malvarez.microservices.monolith.domain.Collision;
import es.malvarez.microservices.monolith.repository.CollisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service to connect the detector with the web
 */
@Service
public class CollisionService {

    private final CollisionRepository collisionRepository;

    @Autowired
    public CollisionService(final CollisionRepository collisionRepository) {
        this.collisionRepository = collisionRepository;
    }

    @Transactional(readOnly = true)
    public List<Collision> findAll() {
        return collisionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Collision> findLast() {
        return collisionRepository.findAllBySnapshot(collisionRepository.lastSnapshot());
    }

    @Transactional(readOnly = true)
    public Collision find(final UUID id) {
        return collisionRepository.findOne(id);
    }

    @Transactional
    public void newCollision(final Collision collision) {
        collisionRepository.save(collision);
    }
}
