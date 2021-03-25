package com.bwl.adn.lib;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ADNConstants {
	public enum BaseNitrogenada {
		A,
		T,
		C,
		G;
		
		private static final List<String> validos = Arrays.stream(values()).map(BaseNitrogenada::name).collect(Collectors.toList());
		
		public static boolean isInValid(String val) {
	        return !validos.contains(val);
	    }
	}
	
	public enum Mutacion {
		AAAA,
		TTTT,
		CCCC,
		GGGG;
		
		public static boolean hasMutacion(String val) {
			return Arrays.stream(values()).map(Mutacion::name).anyMatch(val::contains);
		}
	}
}