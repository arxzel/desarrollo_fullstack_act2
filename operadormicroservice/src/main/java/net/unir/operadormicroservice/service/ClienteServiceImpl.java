package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unir.operadormicroservice.entity.ClienteEntity;
import net.unir.operadormicroservice.repository.ClienteRepository;

/**
 * @author Fabian
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<ClienteEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ClienteEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ClienteEntity save(ClienteEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

}
