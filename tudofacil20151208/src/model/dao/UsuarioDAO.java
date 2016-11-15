package model.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ControleSenha;

import model.Usuario;


public class UsuarioDAO  extends DaoGenericoImp<Usuario, Serializable> {		
	
	public Usuario validarCript(Usuario usuario) {
		
		String sqlQuery  = "from Usuario " +
							"where login = :login" +
							" and  senha = :senha " +
							" and  ativo = true " ;
		
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", usuario.getLogin());
		parameters.put("senha", ControleSenha.codificaSenha(usuario));
		
		return super.pesqParam(sqlQuery, parameters);
		
	}
	
	
	public Usuario inserir(Usuario usuario) {
		
		if (usuario !=null){
			 byte[] senha =ControleSenha.codificaSenha(usuario);
			 usuario.setSenha(senha);
		}
		super.atualizar(usuario);
		return usuario;

	}

	public Usuario trocarSenha(Usuario usuario) {
		
		if (usuario !=null){
			 byte[] senha =ControleSenha.codificaSenha(usuario);
			 usuario.setSenha(senha);
		}
		super.atualizar(usuario);
		return usuario;

	}
	public List<Usuario> listar() {
		return super.todos();
	}
	
}
