package org.church.our.loving.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	private static final Logger logger = new Logger(1);
	public static List<String> getFileContentList(File sourceFile) throws Exception {
		PrintStream pso = null;
		PrintStream pse = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> contentList = new ArrayList<String>();
		try {
			
			if (null == sourceFile || !sourceFile.exists())
				throw new Exception("no file found error!");
			
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			String newLine = "";
			while ((newLine = br.readLine()) != null) {
					contentList.add(newLine + " ");
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			if (null != pso)
				pso.close();
			if (null != pse)
				pse.close();
			try {
				fr.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			try {
				br.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return contentList;
	}
	
	public static String getFileContentString(File sourceFile, boolean isSQL) throws Exception {
		PrintStream pso = null;
		PrintStream pse = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer contentList = new StringBuffer();
		try {
			
			if (null == sourceFile || !sourceFile.exists())
				throw new Exception("no file found error!");
			
			fr = new FileReader(sourceFile);
			br = new BufferedReader(fr);
			String newLine = "";
			while ((newLine = br.readLine()) != null) {
				if (isSQL) {
					if (!newLine.trim().startsWith("--")) {
						newLine = newLine.replace(";", "").trim();
						contentList.append(" " + newLine + " \n");
					}
				}else
					contentList.append(newLine);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			try {
				if (null != pso)
				pso.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			try {
				if (null != pse)
				pse.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			try {
				fr.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			
			try {
				if (null != br)
				br.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return contentList.toString();
	}
	
	public static File writeToFile(String fileName, String content) throws Exception {
		File outputFile = new File(fileName);
		logger.debug("Start to writing data to file: " + fileName);
		FileWriter fWriter = null;
		BufferedWriter bWriter = null;
		try {
			if (!outputFile.exists()) {
				if (null != outputFile.getParentFile() && !outputFile.getParentFile().exists())
				outputFile.getParentFile().mkdirs();
				
				boolean isCreated = outputFile.createNewFile();
				logger.debug("The new file was created successfully? " + isCreated + " -- " + outputFile.getAbsolutePath());
			}
			fWriter = new FileWriter(outputFile.getAbsolutePath());
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(content);
			bWriter.flush();
			bWriter.close();
			logger.debug("File output successfully [" + outputFile.getAbsolutePath() + "].");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		} finally {
			if (null != bWriter) {
				try {
					bWriter.close();
					bWriter = null;
				} catch (Exception e2) {
					logger.error(e2.getMessage(),e2);
				}
			}
			if (null != fWriter) {
				try {
					fWriter.close();
					fWriter = null;
				} catch (Exception e3) {
					logger.error(e3.getMessage(),e3);
				}
			}
		}
		return outputFile;
	}
	
	public static File writeToFile(String fileName, String content, boolean append) throws Exception {
		File outputFile = new File(fileName);
		logger.debug("Start to writing data to file: " + fileName);
		FileWriter fWriter = null;
		BufferedWriter bWriter = null;
		try {
			if (!outputFile.exists()) {
				if (null != outputFile.getParentFile() && !outputFile.getParentFile().exists())
				outputFile.getParentFile().mkdirs();
				
				boolean isCreated = outputFile.createNewFile();
				logger.debug("The new file was created successfully? " + isCreated + " -- " + outputFile.getAbsolutePath());
			}
			fWriter = new FileWriter(outputFile.getAbsolutePath(), append);
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(content);
			bWriter.flush();
			bWriter.close();
			logger.debug("File output successfully [" + outputFile.getAbsolutePath() + "].");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		} finally {
			if (null != bWriter) {
				try {
					bWriter.close();
					bWriter = null;
				} catch (Exception e2) {
					logger.error(e2.getMessage(),e2);
				}
			}
			if (null != fWriter) {
				try {
					fWriter.close();
					fWriter = null;
				} catch (Exception e3) {
					logger.error(e3.getMessage(),e3);
				}
			}
		}
		return outputFile;
	}
	
	public static File getZipFile (File original, File zipedFile) {
		
		
		return zipedFile;
	}
}
