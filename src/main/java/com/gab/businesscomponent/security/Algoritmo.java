package com.gab.businesscomponent.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Algoritmo {
	public static String converti(String password) {

		try {
			// MD5, compromesso tra performance e sicurezza
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
			StringBuffer buffer = new StringBuffer();
			// il sale dell'algoritmo serve per offuscare l'algoritmo, serve per evitare il
			// reverse enginering
			// deve essere calibrato in base alla lunghezza della password
			// generalmente qualche char in meno rispetto alla lunghezza della password
			String salt = "Rd6$1Np%a2JcX05fzLW";
			for (int i = 0; i < array.length; i++) {
				buffer.append(String.format("%x", array[i]) + salt.toCharArray()[i]);
			}

			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
