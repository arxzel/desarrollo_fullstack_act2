package net.unir.operadormicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.unir.operadormicroservice.dto.LeanCompleteDto;
import net.unir.operadormicroservice.dto.PrestamoDto;
import net.unir.operadormicroservice.entity.PrestamoEntity;
import net.unir.operadormicroservice.model.BookModel;
import net.unir.operadormicroservice.model.LeanOperation;
import net.unir.operadormicroservice.repository.PrestamoRepository;

/**
 * @author Fabian
 *
 */
@Service
@Slf4j
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	private PrestamoRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${urls.microservice.buscador}")
	private String buscadorEnpoint;

	@Override
	public List<PrestamoEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<PrestamoEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public PrestamoEntity save(PrestamoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public LeanOperation leanOperation(LeanOperation leanOperation) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LeanOperation> requestEntity = new HttpEntity<>(leanOperation, headers);
        
        ResponseEntity<LeanOperation> response = restTemplate.exchange(
        	String.format(buscadorEnpoint, leanOperation.getBookId()),
            HttpMethod.PATCH,
            requestEntity,
            LeanOperation.class
        );

    	if (response.getStatusCode().is2xxSuccessful()) {
    		LeanOperation patchedResource = response.getBody();
            return patchedResource;
        } else {
        	log.error("Error en: {}", response.getBody());
            return null;
        }
	}

	@Override
	public List<LeanCompleteDto> mapResponseCompleteDto(List<PrestamoDto> prestamoDtos) {
		try {
			List<LeanCompleteDto> completeDtos = new ArrayList<>();
			for(PrestamoDto dto : prestamoDtos) {
				BookModel bookModel = restTemplate.getForObject(String.format(buscadorEnpoint, dto.getLibroId()), BookModel.class);
				LeanCompleteDto leanCompleteDto = LeanCompleteDto.builder()
						.book(bookModel)
						.cliente(dto.getCliente())
						.id(dto.getId())
						.prestamoAt(dto.getPrestamoAt())
						.libroId(dto.getLibroId())
						.devolutionAt(dto.getDevolutionAt())
						.devolution(dto.isDevolution())
						.build();
				completeDtos.add(leanCompleteDto);
			}
	
			return completeDtos;
		} catch(Exception e) {
			log.error("error en la operación: {}", e.getMessage());
			return null;
		}
	}

}