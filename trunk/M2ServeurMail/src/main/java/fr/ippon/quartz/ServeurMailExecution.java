package fr.ippon.quartz;

import java.util.Calendar;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.ippon.mail.GoogleMail;
import fr.ippon.util.StringUtil;

/**
 * Classe lancée quant le cron se déclenche
 * qui fait du nettoyage dans Contexte_disco
 * @author foubert-seb
 * @date 09/10/2006
 *
 */
public class ServeurMailExecution implements Job {

	private static Logger log = Logger.getLogger(QuartzTimer.class);
	
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		log.info("---------- Exécution de job commencée -----------");
		long time = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		log.info("- le : " + StringUtil.getStringFromCalendar(cal) + " à " + StringUtil.getStringFromCalendarHeure(cal) );
		log.info("- nom : " + jobContext.getJobDetail().getFullName());
		log.info("- classe : " + jobContext.getJobDetail().getJobClass());
		

		//envoi du mail 
		try {
			GoogleMail.googleMail();
		} catch (MessagingException e) {
			log.error("Probleme lors de l'envoi du message", e);
			throw new JobExecutionException(e);
		}

		log.info("Temps d'execution : " + (System.currentTimeMillis() - time) + "ms" );
		
		log.info("---------- Exécution de job Terminée -----------");
	}


}
