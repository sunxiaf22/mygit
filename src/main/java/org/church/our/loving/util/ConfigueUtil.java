package org.church.our.loving.util;

import java.util.HashMap;
import java.util.Map;

public class ConfigueUtil {
	private String filePath;
	private Map<String, String> configMap = new HashMap<String, String>();

	public ConfigueUtil() {
		super();
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String loadConfig (String key) {
		
		return "";
	}

	public void updateConfig(String key, String value) {
		
	} 
}
