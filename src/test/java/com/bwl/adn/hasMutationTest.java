package com.bwl.adn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bwl.adn.services.interfaces.ADNService;

@SpringBootTest
public class hasMutationTest {
	@Autowired private ADNService service; 
	
	@Test
	public void hasMutationFalse() {
		String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
		Boolean result = service.hasMutation(dna);
		Assertions.assertEquals(false, result);
	}
	
	@Test
	public void hasMutationFalTrue() {
		String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","CCCCTA","TCACTG"};
		Boolean result = service.hasMutation(dna);
		Assertions.assertEquals(true, result);
	}
}
