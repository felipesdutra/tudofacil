package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cidade", schema = "tudofacil")
public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590791709236767446L;
	/**
		 * 
		 */
	@Id
	private Long codigo;
	
	@Column(length=100) 
	private String descricao;
	
	private Long fazenda;
	
	@Column(length=2) 
	private String uf;

	public String getDescricaoUfDropDown(){
		return this.getDescricao() + " - " + this.getUf() ;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getFazenda() {
		return fazenda;
	}

	public void setFazenda(Long fazenda) {
		this.fazenda = fazenda;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fazenda == null) ? 0 : fazenda.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fazenda == null) {
			if (other.fazenda != null)
				return false;
		} else if (!fazenda.equals(other.fazenda))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [codigo=" + codigo + ", descricao=" + descricao
				+ ", fazenda=" + fazenda + ", uf=" + uf + "]";
	}

}
