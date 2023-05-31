/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.utils;

import net.unir.buscadormicroservice.dto.BookDto;
import net.unir.buscadormicroservice.entity.BookEntity;

/**
 *
 * @author FABIAN MILLAN
 */
public class BookUtils {
    
    private BookUtils(){
        
    }

    public static void mapDtoToentity(BookDto input, BookEntity bookEntity) {
        if(!input.getGender().trim().isEmpty()){
            bookEntity.setGender(input.getGender());
        }
        
        if(!input.getTitle().trim().isEmpty()){
            bookEntity.setTitle(input.getTitle());
        }
        
        if(!input.getResume().trim().isEmpty()){
            bookEntity.setResume(input.getResume());
        }
        
        if(input.getStockQuantity() == 0){
            bookEntity.setStockQuantity(input.getStockQuantity());
        }
        
        if(input.getQuantityAvailable() == 0){
            bookEntity.setQuantityAvailable(input.getQuantityAvailable());
        }
        
    }
    
}
