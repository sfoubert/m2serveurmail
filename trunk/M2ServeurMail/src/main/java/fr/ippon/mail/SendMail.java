package fr.ippon.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendMail {
	
	private static final String loginGMail 		= "sebastien.foubert@gmail.com";
	private static final String passwordGMail 	= "xxxxxxxxx";
	
	private static final String SMTP_HOST_NAME 	= "smtp.gmail.com";
	private static final String SMTP_PORT 		= "465";
	private static final String SSL_FACTORY 	= "javax.net.ssl.SSLSocketFactory";

	private static Logger log = Logger.getLogger(SendMail.class);
	
	public static void sendMail(String recipients[], String subject,String message, String from) throws MessagingException {
		sendMail(recipients, subject, message, from, null);
	}
	
	public static void sendMail(String recipients[], String subject,String message, String from, File file) throws MessagingException {
		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		//Session session = Session.getDefaultInstance(props, null);
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(loginGMail, passwordGMail);
					}
				}
		);

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		
		//si piece jointe
		if(file!=null){
			//-------------------------
			//Première partie du message
			BodyPart messageBodyPart = new MimeBodyPart();
	
			//Contenu du message
			messageBodyPart.setText(message);
	
			//Ajout de la première partie du message dans un objet Multipart
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
	
			// Partie de la pièce jointe
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file.getParentFile());
			messageBodyPart.setDataHandler(new DataHandler(source));				
			messageBodyPart.setFileName(file.getName());
			//Ajout de la partie pièce jointe
			multipart.addBodyPart(messageBodyPart);
			
			msg.setContent(multipart);
			//-------------------------

		}else{
			
			// Setting the Subject and Content Type
			msg.setContent(message, "text/plain");

		}
		
		Transport.send(msg);
	}
}
