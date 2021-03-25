package com.bwl.adn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bwl.adn.dto.DNARequest;
import com.bwl.adn.dto.DNAResponse;
import com.bwl.adn.services.interfaces.ADNService;

@RestController
public class DNAController {
	@Autowired private ADNService service;
	
	@PostMapping("/mutation")
	public ResponseEntity<DNAResponse> isValid(@RequestBody DNARequest dna) {
		try {
			boolean resultado = service.hasMutation(dna.getDna());
			DNAResponse _response = null;
			if (!resultado)
			{
				_response = new DNAResponse(HttpStatus.OK.value(),"Sin mutación",false);
				return new ResponseEntity<DNAResponse>(_response,HttpStatus.OK);
			}else
			{
				_response = new DNAResponse(HttpStatus.FORBIDDEN.value(),"Con mutación",true);
				return new ResponseEntity<DNAResponse>(_response,HttpStatus.FORBIDDEN);
			}
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
