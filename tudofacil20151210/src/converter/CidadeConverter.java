package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Cidade;
import model.dao.CidadeDao;

@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {

	CidadeDao dao = new CidadeDao ();

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || Long.valueOf(arg2) == 0) {
			return arg2;
		}
		Cidade cidade = null;
		try {
			Cidade a = new Cidade();
			a.setCodigo(Long.valueOf(arg2));
			cidade = dao.consultar(a.getCodigo());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cidade;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if (value == null || value == "" )
			return "";
		try {
			if (((Cidade) value).getCodigo() == 0)		{
				return "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
			
		Cidade cidade= (Cidade) value;
		return String.valueOf(cidade.getCodigo());
	}

}
