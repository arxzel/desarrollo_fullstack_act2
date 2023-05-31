package net.unir.operadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.operadormicroservice.entity.PrestamoEntity;

/**
 * @author Fabian
 *
 */
@Mapper(componentModel = "spring", uses = { ClienteMapper.class })
public interface PrestamoMapper {

	PrestamoMapper INSTANCE = Mappers.getMapper(PrestamoMapper.class);

	PrestamoEntity modelToEntity(PrestamoDto model);

	List<PrestamoEntity> modelListToEntityList(List<PrestamoDto> modelList);

	PrestamoDto entityToModel(PrestamoEntity entity);

	List<PrestamoDto> entityListToModelList(List<PrestamoEntity> entityList);
}
