package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validaPeriodo")
public class ValidaPeriodo implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String valor = value.toString();
		/*Integer mes = Integer.parseInt(value.toString().substring(4));*/
		Integer mes = 0;
		if (valor.length() > 4){
			mes = Integer.parseInt(valor.substring(4, 6));
			System.out.println("mes" + mes);
		} else{
			System.out.println("menor!!!!" );
		}
		
		if (mes ==0 || mes > 12){
			System.out.println("mes invalido");
            throw new ValidatorException(new FacesMessage("Mês inválido.")); 
            
        } 
	}

}
