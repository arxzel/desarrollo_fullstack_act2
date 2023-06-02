package net.unir.operadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.operadormicroservice.entity.ClientEntity;

/**
 * @author Fabian
 *
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	ClientEntity modelToEntity(ClientDto model);

	List<ClientEntity> modelListToEntityList(List<ClientDto> modelList);

	ClientDto entityToModel(ClientEntity entity);

	List<ClientDto> entityListToModelList(List<ClientEntity> entityList);

}
