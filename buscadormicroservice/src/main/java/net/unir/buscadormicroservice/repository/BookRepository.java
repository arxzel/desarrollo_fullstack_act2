/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unir.buscadormicroservice.entity.BookEntity;

/**
 *
 * @author FABIAN MILLAN
 */
public interface BookRepository extends JpaRepository<BookEntity, Long>{
    
    /*Streamable<BookEntity> findByTitleContaining(String title);
    Streamable<BookEntity> findByResumeContaining(String resume);
    Streamable<BookEntity> findByGenderContaining(String gender);
    Streamable<BookEntity> findByAuthor_fisrtnameContaining(String name);
    Streamable<BookEntity> findByAuthor_lastnameContaining(String name);*/
}
