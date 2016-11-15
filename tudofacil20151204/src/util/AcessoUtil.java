package util;

//import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class AcessoUtil {

    public static String logIn(String defaultViewId, String login, String perfil) {

        String viewId = defaultViewId;
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute(ControleAcesso.JSF_SECURITY_KEY, "147369");
            session.setAttribute(ControleAcesso.JSF_SESSION_ID, session.getId());
            session.setAttribute(ControleAcesso.LOGGED_USER, login);
            session.setAttribute(ControleAcesso.USER_ROLE, perfil);
            String s = (String)session.getAttribute(ControleAcesso.VIEWID_KEY);
            if (s != null) {
                viewId = s;
            }
        }
        return viewId;
    }

    public static boolean isLoggedIn() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            String s = (String)session.getAttribute(ControleAcesso.JSF_SECURITY_KEY);
            //String sid = (String)session.getAttribute(ControleAcesso.JSF_SESSION_ID);
            if (s != null && s.equals("147369") ) {
                return true;
            }
        }
        return false;
    }

    public static String userLogged() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            String s = (String)session.getAttribute(ControleAcesso.LOGGED_USER);
            return s;
        }
        return null;
    }

    public static String roleLogged() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            String s = (String)session.getAttribute(ControleAcesso.USER_ROLE);
            return s;
        }
        return null;
    }
    public static void logOut() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            session.removeAttribute(ControleAcesso.JSF_SECURITY_KEY);
            session.removeAttribute(ControleAcesso.LOGGED_USER);
            session.removeAttribute(ControleAcesso.USER_ROLE);
            session.removeAttribute(ControleAcesso.VIEWID_KEY);
            session.removeAttribute(ControleAcesso.JSF_SESSION_ID);
        }
    }
}
