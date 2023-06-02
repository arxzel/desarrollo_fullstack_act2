package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;

import net.unir.operadormicroservice.dto.LeanCompleteDto;
import net.unir.operadormicroservice.dto.LeanDto;
import net.unir.operadormicroservice.entity.LeanEntity;
import net.unir.operadormicroservice.model.LeanOperation;

/**
 * @author Fabian
 *
 */
public interface LeanService {

	List<LeanEntity> findAll();

	Optional<LeanEntity> findById(Long id);

	LeanEntity save(LeanEntity entity);

	void deleteById(Long id);
	
	LeanOperation leanOperation(LeanOperation leanOperation);

	List<LeanCompleteDto> mapResponseCompleteDto(List<LeanDto> prestamoDtos);

}
