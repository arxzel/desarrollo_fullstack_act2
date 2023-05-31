package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;

import net.unir.operadormicroservice.dto.LeanCompleteDto;
import net.unir.operadormicroservice.dto.PrestamoDto;
import net.unir.operadormicroservice.entity.PrestamoEntity;
import net.unir.operadormicroservice.model.LeanOperation;

/**
 * @author Fabian
 *
 */
public interface PrestamoService {

	List<PrestamoEntity> findAll();

	Optional<PrestamoEntity> findById(Long id);

	PrestamoEntity save(PrestamoEntity entity);

	void deleteById(Long id);
	
	LeanOperation leanOperation(LeanOperation leanOperation);

	List<LeanCompleteDto> mapResponseCompleteDto(List<PrestamoDto> prestamoDtos);

}
