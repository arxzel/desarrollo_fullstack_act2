/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package net.unir.buscadormicroservice.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.unir.buscadormicroservice.entity.BookEntity;

/**
 *
 * @author FABIAN MILLAN
 */
@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {
	
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
	
    BookEntity modelToEntity(BookDto model);

    List<BookEntity> modelListToEntityList(List<BookDto> modelList);

    BookDto entityToModel(BookEntity entity);

    List<BookDto> entityListToModelList(List<BookEntity> entityList);
}
