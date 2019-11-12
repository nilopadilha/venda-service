package br.com.petronilopadilha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String regiaoSigla;
	private String estadoSigla;
	private String municipio;

	public Estado() {

	}

	public Estado(String regiaoSigla, String estadoSigla, String municipio) {
		this.regiaoSigla = regiaoSigla;
		this.estadoSigla = estadoSigla;
		this.municipio = municipio;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegiaoSigla() {
		return regiaoSigla;
	}

	public void setRegiaoSigla(String regiaoSigla) {
		this.regiaoSigla = regiaoSigla;
	}

	public String getEstadoSigla() {
		return estadoSigla;
	}

	public void setEstadoSigla(String estadoSigla) {
		this.estadoSigla = estadoSigla;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	
}
