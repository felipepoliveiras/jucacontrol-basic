package br.senai.sp.info.pweb.jucacontrol.core;

public enum PesquisaOcorrencias {
	TODOS("Todos"), 
	ABERTO("Abertos"), 
	AGUARDANDO("Aguardando atendimento"), 
	EM_ATENDIMENTO("Em atendimento"), 
	ENCERRADOS("Encerrados")
	;
	
	String descricao;
	
	private PesquisaOcorrencias(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getValor() {
		return this.name();
	}
}
