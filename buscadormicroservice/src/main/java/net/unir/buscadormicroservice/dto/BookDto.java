/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author FABIAN MILLAN
 */
@Data
@Schema(description = "This model represent a Book data to a secure api rest comunication")
public class BookDto {
    
    @Schema(name = "id", required = false, example = "13", description = "UUID of Book into a system DB")
    private Long id;
    
    @Schema(name = "title", required = false, example = "The ego is the enemy", description = "The book title")
    private String title;
    
    @Schema(name = "resume", required = false, example = "Is a stoic book, about the ego", description = "The book resume")
    private String resume;
    
    @Schema(name = "stockQuantity", required = false, example = "100", description = "The quantity of books in total")
    private Integer stockQuantity;
    
    @Schema(name = "quantityAvailable", required = false, example = "99", description = "The books  rented (not avalaible to read)")
    private Integer quantityAvailable;
    
    @Schema(name = "gender", required = false, example = "Personal Superation", description = "The book gender")
    private String gender;
    
    @Schema(name = "author", required = false, description = "The book author data")
    private AuthorDto author;
}
