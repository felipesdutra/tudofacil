package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Atribuicao;
import model.dao.AtribuicaoDAO;


@FacesConverter(value = "atribuicaoConverter")
public class AtribuicaoConverter implements Converter {

	AtribuicaoDAO dao = new AtribuicaoDAO();

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || Long.valueOf(arg2) == 0) {
			return arg2;
		}
		Atribuicao atribuicao = null;
		try {
			Atribuicao a = new Atribuicao();
			a.setId(Long.valueOf(arg2));
			atribuicao = dao.consultar(a.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return atribuicao;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if (value == null || value == "" || value=="0" )
			return "";
		try {
			if (((Atribuicao) value).getId() == 0)		{
				return "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
			
		Atribuicao atribuicao = (Atribuicao) value;
		return String.valueOf(atribuicao.getId());
	}

}
