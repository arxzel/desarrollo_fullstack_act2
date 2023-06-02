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

import net.unir.buscadormicroservice.entity.BookEntity;
import net.unir.buscadormicroservice.model.LeanOperation;
import net.unir.buscadormicroservice.model.LeanOperation.LeanOperationType;
import net.unir.buscadormicroservice.repository.BookRepository;

/**
 *
 * @author FABIAN MILLAN
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<BookEntity> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookEntity> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookEntity> findByAttributes(BookEntity entity) {
		/*
		 * Streamable<BookEntity> result =
		 * repository.findByTitleContaining(entity.getTitle())
		 * .and(repository.findByResumeContaining(entity.getResume()))
		 * .and(repository.findByGenderContaining(entity.getGender()))
		 * .and(repository.findByAuthor_fisrtnameContaining(entity.getAuthor().
		 * getFirstname()))
		 * .and(repository.findByAuthor_lastnameContaining(entity.getAuthor().
		 * getLastname())); return result.toList();
		 */
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BookEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public BookEntity save(BookEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(BookEntity entity) {
		repository.delete(entity);
	}

	@Override
	@Transactional
	public LeanOperation leanOperationBook(LeanOperation operation) {
		Optional<BookEntity> bookOp = repository.findById(operation.getBookId());
		if (!bookOp.isPresent()) {
			return null;
		}
		BookEntity book = bookOp.get();
		Integer quantity = book.getQuantityAvailable();
		if (operation.getType().equals(LeanOperationType.RENT)) {
			if (book.getQuantityAvailable() == 0) {
				operation.setSuccess(false);
				operation.setMessage("Sorry, there are not available books to reent in this momment");
				return operation;
			}
			quantity++;
		} else if (operation.getType().equals(LeanOperationType.RETURN)) {
			quantity--;
		} else {
			operation.setSuccess(false);
			operation.setMessage("ERROR: Operation RENT/RETURN was not found");
			return operation;
		}

		book.setQuantityAvailable(quantity);
		repository.saveAndFlush(book);
		operation.setSuccess(true);
		operation.setMessage("Rented success");

		return operation;

	}

}
