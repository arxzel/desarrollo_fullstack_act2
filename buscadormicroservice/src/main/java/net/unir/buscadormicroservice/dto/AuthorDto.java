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
@Schema(description = "This model represent a Author data to a secure api rest comunication")
public class AuthorDto {
    
    @Schema(name = "id", required = false, example = "102", description = "UUID of Author into a system DB")
    private Long id;
    
    @Schema(name = "firstname", required = true, example = "Ryan", description = "the author's first name")
    private String firstname;
    
    @Schema(name = "lastname", required = true, example = "Holiday", description = "the author's last name")
    private String lastname;
}
