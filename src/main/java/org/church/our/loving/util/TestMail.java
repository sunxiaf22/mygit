package org.church.our.loving.util;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.util.MailSSLSocketFactory;
public class TestMail {
    public static void  main(String args[]){
        try {
            send_email();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void send_email() throws IOException, AddressException, MessagingException, GeneralSecurityException{
        String to = "164570618@qq.com";
        String subject = "subject";
        String content = "content";
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.setProperty("mail.host", "smtp.qq.com");
        props.setProperty("mail.transport.protocol", "smtp");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Authenticator authenticator = new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		PasswordAuthentication passwordAuthentication = new PasswordAuthentication("164570618@qq.com", "osvvmeveukofcaja");
        		return passwordAuthentication;
        	}
		};
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(props, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress("164570618@qq.com"));
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(subject, "UTF-8");
        mailMessage.setSentDate(new Date());
        Multipart mainPart = new MimeMultipart();
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        System.out.println("Read send.........");
        Transport.send(mailMessage);
    }
    
    public static String commonMail(String mail_to, String subject_v, String content_v, String file_name) {
    	System.out.println("Sending email............");
    	String result = "SUCCESS";
    	try {
    		String to = mail_to;
    		String subject = subject_v;
    		String content = content_v;
    		Properties props = new Properties();
    		//props.setProperty("mail.debug", "true");
    		props.setProperty("mail.smtp.auth", "true");
    		props.put("mail.smtp.port", "465");
    		props.setProperty("mail.host", "smtp.qq.com");
    		props.setProperty("mail.transport.protocol", "smtp");
    		MailSSLSocketFactory sf = new MailSSLSocketFactory();
    		sf.setTrustAllHosts(true);
    		props.put("mail.smtp.ssl.enable", "true");
    		//props.put("mail.smtp.ssl.socketFactory", sf);
    		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    		Authenticator authenticator = new Authenticator() {
    			@Override
    			protected PasswordAuthentication getPasswordAuthentication() {
    				PasswordAuthentication passwordAuthentication = new PasswordAuthentication("164570618@qq.com", "osvvmeveukofcaja");
    				return passwordAuthentication;
    			}
    		};
    		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(props, authenticator);
    		MimeMessage mailMessage = new MimeMessage(sendMailSession);
    		mailMessage.setFrom(new InternetAddress("164570618@qq.com"));
    		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		mailMessage.setSubject(subject, "UTF-8");
    		mailMessage.setSentDate(new Date());
    		Multipart mainPart = new MimeMultipart();
    		BodyPart contentPart = new MimeBodyPart();
    		contentPart.setContent(content.trim(), "text/html; charset=utf-8");
    		mainPart.addBodyPart(contentPart);
    		if (null != file_name && file_name.trim().length() != 0) {
    			BodyPart attachPart = new MimeBodyPart();
    			DataSource dataSource = new FileDataSource(new File(file_name));
    			attachPart.setDataHandler(new DataHandler(dataSource));
    			attachPart.setFileName(file_name);
    			mainPart.addBodyPart(attachPart);
    		}
    		mailMessage.setContent(mainPart);
    		Transport.send(mailMessage);
			
		} catch (Exception e) {
			result = StringUtil.processException(e);
			System.out.println(result);
		} finally {
			result += "\n End!";
			System.out.println("Finished sending email... " + result);
		}
    	return result;
    }
}