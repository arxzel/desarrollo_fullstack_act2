package net.unir.operadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.operadormicroservice.entity.LeanEntity;

/**
 * @author Fabian
 *
 */
@Mapper(componentModel = "spring", uses = { ClientMapper.class })
public interface LeanMapper {

	LeanMapper INSTANCE = Mappers.getMapper(LeanMapper.class);

	LeanEntity modelToEntity(LeanDto model);

	List<LeanEntity> modelListToEntityList(List<LeanDto> modelList);

	LeanDto entityToModel(LeanEntity entity);

	List<LeanDto> entityListToModelList(List<LeanEntity> entityList);
}
