package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import managedbean.FuncionarioMB;

@Entity
@Table(name = "funcionario", schema = "tudofacil")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 7863185821844447725L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String nome;

	@Column
	private Date entrada;

	@Column
	private Date almoco;

	@Column
	private Date horacomputador;

	@Transient
	private int tempoTotal;

	@ManyToOne
	@JoinColumn(name = "atribuicao", referencedColumnName = "id")
	private Atribuicao atribuicao;

	public Funcionario() {
		/*
		 * Calendar c1 = Calendar.getInstance(); Calendar c2 =
		 * Calendar.getInstance(); c2.setTime(this.getEntrada());
		 * 
		 * int minutosAgora = (c1.get(Calendar.MINUTE)
		 * +(c1.get(Calendar.HOUR)*60)); int minutosEntrada =
		 * (c2.get(Calendar.MINUTE) +(c2.get(Calendar.HOUR)*60));
		 */
		// this.tempoTotal = minutosAgora-minutosEntrada;
	}

	public Atribuicao getAtribuicao() {
		return atribuicao;
	}

	public void setAtribuicao(Atribuicao atribuicao) {
		this.atribuicao = atribuicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getAlmoco() {
		return almoco;
	}

	public void setAlmoco(Date almoco) {
		this.almoco = almoco;
	}

	public int getTempoTotal() {
		return tempoTotal;
	}

	public void setTempoTotal(int tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

	public Date getHoracomputador() {
		return horacomputador;
	}

	public void setHoracomputador(Date horacomputador) {
		this.horacomputador = horacomputador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((almoco == null) ? 0 : almoco.hashCode());
		result = prime * result
				+ ((atribuicao == null) ? 0 : atribuicao.hashCode());
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result
				+ ((horacomputador == null) ? 0 : horacomputador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + tempoTotal;
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
		Funcionario other = (Funcionario) obj;
		if (almoco == null) {
			if (other.almoco != null)
				return false;
		} else if (!almoco.equals(other.almoco))
			return false;
		if (atribuicao == null) {
			if (other.atribuicao != null)
				return false;
		} else if (!atribuicao.equals(other.atribuicao))
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (horacomputador == null) {
			if (other.horacomputador != null)
				return false;
		} else if (!horacomputador.equals(other.horacomputador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tempoTotal != other.tempoTotal)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", entrada="
				+ entrada + ", almoco=" + almoco + ", horacomputador="
				+ horacomputador + ", tempoTotal=" + tempoTotal
				+ ", atribuicao=" + atribuicao + "]";
	}

}
