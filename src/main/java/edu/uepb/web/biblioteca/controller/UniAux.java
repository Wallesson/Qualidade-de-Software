package edu.uepb.web.biblioteca.controller;

import edu.uepb.web.biblioteca.service.UniversidadeService;

public class UniAux {
	public boolean teste() {
		UniversidadeService universidadeService = null;
		boolean teste = universidadeService.getUniversidade() != null;
		return teste;
	}
}
