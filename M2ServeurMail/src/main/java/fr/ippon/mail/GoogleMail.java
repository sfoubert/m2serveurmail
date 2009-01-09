package fr.ippon.mail;

import java.security.Security;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import fr.ippon.util.ServeurMailUtil;
import fr.ippon.util.StringUtil;

public class GoogleMail {

	private static final String emailFromAddress 	= "sebastien.foubert@gmail.com";
	private static final String[] sendTo 			= { "sebastien.foubert@gmail.com" };
	private static final String[] sendTo2 			= { "0689316118@orange.fr" };

	private static String adresseIP;
	
	//flag si mail deja envoyé
	private static boolean flagPassage = false;
	
	private static Logger log = Logger.getLogger(GoogleMail.class);
	
	public static void googleMail() throws MessagingException {

	
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		//si l'adresse a changé on renvoie un texto
		if( !StringUtil.isEmpty(adresseIP) && !ServeurMailUtil.getAdresseIP().equals(adresseIP)){
			flagPassage = false;
		}
		
		
		//Récupere l'adresse IP
		adresseIP = ServeurMailUtil.getAdresseIP();
		
		if(!StringUtil.isEmpty(adresseIP)){
		
			if(!adresseIP.equals("127.0.0.1") && !adresseIP.startsWith("192.168.0.")){
				
				log.info("Envoi de mail en cours...");
				
				Calendar cal = Calendar.getInstance();
				
				
				//-------------------------------------------- emailSubjectTxt ------------//
				String emailSubjectTxt = "IP: " + adresseIP +" [" + ServeurMailUtil.getUserName()+ "]" ;
				
				
				//-------------------------------------------- emailMsgTxt ------------//
				String emailMsgTxt = "Le : " + StringUtil.getStringFromCalendar(cal) 
							+ " à " + StringUtil.getStringFromCalendarHeure(cal)
							+ " Mon adresse IP : " + adresseIP; 
		
								
				//-------------------------------------------- envoi Mail ------------//
				SendMail.sendMail(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress);
				//SendMail.sendMail(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress, new File("C:/tmp/mailServeur.log"));
				
				
				//-------------------------------------------- envoi Texto ------------//
				// si 1ere fois
				if(!flagPassage){
					log.info("Envoi de texto en cours...");
					SendMail.sendMail(sendTo2, emailSubjectTxt, emailMsgTxt, emailFromAddress);
					flagPassage = true;
				}
				
		
				log.info("Envoi de mail Réussi");

			}else{
				log.error("Pas d'envoi de mail, l'adresse IP est : " + adresseIP);
			}
			
		}
		else{
			log.error("Probleme de récuperation de l'adresse IP");
		}
		
	}


	
	
	
	

}