package fr.ippon.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * Classe utilitaire pour l'appli
 * @author foubert-seb
 *
 */
public class ServeurMailUtil {
	
	private static Logger log = Logger.getLogger(ServeurMailUtil.class);
	
	/**
	 * fonction qui recupere l'adresse IP
	 * @return l'adresse IP
	 */
	public static String getAdresseIP(){
		String adresseIP = "";
		
		try {
			adresseIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("Impossible de récuperer l'adresse IP");
			e.printStackTrace();
		}
		
		log.info("Adresse IP : " + adresseIP);
		return adresseIP;
	}
	
	
	/**
	 * fonction qui recupere le userName 
	 * @return l'adresse IP
	 */
	public static String getUserName(){
		return System.getProperty("user.name");
	}
	

	
	
	/**
	 * classe de test
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ServeurMailUtil.getAdresseIP());
		
		System.out.println(
				System.getProperty("user.name")
		);
		
	}
	

	
}
