/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import net.unir.buscadormicroservice.entity.BookEntity;
import net.unir.buscadormicroservice.model.LeanOperation;

/**
 *
 * @author FABIAN MILLAN
 */
public interface BookService {
    public List<BookEntity> findAll();

    public List<BookEntity> findAll(Sort sort);
    
    public List<BookEntity> findByAttributes(BookEntity entity);

    public Optional<BookEntity> findById(Long id);

    public BookEntity save(BookEntity entity);

    public void deleteById(Long id);

    public void delete(BookEntity entity);

	public LeanOperation leanOperationBook(LeanOperation operation);
}
