package net.unir.operadormicroservice.utils;

import net.unir.operadormicroservice.dto.PrestamoDto;
import net.unir.operadormicroservice.entity.PrestamoEntity;

public class PrestamoUtils {
	
	private PrestamoUtils() {
		
	}
	
	public static void mapDtoToentity(PrestamoDto dto, PrestamoEntity prestamoEntity) {
		if(dto.getLibroId() != null) {
			prestamoEntity.setLibroId(dto.getLibroId());
		}
		if(dto.getPrestamoAt() != null) {
			prestamoEntity.setPrestamoAt(dto.getPrestamoAt());
		}
		if(dto.getDevolutionAt() != null) {
			prestamoEntity.setDevolutionAt(dto.getDevolutionAt());
		}
		prestamoEntity.setDevolution(dto.isDevolution());
	}
}
