package managedbean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.bean.ManagedBean;
import javax.management.timer.Timer;

import org.primefaces.context.RequestContext;
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
	int contaTempo = 0;
	int contaTempoIntervalo = 0;

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

	public boolean testeHoraAlmoco(Calendar horaAlmoco) {
		Calendar horaAtual = Calendar.getInstance();

		int horaAtualMin = horaAtual.get(Calendar.MINUTE)
				+ (horaAtual.get(Calendar.HOUR_OF_DAY) * 60);
		int horaAlmocoMin = horaAlmoco.get(Calendar.MINUTE)
				+ (horaAlmoco.get(Calendar.HOUR_OF_DAY) * 60);

		if (horaAtualMin > horaAlmocoMin && horaAtualMin < horaAlmocoMin + 60) {
			return true;
		} else
			return false;
	}

	public Date retornaHoraSaida(Date horaEntrada) {
		Calendar entrada = Calendar.getInstance();
		entrada.setTime(horaEntrada);

		int minutosEnt = (entrada.get(Calendar.MINUTE) + (entrada
				.get(Calendar.HOUR_OF_DAY) * 60));
		int minutosSaida = minutosEnt + 540;
		int horas = minutosSaida / 60;
		int minutos = minutosSaida % 60;

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		try {
			return format.parse(horas + ":" + minutos);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean intervalo(Date horaEntrada) {
		Calendar horaAtual = Calendar.getInstance();

		Calendar entrada = Calendar.getInstance();
		entrada.setTime(horaEntrada);

		int minutosEnt = (entrada.get(Calendar.MINUTE) + (entrada
				.get(Calendar.HOUR_OF_DAY) * 60));
		int horaAtualMin = horaAtual.get(Calendar.MINUTE)
				+ (horaAtual.get(Calendar.HOUR_OF_DAY) * 60);
		
		//intervalo de 10 min a cada 50 min trabalhados
		if (horaAtualMin == minutosEnt + 50
				|| horaAtualMin == minutosEnt + 50 * 2 + 10
				|| horaAtualMin == minutosEnt + 50 * 3 + 10
				|| horaAtualMin == minutosEnt + 50 * 4 + 10
				|| horaAtualMin == minutosEnt + 50 * 5 + 10
				|| horaAtualMin == minutosEnt + 50 * 6 + 10
				|| horaAtualMin == minutosEnt + 50 * 7 + 10
				|| horaAtualMin == minutosEnt + 50 * 8 + 10
				|| horaAtualMin == minutosEnt + 50 * 9 + 10) {
			return true;
		} else
			return false;
	}

	public void alerta() {
		setContaTempo(0);
		setContaTempoIntervalo(0);
	}

	public FuncionarioMB() {
		Date entrada = new Date();
		Date horaEntrada = new Date(entrada.getTime() + Timer.ONE_HOUR - 10);
		System.out.println("Intervalo: " + intervalo(horaEntrada));

	}

	public void incluir() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			funcionario.setHoracomputador(format.parse("00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (dao.gravar(funcionario) == null)
			this.setMensagem("Erro ao salvar Funcionário.");
		else
			this.setMensagem("Dados salvos com sucesso.");
	}

	// int minutosAgora = (data1.get(Calendar.MINUTE) +
	// (data1.get(Calendar.HOUR_OF_DAY) * 60));

	public void onEdit(RowEditEvent event) {
		Funcionario fun = ((Funcionario) event.getObject());
		fun.setHoraSaida(retornaHoraSaida(fun.getEntrada()));
		System.out.println(fun);

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
		 * if (lista.isEmpty()) { lista = dao.listarOrdenado(); }
		 */
		lista = dao.listarOrdenado();
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).setEstaEmIntervalo10Min(false);
			
			Calendar c = Calendar.getInstance(); // inicializa c minutos agora
			Calendar c2 = Calendar.getInstance(); // inicializa c2 minutos

			c2.setTime(lista.get(i).getHoracomputador());
			int varTempoTotal = retornaMinutos(c, c2);
			Calendar cAlmoco = Calendar.getInstance();
			cAlmoco.setTime(lista.get(i).getAlmoco());

			lista.get(i).setEstaEmIntervalo(testeHoraAlmoco(cAlmoco));

			int minutosAlmoco = cAlmoco.get(Calendar.MINUTE)
					+ cAlmoco.get(Calendar.HOUR_OF_DAY) * 60;

			int minutosAgora = c.get(Calendar.MINUTE)
					+ c.get(Calendar.HOUR_OF_DAY) * 60;

			if (minutosAgora > minutosAlmoco) {
				varTempoTotal = varTempoTotal - 60;
			}

			if (varTempoTotal <= 0) {
				varTempoTotal = 0;
			}

			if (varTempoTotal == 360 && getContaTempo() == 0) {
				RequestContext.getCurrentInstance().execute(
						"alert('Tempo de uso do computador excedido: "
								+ lista.get(i).getNome() + "');");
			}

			if (minutosAgora == minutosAlmoco && getContaTempo() == 0) {
				RequestContext.getCurrentInstance().execute(
						"alert('Hora do Almoço: " + lista.get(i).getNome()
								+ "');");
			}

			Date ent = new Date();
			ent = lista.get(i).getEntrada();
			if (intervalo(ent) == true && getContaTempoIntervalo() == 0) {
				lista.get(i).setEstaEmIntervalo10Min(true);
				RequestContext.getCurrentInstance().execute(
						"alert('Hora do intervalo: " + lista.get(i).getNome()
								+ "');");
			}
			setContaTempo(1);
			setContaTempoIntervalo(1);

			lista.get(i).setTempoTotal(varTempoTotal);
		}
		return lista;
	}

	public List<Funcionario> getListaEditar() {
		if (lista.isEmpty()) {
			lista = dao.listarOrdenado();
		}
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

	public int getContaTempo() {
		return contaTempo;
	}

	public void setContaTempo(int contaTempo) {
		this.contaTempo = contaTempo;
	}

	public int getContaTempoIntervalo() {
		return contaTempoIntervalo;
	}

	public void setContaTempoIntervalo(int contaTempoIntervalo) {
		this.contaTempoIntervalo = contaTempoIntervalo;
	}
}
