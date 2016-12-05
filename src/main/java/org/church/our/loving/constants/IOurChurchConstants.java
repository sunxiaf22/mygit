package org.church.our.loving.constants;

import java.io.File;

import org.church.our.loving.util.StringUtil;

public interface IOurChurchConstants {
	
	public static final String IP_FILE = "IPS.txt";
	public static final String FORWARD_FILE = "FORWARD.txt";
	public static final String NORMAL_DATE_FORMAT = "dd-MM-yyyy HH:mm";
	public static final String OUTPUT_DIR= StringUtil.getRootDir() + File.separator + "upload";

}
