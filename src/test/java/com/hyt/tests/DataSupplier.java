package com.hyt.tests;

import org.testng.annotations.DataProvider;

public class DataSupplier {
	@DataProvider(indices = { 0, 2 })
	public String[][] loginData() {
		String[][] data = new String[3][2];
		data[0][0] = "anand.lorzi@gmail.com";
		data[0][1] = "admin123";

		data[1][0] = "anand.lorzi@gmail.com";
		data[1][1] = "admin1234";

		data[2][0] = "abc@gmail.com";
		data[2][1] = "admin123";

		return data;
	}
}
