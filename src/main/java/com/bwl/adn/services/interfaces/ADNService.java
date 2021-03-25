package com.bwl.adn.services.interfaces;

public interface ADNService {
	Boolean isDNAValid(String[] dna);
	Boolean hasMutation(String[] dna);
	Boolean hasMutationHorizontal(String[] dna);
	Boolean hasMutationVertical(String[] dna);
	Boolean hasMutationOblicuo(String[] dna,Boolean invertido);
}