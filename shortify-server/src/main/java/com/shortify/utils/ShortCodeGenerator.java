package com.shortify.utils;

import java.util.Random;

public class ShortCodeGenerator {

	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 6;
	private static final Random random = new Random();

	public static String generate() {
		StringBuilder shortCode = new StringBuilder(CODE_LENGTH);
		
		for (int i = 0; i < CODE_LENGTH; i++) {
			shortCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return shortCode.toString();
	}
}
