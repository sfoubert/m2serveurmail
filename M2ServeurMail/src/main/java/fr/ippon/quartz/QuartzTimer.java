package fr.ippon.quartz;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import fr.ippon.util.ConfigurationUtil;
import fr.ippon.util.StringUtil;

/**
 * Classe de lancement du timer Quartz
 * à partir des configurations définis dans config-b2b 
 * @author foubert-seb
 * @date 09/10/2006
 */
public class QuartzTimer {

	private static Logger log = Logger.getLogger(QuartzTimer.class);
	
	private static Scheduler sched = null;
	private static boolean activated = false;	
	
	public static void loadQuartz() {
		
		
			if(sched==null){
				activated = (ConfigurationUtil.getString("quartz.activated").equals("oui")) ? true : false;	
				if(activated){
					log.info(" -- Lancement de Quartz -- ");
					try {		
						SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			
						sched = schedFact.getScheduler();
			
						sched.start();
				
						JobDetail jobDetail = new JobDetail("ServeurMailJob",
						                                      null,
						                                      ServeurMailExecution.class);
			  
						//recupere l'expression cron du fichier de conf
						String cronExpression = ConfigurationUtil.getString("quartz.cron.serveurMail");
						
						if(!StringUtil.isEmpty(cronExpression)){
							log.info("Expression cron : " + cronExpression);
						}else{
							log.error("Expression cron absente");
						}
						
						
						CronTrigger trigger = new CronTrigger("ServeurMailTrigger", "ServeurMailGroup");
						try {
							trigger.setCronExpression(cronExpression);
						} catch (ParseException e) {
							log.error("Impossible de parser l'expression cron suivante : " + cronExpression, e);
						}
				
						  sched.scheduleJob(jobDetail, trigger);
						  
					} catch (SchedulerException e) {
						log.error("Impossible de lancer le timer Quartz");
						e.printStackTrace();
					}
				}
			}


	}	
	
	
	public static void shutdownQuartz() {
		if(sched!=null){
			log.info(" -- Arret de Quartz -- ");
			try {
				sched.shutdown();
			} catch (SchedulerException e) {
				log.error("Impossible d'arreter le scheduler",e);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		QuartzTimer.loadQuartz();
	}
	
}
