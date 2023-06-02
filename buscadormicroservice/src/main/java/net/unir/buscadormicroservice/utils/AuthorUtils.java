/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.utils;

import net.unir.buscadormicroservice.dto.AuthorDto;
import net.unir.buscadormicroservice.entity.AuthorEntity;

/**
 *
 * @author FABIAN MILLAN
 */
public class AuthorUtils {

    public static void mapDtoToentity(AuthorDto input, AuthorEntity authorEntity) {
        if(!input.getFirstname().trim().isEmpty()){
            authorEntity.setFirstname(input.getFirstname());
        }
        
        if(!input.getLastname().trim().isEmpty()){
            authorEntity.setFirstname(input.getLastname());
        }
    }
    
}
