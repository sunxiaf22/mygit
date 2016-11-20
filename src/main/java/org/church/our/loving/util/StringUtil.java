package org.church.our.loving.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParsePosition;

import org.church.our.loving.constants.IOurChurchConstants;

public class StringUtil {

    public static final String DATE_FORMAT_FULL_MONTH = "dd MMMMM yyyy";
    public static final String DATE_FORMAT_SLASH = "dd/MM/yyyy";
    public static final String DATE_FORMAT_SESCOND = "yyyy-MM-dd HH:mm:ss";
    private static final Logger logger = new Logger(1);

    /**
     * 
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        if (isEmpty(filePath)) {
            return "";
        }
        try {
            return readInputStream(new FileInputStream(new File(filePath)));
        } catch (FileNotFoundException e) {
            logger.error("Read file" + filePath + " encounters an exception: "
                    + e.getMessage(), e);
        }
        return "";
    }

    /**
     * 
     * @param is
     * @return
     */
    public static String readInputStream(InputStream is) {
        if (is == null) {
            return "";
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            logger.error("IOException occured in readInputStream", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("IOException occured in readInputStream", e);
            }
        }
        return buffer.toString();
    }

    /**
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if ((str == null) || (str.trim().length() == 0)) {
            return true;
        }

        return false;
    }

    /**
     * 
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 
     * @param date
     * @param format
     * @return
     */
    public static String formateDateToString(Date date, String format) {
        String rv = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            rv = sdf.format(date);
        }
        return rv;
    }

    /**
     * 
     * @param inputStr
     * @param oldValue
     * @param newValue
     * @return
     */
    public static String replaceNewLine(String inputStr, String oldValue,
            String newValue) {
        String patternStr = oldValue;
        String replaceStr = newValue.replaceAll("\\\\", "\\\\\\\\").replaceAll(
                "\\$", "\\\\\\$"); // spec sign '$' and '\' in newValue
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.replaceAll(replaceStr);
    }

    /**
     * 
     * @param str
     * @return
     */
    public static String deNull(String str) {
        if (str == null) {
            return "";
        }

        return str;
    }

    public static String linkUrlProcess(String url) {
        url = deNull(url).trim();
        if ("".equals(url) || "#".equals(url)) {
            return "javascript:void(0);";
        } else {
        	if(url.toLowerCase().contains("/content/dam/")){
            	return url;
            }else if (url.toLowerCase().startsWith("/content/")) {
                int anchorPos = url.indexOf("#");
		        String anchor = "";
		        if (anchorPos > 0) {
		          anchor = url.substring(anchorPos, url.length());
		          url = url.substring(0, anchorPos);
		        }

		        int extSepPos = url.lastIndexOf(".");
		        int slashPos = url.lastIndexOf("/");
		        if ((extSepPos <= 0) || (extSepPos < slashPos)) {
		        	url = url + ".html" + anchor;
		        }else {
					int extHtml = url.lastIndexOf(".html");
		        	if(extHtml < 0){
		        		url = url + ".html" + anchor;
		        	}else{
		        		url = url + anchor;
		        	}
		        }
	            return url;
            }
            
            if (url.toLowerCase().startsWith("javascript:")) {
                return url;
            }
            
            if (url.toLowerCase().startsWith("#") && url.length() > 1) {
                return url;
            }
            
            if (url.toLowerCase().startsWith("mailto:")) {
                return url;
            }
            
            if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
                return url;
            } else {
                return "http://" + url;
            }
        }
    }

    public static Date toDate(String strDateTime, String dateTimeFormat) {
        if ((strDateTime == null) || (strDateTime.length() == 0)
                || (dateTimeFormat == null) || (dateTimeFormat.length() == 0)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        Date date = dateFormat.parse(strDateTime, new ParsePosition(0));

        if (date == null) {
            return null;
        }

        return date;
    }

    /**
     * Check the input string whether it is a number or not.
     * 
     * @param inputStr
     *            , The string to be checked.
     * @return boolean value ,If the input string is a number then return true
     *         else return false.
     */
    public static boolean isNumeric(String inputStr) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(inputStr);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    
	 public static boolean isANumber(String inputStr) {
	        boolean isNumber =false;
	        try {
				Double.parseDouble(inputStr);
				isNumber = true;
			} catch (Exception e) {
				isNumber = false;
			}
	        return isNumber;
	    }
	 
	 public static String getRootDir () {
		 return System.getProperty("user.dir");
	 }
	 
	 public static String getLatestIP () {
		 String latestIP = "";
		 File ipFile = new File(IOurChurchConstants.IP_FILE);
		 try {
			 if (!ipFile.exists()) {
				 ipFile.createNewFile();
			 }
			 List<String> ipLists = FileUtils.getFileContentList(ipFile);
			 if (null != ipLists && ipLists.size() > 0)
			 latestIP = ipLists.get(ipLists.size() - 1).split("\\|")[0].trim();
			
		} catch (Exception e) {
			 logger.error("Exception occured in getLatestIP", e);
		}
		return latestIP;
	 }
	 
	 public static String getForwardLink() {
		 String forwardLink = "";
		 File forwardFile = new File(IOurChurchConstants.FORWARD_FILE);
		 try {
			 List<String> linkLists = FileUtils.getFileContentList(forwardFile);
			 if (null != linkLists) forwardLink = linkLists.get(0);
		} catch (Exception e) {
			 logger.error("Exception occured in getForwardLink", e);
		}
		 return forwardLink;
	 }
}
