package com.restful.booker.utils;

import java.util.Random;

public class PropertyReader {

	public static String getRandomValue(){
		Random random = new Random();
		int randomInt = random.nextInt(100000);
		return Integer.toString(randomInt);
	}
}
