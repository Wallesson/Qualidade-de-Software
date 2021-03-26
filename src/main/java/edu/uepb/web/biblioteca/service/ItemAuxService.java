package edu.uepb.web.biblioteca.service;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.model.Funcionario;

public class ItemAuxService {
	public boolean atualizarItemAux(Funcionario funcionario) {
		boolean teste = funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR);
		return teste;
	}
	public boolean removerItemAux(Funcionario funcionario) {
		boolean teste = funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR);
		return teste;
	}
}
