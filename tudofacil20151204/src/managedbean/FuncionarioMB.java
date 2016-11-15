package managedbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.RowEditEvent;

import model.Funcionario;
import model.dao.FuncionarioDAO;

@ManagedBean(name = "funcionarioMB")
@ViewScoped
public class FuncionarioMB implements Serializable {

	private static final long serialVersionUID = 1277576843817138444L;

	private String mensagem = "";
	private Funcionario funcionario = new Funcionario();
	private FuncionarioDAO dao = new FuncionarioDAO();

	private int number;

	public void increment() {
		number++;
	}
	
	

	private List<Funcionario> lista = new ArrayList<Funcionario>();

	public int retornaMinutos(Calendar data1, Calendar data2) {
		int minutosAgora = (data1.get(Calendar.MINUTE) + (data1
				.get(Calendar.HOUR_OF_DAY) * 60));
		int minutosEntrada = (data2.get(Calendar.MINUTE) + (data2
				.get(Calendar.HOUR_OF_DAY) * 60));
		int tempoRestante = minutosAgora - minutosEntrada;
		return tempoRestante;
	}

	public FuncionarioMB() {
		System.out.println("numero: "+ getNumber());
		/*
		 * Funcionario f = new Funcionario(); FuncionarioDAO dao = new
		 * FuncionarioDAO(); f = dao.consultar(1L); Calendar c =
		 * Calendar.getInstance(); Calendar c2 = Calendar.getInstance(); //Date
		 * horaEntrada = f.getEntrada(); c2.setTime(f.getEntrada());
		 * 
		 * f.setTempoTotal(retornaMinutos(c, c2)); System.out.println(f);
		 */
		/*
		 * Calendar c = Calendar.getInstance(); Calendar c2 =
		 * Calendar.getInstance(); Date horaEntrada = f.getEntrada();
		 * c2.setTime(horaEntrada); System.out.println("Total de minutos: " +
		 * retornaMinutos(c, c2));
		 */
	}

	public void incluir() {
		if (dao.gravar(funcionario) == null)
			this.setMensagem("Erro ao salvar Funcionário.");
		else
			this.setMensagem("Dados salvos com sucesso.");
	}

	public void onEdit(RowEditEvent event) {
		Funcionario fun = ((Funcionario) event.getObject());
		if (dao.gravar(fun) == null)
			this.setMensagem("Erro ao gravar Funcionário.");
		else
			this.setMensagem("Dados salvos com sucesso.");
	}

	public void onCancel(RowEditEvent event) {
		// Não apagar, apenas não realiza ação nenhuma.
	}

	public String getMensagem() {
		return mensagem;
	}

	private void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getLista() {
/*
		if (lista.isEmpty()) {
			lista = dao.listarOrdenado();
		}
*/
		lista = dao.listarOrdenado();
		for (int i = 0; i < lista.size(); i++) {
			Calendar c = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c2.setTime(lista.get(i).getHoracomputador());

			int varTempoTotal = retornaMinutos(c, c2);

			if(varTempoTotal < 0){
				varTempoTotal = 0;
			}
			
			lista.get(i).setTempoTotal(varTempoTotal);
		
			
		}
		System.out.println(lista.get(0).getTempoTotal());
		

		return lista;
	}

	public FuncionarioDAO getDao() {
		return dao;
	}

	public void setDao(FuncionarioDAO dao) {
		this.dao = dao;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
