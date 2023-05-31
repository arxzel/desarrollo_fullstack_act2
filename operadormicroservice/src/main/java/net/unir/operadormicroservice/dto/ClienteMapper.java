package net.unir.operadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.operadormicroservice.entity.ClienteEntity;

/**
 * @author Fabian
 *
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {

	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

	ClienteEntity modelToEntity(ClienteDto model);

	List<ClienteEntity> modelListToEntityList(List<ClienteDto> modelList);

	ClienteDto entityToModel(ClienteEntity entity);

	List<ClienteDto> entityListToModelList(List<ClienteEntity> entityList);

}
