package model.dao;

import java.io.Serializable;
import java.util.List;

import model.Cidade;


public class CidadeDao extends DaoGenericoImp<Cidade, Serializable>{
	

	public Cidade gravar(Cidade a) {
		return super.salvar(a);
	}

	public Cidade consultar(Long codigo) {
		return super.pesquisarPorId(codigo);
	}
	
	public List<Cidade> listar() {
		return super.todos();
	}
	
	public List<Cidade> listarOrdenado(){
		
		String sqlQuery  = "from Cidade order by uf, descricao" ;

		return  super.listPesq(sqlQuery);
	

	}
	public List<Cidade> listarRSOrdenado(){
		
		String sqlQuery  = "from Cidade where uf = 'RS' order by descricao" ;

		return  super.listPesq(sqlQuery);

	}
	
}
