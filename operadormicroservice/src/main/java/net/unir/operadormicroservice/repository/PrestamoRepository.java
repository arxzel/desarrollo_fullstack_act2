package net.unir.operadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.operadormicroservice.entity.PrestamoEntity;


/**
 * @author Fabian
 *
 */
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

}
