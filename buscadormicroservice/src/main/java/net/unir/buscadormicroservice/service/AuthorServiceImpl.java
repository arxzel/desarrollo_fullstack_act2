/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.buscadormicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.unir.buscadormicroservice.entity.AuthorEntity;
import net.unir.buscadormicroservice.repository.AuthorRepository;

/**
 *
 * @author FABIAN MILLAN
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public List<AuthorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorEntity> findAll(Sort sort) {
        return repository.findAll(sort);
    }
    
    @Override
    public List<AuthorEntity> findByAttributes(AuthorEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public AuthorEntity save(AuthorEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(AuthorEntity entity) {
        repository.delete(entity);
    }
    
}
