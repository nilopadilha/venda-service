package br.com.petronilopadilha.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String Descricao;
	private Date DataColeta;
	private float ValorCompra;
	private float ValorVenda;
	private String UnidadeMedida;
	private String Bandeira;

	public Produto() {

	}

	public Produto( String Descricao, Date DataColeta, Float ValorCompra, Float ValorVenda, String UnidadeMedida,
			String Bandeira) {	

		this.Descricao = Descricao;
		this.DataColeta = DataColeta;
		this.ValorCompra = ValorCompra;
		this.ValorVenda = ValorVenda;
		this.UnidadeMedida = UnidadeMedida;
		this.Bandeira = Bandeira;

	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Date getDataColeta() {
		return DataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		DataColeta = dataColeta;
	}

	public float getValorCompra() {
		return ValorCompra;
	}

	public void setValorCompra(float valorCompra) {
		ValorCompra = valorCompra;
	}

	public float getValorVenda() {
		return ValorVenda;
	}

	public void setValorVenda(float valorVenda) {
		ValorVenda = valorVenda;
	}

	public String getUnidadeMedida() {
		return UnidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		UnidadeMedida = unidadeMedida;
	}

	public String getBandeira() {
		return Bandeira;
	}

	public void setBandeira(String bandeira) {
		Bandeira = bandeira;
	}
	
	
	

}
