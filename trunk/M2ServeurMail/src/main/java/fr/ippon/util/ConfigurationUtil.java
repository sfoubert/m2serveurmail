package fr.ippon.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 
 * @author foubert-seb
 * Classe de gestion des ResourcesBundle
 */
public class ConfigurationUtil {
	
	/**
	 * fichiers de properties
	 */
	private final static String configuration = "config";
	
	/**
	 * resourcebundles
	 */
	private static ResourceBundle configurationBundle=null;

	/**
	 * log
	 */
	private static Logger log = Logger.getLogger(ConfigurationUtil.class);
	
	/**
	 * divers
	 */
	private static final String argument0 = "{0}";
	private static final String argument1 = "{1}";
	
	private static final String unicode0 = "\\u007B0\\u007D";  //correspond à {0} en unicode
	private static final String unicode1 = "\\u007B1\\u007D";  //correspond à {1} en unicode
	
	/**
	 * fonction qui fait un getString et rajoute les arguments à la Struts 
	 * @param bundle
	 * @param arg0
	 * @return
	 */
	public static String getString( final String key){
		if(configurationBundle==null){
			try{
				configurationBundle = ResourceBundle.getBundle(configuration);
			}catch(MissingResourceException e){
				log.fatal(configuration + " n'est pas trouvé");
				throw new RuntimeException(configuration + " n'est pas trouvé");
			}
		}		
		final String chaine = configurationBundle.getString(key);
		return chaine;
	}
	
	/**
	 * fonction qui fait un getString et rajoute les arguments à la Struts 
	 * @param bundle
	 * @param arg0
	 * @return
	 */
	public static String getString( final String key, final String arg0){
		if(configurationBundle==null){
			try {
				configurationBundle = ResourceBundle.getBundle(configuration);
			}catch(MissingResourceException e){
				log.fatal(configuration + " n'est pas trouvé");
				throw new RuntimeException(configuration + " n'est pas trouvé");
			}
		}
		String chaine = configurationBundle.getString(key);
		if(arg0!=null && chaine.indexOf(argument0)>-1){
			chaine = chaine.replaceFirst(unicode0, arg0);
		}
		return chaine;
	}

	/**
	 * fonction qui fait un getString et rajoute les arguments à la Struts 
	 * @param bundle
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static String getString( String key, final String arg0, final String arg1){
		if(configurationBundle==null){
			try{
				configurationBundle = ResourceBundle.getBundle(configuration);
			}catch(MissingResourceException e){
				log.fatal(configuration + " n'est pas trouvé");
				throw new RuntimeException(configuration + " n'est pas trouvé");
			}
		}
		String chaine = configurationBundle.getString(key);
		if(arg0!=null){
			if(chaine.indexOf(argument0)>-1){
				chaine = chaine.replaceFirst(unicode0, arg0);
			}
			if(chaine.indexOf(argument1)>-1){
				chaine = chaine.replaceFirst(unicode1, arg1);
			}
		}
		return chaine;
	}
	
}
