package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuario",schema="tudofacil")
public class Usuario implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8986912056573551539L;

	@Id	
	private String login;
	
	@Column(length = 60)
	private String nome;

	@Column(length = 15)
	private String perfil;
	
	private byte[] senha;	

	@Transient
	private String senhaweb;

	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="usuarioinclusao", referencedColumnName = "login")
	private Usuario usuarioinclusao;
	
	private Date datainclusao;

	@ManyToOne
	@JoinColumn(name="usuariomod", referencedColumnName = "login")
	private Usuario usuariomod;
	
	private Date datamod;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return perfil;
	}
	
	public String getPerfilString() {
		if (getPerfil().equalsIgnoreCase("ROLE_USER")) return "Sad";
		else if (getPerfil().equalsIgnoreCase("ROLE_MANAGER")) return "Financeiro";
		else if (getPerfil().equalsIgnoreCase("ROLE_COMPRAS")) return "Compras";
		else return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public byte[] getSenha() {
		return senha;
	}

	public void setSenha(byte[] senha) {
		this.senha = senha;
	}

	
	
	public String getSenhaweb() {
		return senhaweb;
	}

	public void setSenhaweb(String senhaweb) {
		this.senhaweb = senhaweb;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuarioinclusao() {
		return usuarioinclusao;
	}

	public void setUsuarioinclusao(Usuario usuarioinclusao) {
		this.usuarioinclusao = usuarioinclusao;
	}

	public Date getDatainclusao() {
		return datainclusao;
	}

	public void setDatainclusao(Date datainclusao) {
		this.datainclusao = datainclusao;
	}

	public Usuario getUsuariomod() {
		return usuariomod;
	}

	public void setUsuariomod(Usuario usuariomod) {
		this.usuariomod = usuariomod;
	}

	public Date getDatamod() {
		return datamod;
	}

	public void setDatamod(Date datamod) {
		this.datamod = datamod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result
				+ ((datainclusao == null) ? 0 : datainclusao.hashCode());
		result = prime * result + ((datamod == null) ? 0 : datamod.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + Arrays.hashCode(senha);
		result = prime * result
				+ ((usuarioinclusao == null) ? 0 : usuarioinclusao.hashCode());
		result = prime * result
				+ ((usuariomod == null) ? 0 : usuariomod.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (datainclusao == null) {
			if (other.datainclusao != null)
				return false;
		} else if (!datainclusao.equals(other.datainclusao))
			return false;
		if (datamod == null) {
			if (other.datamod != null)
				return false;
		} else if (!datamod.equals(other.datamod))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (!Arrays.equals(senha, other.senha))
			return false;
		if (usuarioinclusao == null) {
			if (other.usuarioinclusao != null)
				return false;
		} else if (!usuarioinclusao.equals(other.usuarioinclusao))
			return false;
		if (usuariomod == null) {
			if (other.usuariomod != null)
				return false;
		} else if (!usuariomod.equals(other.usuariomod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", nome=" + nome + ", perfil="
				+ perfil + ", ativo="
				+ ativo + ", usuarioinclusao=" + usuarioinclusao
				+ ", datainclusao=" + datainclusao + ", usuariomod="
				+ usuariomod + ", datamod=" + datamod + "]";
	}

	

}
