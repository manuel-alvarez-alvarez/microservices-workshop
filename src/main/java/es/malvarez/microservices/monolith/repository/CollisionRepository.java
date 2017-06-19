package es.malvarez.microservices.monolith.repository;

import es.malvarez.microservices.monolith.domain.Collision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Repository to store the collisions in a database :)
 */
public interface CollisionRepository extends JpaRepository<Collision, UUID> {

    @Query("select distinct c.snapshot from Collision c where c.when = (select max(c.when) from Collision c)")
    UUID lastSnapshot();

    List<Collision> findAllBySnapshot(UUID snapshot);
}
