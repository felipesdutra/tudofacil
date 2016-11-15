package validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validaCPFCNPJ")  
public class ValidaCPFCNPJ implements Validator {
	private  final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private  final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	
	 
     public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
		 if(isValidCPF(String.valueOf(valorTela))|| isValidCNPJ(String.valueOf(valorTela))){
			 
		 }else{
             FacesMessage message = new FacesMessage();
             message.setSeverity(FacesMessage.SEVERITY_ERROR);
             message.setSummary("Número de documento inválido");
             throw new ValidatorException(message);

		 }
/*		 if (!validaCPF(String.valueOf(valorTela))) {
               FacesMessage message = new FacesMessage();
               message.setSeverity(FacesMessage.SEVERITY_ERROR);
               message.setSummary(ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("erro.validacao.cpf"));
               throw new ValidatorException(message);
          }*/
     }
	 
     /**
     * Valida CPF do usuário. Não aceita CPF's padrões como
     * 11111111111 ou 22222222222
     *
     * @param cpf String valor com 11 dígitos
     */
 
     /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
 /*    private static boolean isCPFPadrao(String cpf) {
          if (cpf.equals("11111111111") || cpf.equals("22222222222")
        || cpf.equals("33333333333")
        || cpf.equals("44444444444")
        || cpf.equals("55555555555")
        || cpf.equals("66666666666")
        || cpf.equals("77777777777")
        || cpf.equals("88888888888")
        || cpf.equals("99999999999")) {
 
               return true;
          }
 
      return false;
     }*/
 
     private int calcularDigito(String str, int[] peso) {
         int soma = 0;
         for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
         }
         soma = 11 - soma % 11;
         return soma > 9 ? 0 : soma;
      }
    
      public boolean isValidCPF(String cpf) {
         if ((cpf==null) || (cpf.length()!=11)) return false;
    
         Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
         Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
         return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
      }
    
      public boolean isValidCNPJ(String cnpj) {
         if ((cnpj==null)||(cnpj.length()!=14)) return false;
    
         Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
         Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
         return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
      }




}
