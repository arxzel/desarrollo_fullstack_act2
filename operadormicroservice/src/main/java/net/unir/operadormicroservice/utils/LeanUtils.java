package net.unir.operadormicroservice.utils;

import net.unir.operadormicroservice.dto.LeanDto;
import net.unir.operadormicroservice.entity.LeanEntity;

public class LeanUtils {
	
	private LeanUtils() {
		
	}
	
	public static void mapDtoToentity(LeanDto dto, LeanEntity prestamoEntity) {
		if(dto.getBookId() != null) {
			prestamoEntity.setBookId(dto.getBookId());
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
