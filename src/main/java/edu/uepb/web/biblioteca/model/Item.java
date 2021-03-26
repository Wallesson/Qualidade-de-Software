package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoAnais;
import edu.uepb.web.biblioteca.enums.TipoItem;
import edu.uepb.web.biblioteca.enums.TipoMidia;
import edu.uepb.web.biblioteca.enums.TipoTrabalhoConclusao;

/**
 * A classe POJO do Item
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class Item {
	int id;
	private TipoItem tipoItem;
	private String isbn;
	private String titulo;
	TipoAnais tipoAnais;
	TipoMidia tipoMidia;
	TipoTrabalhoConclusao tipoTrabalho;
	private String autor;
	private String congresso;
	private String anoPublicacao;
	private String local;
	private String editora;
	private String edicao;
	private int numeroPagina;
	private String area;
	private String tema;
	private String dataGravacao;
	private String orientador;
	private String data;
	private int quantidade;

	public Item() {

	}

	
	public void construtor(TipoItem tipoItem, String titulo, String data) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = TipoItem.valueOf(tipoItem);
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoAnais getTipoAnais() {
		return tipoAnais;
	}

	public void setTipoAnais(String tipo) {
		this.tipoAnais = TipoAnais.valueOf(tipo);
	}

	public void setTipoAnais(TipoAnais tipoAnais) {
		this.tipoAnais = tipoAnais;
	}

	public TipoMidia getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(String tipoMidia) {
		this.tipoMidia = TipoMidia.valueOf(tipoMidia);
	}

	public void setTipoMidia(TipoMidia tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public TipoTrabalhoConclusao getTipoTrabalho() {
		return tipoTrabalho;
	}

	public void setTipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = TipoTrabalhoConclusao.valueOf(tipoTrabalho);
	}

	public void setTipoTrabalho(TipoTrabalhoConclusao tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCongresso() {
		return congresso;
	}

	public void setCongresso(String congresso) {
		this.congresso = congresso;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(String dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		ItemAux aux = new ItemAux(); 
		return aux.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		ItemAux aux = new ItemAux();
		aux.equals(obj);
		Item other = (Item) obj;
		return true;
	}

	@Override
	public String toString() {
		ItemAux aux = new ItemAux();
		return aux.toString();
	}

}
