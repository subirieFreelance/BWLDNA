package com.bwl.adn.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bwl.adn.exceptions.APIException;
import com.bwl.adn.lib.ADNConstants;
import com.bwl.adn.services.interfaces.ADNService;

@Service
public class ADNServiceImpl implements ADNService{
	@Override
	public Boolean hasMutation(String[] dna) {
		Boolean resultado = true;
		if(dna.length==0) throw new APIException("Secuencia de ADN vacía");
		if(isDNAValid(dna))
		{
			if (!hasMutationHorizontal(dna))
				if(!hasMutationVertical(dna))
					if (!hasMutationOblicuo(dna,false))
						resultado = hasMutationOblicuo(dna, true);
			return resultado;
		}
		throw new APIException("Secuencia de ADN No válida");
	}

	@Override
	public Boolean isDNAValid(String[] dna) {
		List<String> lista = Arrays.asList(dna); 
		Boolean resultado = false;
		for(String item : lista)
		{
			List<String> caracteres = item.chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList());
			List<String> valoresNoPermitidos = caracteres.stream().filter(ADNConstants.BaseNitrogenada::isInValid).map(String::new).collect(Collectors.toList());
			if (valoresNoPermitidos.isEmpty()) resultado = true;
			else
			{
				resultado = false;
				break;
			}
		}
		return resultado;
	}
	
	@Override
	public Boolean hasMutationHorizontal(String[] dna) {
		Boolean resultado = true;
		List<String> lista = Arrays.asList(dna); // Para usar Java 8
		List<String> mutacionesHorizontales = lista.stream().filter(ADNConstants.Mutacion::hasMutacion).map(String::new).collect(Collectors.toList()); 
		if (mutacionesHorizontales.isEmpty()) resultado = false;
		return resultado;
	}
	
	@Override
	public Boolean hasMutationVertical(String[] dna) {
		return hasMutationHorizontal(convertToVertical(dna));
	}
	
	@Override
	public Boolean hasMutationOblicuo(String[] dna,Boolean invertido) {
		return hasMutationHorizontal(convertToOblicuo(dna,invertido));
	}
	
	private String[] convertToVertical(String[] dna) {
		Integer longitud = dna[0].chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList()).size();
		String[] dnaVertical = new String[longitud];
		Integer index = 0;
		List<String> lista = Arrays.asList(dna);
		for(index=0; index<longitud; index++)
		{	
			for(String item : lista)
			{
				List<String> caracteres = item.chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList());
				if (dnaVertical[index]==null) dnaVertical[index] ="";
				dnaVertical[index]=dnaVertical[index].concat(caracteres.get(index));
			}
		}
		return dnaVertical;
	}
	
	private String[] convertToOblicuo(String[] dna, Boolean invertido) {
		Integer longitud = dna[0].chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList()).size();
		Integer iteraciones = longitud -3;
		String[] dnaOblicuo = new String[iteraciones];
		Integer index = 0;
		List<String> lista = Arrays.asList(dna);
		if(!invertido)
		{
			for(index=0;index<iteraciones;index++)
			{
				Integer columna = index;
				for(String item : lista)
				{
					List<String> caracteres = item.chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList());
					if (dnaOblicuo[index]==null) dnaOblicuo[index] ="";
						dnaOblicuo[index]=dnaOblicuo[index].concat(caracteres.get(columna));
						columna++;
						if(columna>longitud-1) break;
				}
			}
		}else {
			Integer posicion = longitud-1; 
			for(index=0;index<iteraciones;index++)
			{
				Integer columna = posicion;
				for(String item : lista)
				{
					List<String> caracteres = item.chars().mapToObj(s -> Character.toString((char)s)).collect(Collectors.toList());
					if (dnaOblicuo[index]==null) dnaOblicuo[index] ="";
						dnaOblicuo[index]=dnaOblicuo[index].concat(caracteres.get(columna));
						columna--;
						if(columna<0) break;
				}
				posicion--;
			}
		}
		return dnaOblicuo;
	}
}
