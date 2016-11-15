package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.RowEditEvent;

import model.Atribuicao;
import model.dao.AtribuicaoDAO;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "atribuicaoMB")
public class AtribuicaoMB implements Serializable{

	private static final long serialVersionUID = -6124553483020142823L;
	
	private String mensagem = "";
	private Atribuicao atribuicao = new Atribuicao();
	private AtribuicaoDAO dao = new AtribuicaoDAO();
	
	private List<Atribuicao> lista = new ArrayList<Atribuicao>();
	
	public void incluir(){
		if(dao.gravar(atribuicao) == null)
			this.setMensagem("Erro ao salvar Atribuição.");
		else 
			this.setMensagem("Dados salvos com sucesso.");
	}

	public void onEdit(RowEditEvent event){
		Atribuicao atr = ((Atribuicao) event.getObject());
		if(dao.gravar(atr) == null)
			this.setMensagem("Erro ao gravar Atribuição.");
		else
			this.setMensagem("Dados salvos com sucesso.");
	}
	
	public void onCancel(RowEditEvent event) {
		//Não apagar, apenas não realiza ação nenhuma.
	}
	
	public String getMensagem(){
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Atribuicao getAtribuicao(){
		return atribuicao;
	}
	
	public void setAtribuicao(Atribuicao atribuicao){
		this.atribuicao = atribuicao;
	}
	

	
	public List<Atribuicao> getLista(){
		if(lista.isEmpty()){
			lista = dao.listar();
		}
		return lista;
	}
	
	public AtribuicaoDAO getDao() {
		return dao;
	}

	public void setDao(AtribuicaoDAO dao) {
		this.dao = dao;
	}

	public void setLista(List<Atribuicao> lista) {
		this.lista = lista;
	}

}
