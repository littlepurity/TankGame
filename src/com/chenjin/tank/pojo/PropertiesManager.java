package com.chenjin.tank.pojo;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private PropertiesManager() {}
	
	private static Properties props = null;
	
	static {
		props = new Properties();
		try {
			props.load(PropertiesManager.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
