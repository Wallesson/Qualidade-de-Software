package edu.uepb.web.biblioteca.service;

import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Emprestimo;

public class DividaAuxService {
	public Divida auxPagar(Aluno aluno, Emprestimo emprestimo, String dataDevolucao,float saldo) {
		Divida divida = new Divida();
		divida.construtor(aluno, emprestimo, saldo, false);
		return divida;
	}
}
