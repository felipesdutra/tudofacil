package model.dao;

import java.io.Serializable;
import java.util.List;

import model.Funcionario;

public class FuncionarioDAO extends DaoGenericoImp<Funcionario, Serializable>{
	
	public Funcionario gravar(Funcionario f){
		return super.salvar(f);
	}
	
	public Funcionario consultar(Long id){
		return super.pesquisarPorId(id);
	}
	
	public List<Funcionario> Listar(){
		
		return super.todos();
	}
	
	public List<Funcionario> listarOrdenado(){
		String sqlQuery = "from Funcionario order by id, nome";
	
		return super.listPesq(sqlQuery);
	}
}
