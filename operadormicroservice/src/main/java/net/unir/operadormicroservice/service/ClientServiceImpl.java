package net.unir.operadormicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unir.operadormicroservice.entity.ClientEntity;
import net.unir.operadormicroservice.repository.ClientRepository;

/**
 * @author Fabian
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	@Override
	public List<ClientEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ClientEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ClientEntity save(ClientEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

}
