package com.bwl.adn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bwl.adn.services.interfaces.ADNService;

@SpringBootTest
public class isDNAValidTest {
	@Autowired private ADNService service; 
	
	@Test
	public void testDNAValid() {
		String[] dna = {"ATGCGA"};
		Boolean result = service.isDNAValid(dna);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	public void testDNAInvalid() {
		String[] dna = {"XTGCGA"};
		Boolean result = service.isDNAValid(dna);
		Assertions.assertEquals(false, result);
	}
}
