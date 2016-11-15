package util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import model.Usuario;

public class ControleSenha {
	
	private static final String ALGORITHM       = "DES/ECB/PKCS5Padding";

	public static byte[] codificaSenha(Usuario u){
		
		byte[] textoEncriptado = null;
		try {

			SecretKey myDesKey = chave();
			
			Cipher desCipher;

			desCipher = Cipher.getInstance(ALGORITHM);

			desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

			byte[] text = u.getSenhaweb().getBytes();

			textoEncriptado = desCipher.doFinal(text);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return textoEncriptado;
		
	}
	
	
	private static SecretKey chave() throws Exception {
		byte[] keyAsBytes;
		keyAsBytes = myEncryptionKey.getBytes();
		SecretKey key = new SecretKeySpec(keyAsBytes,  "DES");
		return key;
		
	}
	
	
	
	
	
	
	
	
	private static final String myEncryptionKey = "@t3ndimT";
}
