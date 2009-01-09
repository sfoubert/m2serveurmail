package fr.ippon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Classe utilitaire pour l'appli
 * @author foubert-seb
 *
 */
public class StringUtil {
	
	private static Logger log = Logger.getLogger(StringUtil.class);
	
	
	/**
	 * format date
	 */
	private static SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * format heure
	 */
	private static SimpleDateFormat shf= new SimpleDateFormat("HH:mm:ss");
	  

	
	  /**
	   * fonction qui retourne un boolean a true/false suivant si la chaine est vide ou null
	   * @param chaine
	   * @return true si chaine est empty
	   */
	  public static boolean isEmpty(String chaine){
		  if(null==chaine || (null!=chaine && chaine.equals(""))) return true;
		  return false;
	  }
	
	/**
	 * retourne un String à partir d'une date  
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getStringFromDate(Date date){			
		return sdf.format(date);
	}
	
	/**
	 * retourne un String à partir d'un Calendar  
	 * @param Calendar
	 * @return
	 */
	public static String getStringFromCalendar(Calendar cal){
		return getStringFromDate(cal.getTime());
	}
	
	/**
	 * retourne un String à partir d'un Calendar representant une heure  
	 * @param Calendar
	 * @return
	 */
	public static String getStringFromCalendarHeure(Calendar cal){
		return shf.format(cal.getTime());
	}
	
}
