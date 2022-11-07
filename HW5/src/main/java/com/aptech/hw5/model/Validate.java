package com.aptech.hw5.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bouncycastle.util.encoders.Hex;

public class Validate {
	public static boolean isName(String name) {
		String regex = "[a-zA-Z\\s]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name.trim());
		return m.matches() ? true : false;
	}

	public static boolean isNumber(String number) {
		String regex = "[0-9]+.?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(number.trim());
		return m.matches() ? true : false;
	}

	public static boolean isId(String id) {
		return id.length() != 6 ? false : true;
	}

	public static String sha_256(String txt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(txt.getBytes(StandardCharsets.UTF_8));
			String sha256hex = new String(Hex.encode(hash));
			return sha256hex;
		} catch (Exception ex) {
			ex.getMessage();
			return null;
		}
	}
}
