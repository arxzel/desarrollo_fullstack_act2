/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package net.unir.buscadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.buscadormicroservice.entity.AuthorEntity;

/**
 *
 * @author FABIAN MILLAN
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {
	
	AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    
    AuthorEntity modelToEntity(AuthorDto model);

    List<AuthorEntity> modelListToEntityList(List<AuthorDto> modelList);

    AuthorDto entityToModel(AuthorEntity entity);

    List<AuthorDto> entityListToModelList(List<AuthorEntity> entityList);
}
