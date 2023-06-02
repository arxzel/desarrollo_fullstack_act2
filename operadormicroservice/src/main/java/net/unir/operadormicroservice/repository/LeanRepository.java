package net.unir.operadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.operadormicroservice.entity.LeanEntity;


/**
 * @author Fabian
 *
 */
public interface LeanRepository extends JpaRepository<LeanEntity, Long> {

}
