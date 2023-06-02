package net.unir.operadormicroservice.utils;

import net.unir.operadormicroservice.dto.ClientDto;
import net.unir.operadormicroservice.entity.ClientEntity;

public class ClientUtils {
	
	private ClientUtils() {
		
	}
	
	public static void mapDtoToentity(ClientDto dto, ClientEntity clienteEntity) {
		if(!dto.getDni().trim().isEmpty()) {
			clienteEntity.setDni(dto.getDni());
		}
		if(!dto.getFirstName().trim().isEmpty()) {
			clienteEntity.setFirstName(dto.getFirstName());
		}
		if(!dto.getLastName().trim().isEmpty()) {
			clienteEntity.setLastName(dto.getLastName());
		}
	}
}
