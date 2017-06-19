package es.malvarez.microservices.cqrs.service;

import es.malvarez.microservices.api.ParticleType;
import es.malvarez.microservices.cqrs.domain.Collision;
import es.malvarez.microservices.cqrs.domain.Particle;
import es.malvarez.microservices.cqrs.repository.CollisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

/**
 * Service to connect the detector with the web
 */
@Service
public class CollisionService {

    private final CollisionRepository collisionRepository;
    private final EntityManager entityManager;

    @Autowired
    public CollisionService(final CollisionRepository collisionRepository, final EntityManager entityManager) {
        this.collisionRepository = collisionRepository;
        this.entityManager = entityManager;
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

    @Transactional
    public void updateParticle(final UUID particleId, final ParticleType type) {
       Particle particle = entityManager.find(Particle.class, particleId);
       particle.setType(type);
       entityManager.merge(particle);
    }
}