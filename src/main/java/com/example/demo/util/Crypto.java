package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.Date;

public class Crypto {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	/**
	 * @param myKey
	 */
	private static void setKey(final String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * @param text
	 * @param Hour
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	private static byte[] parseString(String text, Integer Hour) throws UnsupportedEncodingException, ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Date tempDate = cal.getTime();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + Hour);
		String textUUID = text.toString() + "¥" +  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()).toString();
		return textUUID.getBytes("UTF-8");
	}
	
	/** 
	 * @param decode
	 * @return STRING[]
	 */
	private static String decodeSplit (String decode, Integer position) {
		return decode.split("¥")[position];
	}

	/**
	 * @param strToEncrypt
	 * @param secret
	 * @return STRING
	 */
	public static String encrypt(final String strToEncrypt, final String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] base64Bytes =  Base64.encodeBase64(cipher.doFinal(parseString(strToEncrypt, 0)));
			return  new String(base64Bytes).replace("+", "$");
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	/**
	 * @param strToDecrypt
	 * @param secret
	 * @return STRING
	 */
	public static String decrypt( String strToDecrypt, final String secret) {
		try {
			setKey(secret);
			strToDecrypt = strToDecrypt.replace("$", "+");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] message = Base64.decodeBase64(strToDecrypt.getBytes("utf-8"));
			return decodeSplit( new String(cipher.doFinal(message)) , 0 );
			
		} catch (Exception e) {
			System.out.println("E|rror while decrypting: " + e.toString());
		}
		return null;
	}
	
	/**
	 * @param strToEncrypt
	 * @param secret
     * @return NULL | STRING
	 */
	public static String tokenOneHour (final String strToEncrypt, final String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] base64Bytes =  Base64.encodeBase64(cipher.doFinal(parseString(strToEncrypt, 1)));
			return  new String(base64Bytes).replace("+", "$");
		
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	/**
	 * @param strToDecrypt
	 * @param secret
	 * @return NULL | STRING
	 */
	public static String decryptToken ( String strToDecrypt, final String secret) {
		try {
			setKey(secret);
			strToDecrypt = strToDecrypt.replace("$", "+");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] message = Base64.decodeBase64(strToDecrypt.getBytes("utf-8"));
			String timestamp = decodeSplit( new String(cipher.doFinal(message)), 1 );
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			//FECHA ACTUAL
			Date dateNow = cal.getTime();
			//FECHA DE EXPIRACIÓN DEL TOKEN
			Date dateToken = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp).getTime());
			
			if(dateNow.before(dateToken)) {
				return decodeSplit( new String(cipher.doFinal(message)) , 0 ) ;
			}else  return null;
			
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		
		return null;
	}

}
