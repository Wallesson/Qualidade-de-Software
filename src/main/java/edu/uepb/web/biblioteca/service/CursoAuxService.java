package edu.uepb.web.biblioteca.service;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Funcionario;

public class CursoAuxService {
	private static Logger logger = Logger.getLogger(CursoService.class);
	private int id;
	public int getId() {
		return id;
	}
	public void aux(Funcionario funcionario) throws AutenticacaoException {
		logger.error("Funcionario nao autorizado, idFuncionario: " + getId());
		throw new AutenticacaoException("Este funcionario nao esta autorizado");
	}
}
