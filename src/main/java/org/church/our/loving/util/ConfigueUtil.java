package org.church.our.loving.util;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigueUtil {
	private String filePath;
	private Properties properties = new Properties();
	private Map<String, String> configMap = new HashMap<String, String>();
	
	private static class ConfigureUtilHelper {
		private static final ConfigueUtil CONFIGUE_UTIL = new ConfigueUtil();
	}

	private ConfigueUtil() {
		File configFile = new File(StringUtil.getRootDir() + "/upload/config.properties");
		try {
			if(configFile.exists()) {
				FileReader fReader = new FileReader(configFile);
				properties.load(fReader);
			}
		} catch (Exception e) {
		}
	}
	
	public static ConfigueUtil getInstance() {
		if (null == ConfigureUtilHelper.CONFIGUE_UTIL) {
			return new ConfigueUtil();
		}
		return ConfigureUtilHelper.CONFIGUE_UTIL;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String loadConfig (String key) {
		configMap.size();
		return "";
	}

	public void updateConfig(String key, String value) {
		
	} 
}
