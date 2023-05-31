/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.buscadormicroservice.entity.AuthorEntity;

/**
 *
 * @author FABIAN MILLAN
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{
    
}
