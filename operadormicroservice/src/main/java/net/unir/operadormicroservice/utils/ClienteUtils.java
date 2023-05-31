package net.unir.operadormicroservice.utils;

import net.unir.operadormicroservice.dto.ClienteDto;
import net.unir.operadormicroservice.entity.ClienteEntity;

public class ClienteUtils {
	
	private ClienteUtils() {
		
	}
	
	public static void mapDtoToentity(ClienteDto dto, ClienteEntity clienteEntity) {
		if(!dto.getDni().trim().isEmpty()) {
			clienteEntity.setDni(dto.getDni());
		}
		if(!dto.getFirstName().trim().isEmpty()) {
			clienteEntity.setFirstName(dto.getFirstName());
		}
		if(dto.getLastName().trim().isEmpty()) {
			clienteEntity.setLastName(dto.getLastName());
		}
		if(dto.getRegisteredAt()!=null) {
			clienteEntity.setRegisteredAt(dto.getRegisteredAt());			
		}
	}
}
