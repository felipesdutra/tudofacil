package model.dao;

import java.io.Serializable;
import java.util.List;

import model.Atribuicao;

public class AtribuicaoDAO extends DaoGenericoImp<Atribuicao, Serializable>{
	
	public Atribuicao gravar(Atribuicao a){
		return super.salvar(a);
	}
	
	public Atribuicao consultar(Long id){
		return super.pesquisarPorId(id);
	}
	
	public List<Atribuicao> listar(){		
		return super.todos();
	}
	
	public List<Atribuicao> listarOrdenado(){
		String sqlQuery = "from Atribuicao order by id, nome";
		
		return super.listPesq(sqlQuery);
	}	
}
