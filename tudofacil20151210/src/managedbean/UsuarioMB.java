package managedbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import util.AcessoUtil;
import util.ControleSenha;
import model.Funcionario;
import model.Usuario;
import model.dao.UsuarioDAO;

@ManagedBean(name="usuarioMB")
public class UsuarioMB implements Serializable{

	private static final long serialVersionUID = -4350207791581637099L;

	private Usuario usuario = new Usuario();
	private Usuario novoUsuario = new Usuario();
	private String mensagem = "";
	private String url = ""; 
	private String senha ="";
	private boolean logado = false;
	private String repetir = "";
	private UsuarioDAO dao = new UsuarioDAO();
	
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private List<Usuario> lista = new ArrayList<Usuario>();


	public String autenticar()  {
		if (AcessoUtil.isLoggedIn()){
			AcessoUtil.logOut();
		}
		
		String viewId = "sempermissao";
		//usuario = getUsuarioDao().validarCript(usuario);
		usuario = getUsuarioDao().pesquisarPorId(usuario.getLogin());
		if (usuario!=null) {
			setLogado(true);
			//System.out.println("entrou no logado");
			viewId = AcessoUtil.logIn("/welcome.xhtml", usuario.getLogin(), usuario.getPerfil());
		
		}else {
			this.setMensagem("Usuário e/ou senha inválidos!");
			setLogado(false);
			viewId = "";
		}
		//System.out.println("retorno " + viewId);
		return viewId;
	}

	public boolean isLoggedIn() {

        return AcessoUtil.isLoggedIn();
    }
	
	public void onEdit(RowEditEvent event) {
		Usuario usu = ((Usuario) event.getObject());
		
		if(!usu.getSenhaweb().isEmpty()){
			 byte[] senha =ControleSenha.codificaSenha(usu);
			 usu.setSenha(senha);
		}
		Calendar calendar = new GregorianCalendar();  
	    Date date = new Date();  
	    calendar.setTime(date);  
	    Usuario user = new UsuarioDAO().pesquisarPorId(AcessoUtil.userLogged());
	    usu.setUsuariomod(user);
	    usu.setDatamod(date);

		
		if (dao.atualizar(usu) == null)
			this.setMensagem("Erro ao salvar Usuario");
		else
			this.setMensagem("Dados salvos com sucesso.");

	}

	public void trocarSenha(){
		if(novoUsuario.getSenhaweb().equals(repetir)){
			usuario.setLogin(AcessoUtil.userLogged());
			usuario = getUsuarioDao().validarCript(usuario);
			if (usuario!=null) {
				usuario.setSenhaweb(repetir);
				dao.trocarSenha(usuario);
				this.setMensagem("Senha atualizada com sucesso!");
			}else{
				this.setMensagem("Senha atual inválida!");
			}
				
			
		}else{
			this.setMensagem("Senha e repetição da senha diferentes!");
		}
	}

	public void consultar(){
		usuario = getUsuarioDao().pesquisarPorId(usuario.getLogin());
	}
	
	public void onCancel(RowEditEvent event) {
		//Não apagar, apenas não realiza ação nenhuma.
	}

	public void incluir(){
		if (getUsuarioDao().pesquisarPorId(novoUsuario.getLogin()) == null) {
			Calendar calendar = new GregorianCalendar();  
		    Date date = new Date();  
		    calendar.setTime(date);  
		    Usuario user = new UsuarioDAO().pesquisarPorId(AcessoUtil.userLogged());
		    novoUsuario.setAtivo(true);
		    novoUsuario.setUsuarioinclusao(user);
		    novoUsuario.setDatainclusao(date);

			usuarioDao.inserir(novoUsuario);
			novoUsuario = new Usuario();
			this.setMensagem("Usuário salvo com sucesso!");
		}
		else {
			this.setUrl("Usuário já existe, clique aqui para editar");			
		}
			
		
	}
	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}


	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getLista() {
		if (lista.isEmpty()){
			lista = new UsuarioDAO().listar();
		}		
		for(Usuario elemento: lista){  
			   System.out.println(elemento.getLogin() + " - " + elemento.getDatainclusao());  
			}  
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setUsuario(Usuario usuario)	{
		this.usuario = usuario;
	}

	public String logout(){
        AcessoUtil.logOut();
        setMensagem("");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, ("/paginas/login.xhtml" ));
        ctx.addMessage(null, new FacesMessage("Usuário não está mais autenticado."));
        return "";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isLogado() {
		return logado;
	}

	public boolean getLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepetir() {
		return repetir;
	}

	public void setRepetir(String repetir) {
		this.repetir = repetir;
	}


	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
