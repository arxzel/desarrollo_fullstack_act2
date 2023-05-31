/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import net.unir.buscadormicroservice.entity.AuthorEntity;

/**
 *
 * @author FABIAN MILLAN
 */
public interface AuthorService {
    public List<AuthorEntity> findAll();

    public List<AuthorEntity> findAll(Sort sort);
    
    public List<AuthorEntity> findByAttributes(AuthorEntity entity);

    public Optional<AuthorEntity> findById(Long id);

    public AuthorEntity save(AuthorEntity entity);

    public void deleteById(Long id);

    public void delete(AuthorEntity entity);
}
