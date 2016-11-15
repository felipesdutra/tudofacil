package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Cidade;
import model.dao.CidadeDao;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "cidadeMB")
public class CidadeMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968663509271177335L;
	/**
		 * 
		 */

	private String mensagem = "";
	private Cidade cidade = new Cidade();
	private CidadeDao dao = new CidadeDao();

	private List<Cidade> lista = new ArrayList<Cidade>();
	
	public void incluir() {
		if (dao.gravar(cidade) == null)
			this.setMensagem("Erro ao salvar Cidade");
		else
			this.setMensagem("Dados salvos com sucesso.");

	}

	public void onEdit(RowEditEvent event) {
		Cidade cid = ((Cidade) event.getObject());
		if (dao.gravar(cid) == null)
			this.setMensagem("Erro ao salvar Cidade");
		else
			this.setMensagem("Dados salvos com sucesso.");
	}

	public void onCancel(RowEditEvent event) {
		//Não apagar, apenas não realiza ação nenhuma.
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public CidadeDao getDao() {
		return dao;
	}

	public void setDao(CidadeDao dao) {
		this.dao = dao;
	}

	public List<Cidade> getLista() {
		if (lista.isEmpty()) {
			lista = dao.listar();
		}
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
	

}
