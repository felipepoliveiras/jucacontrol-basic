package br.senai.sp.info.pweb.jucacontrol.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "categoria_id")
	private CategoriaOcorrencia categoria;
	
	@ManyToOne
	@JoinColumn(name = "emissor_id", nullable = false)
	@NotNull
	private Usuario emissor;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id", nullable = true)
	private Usuario tecnico;
	
	@Column(length = 30, nullable = false, unique = false)
	@Size(min = 1, max = 30)
	@NotNull
	private String titulo;
	
	@Column(nullable = false, unique = false)
	@Lob
	@NotNull
	private String descricao;
	
	@NotNull
	private Boolean concluido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getEmissor() {
		return emissor;
	}

	public void setEmissor(Usuario emissor) {
		this.emissor = emissor;
	}

	public Usuario getTecnico() {
		return tecnico;
	}

	public void setTecnico(Usuario tecnico) {
		this.tecnico = tecnico;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

}
