package com.nfc.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.Constants;
import com.nfc.main.controllers.SchedulerController;
import com.nfc.main.dao.SchedulerDAO;

@RestController
public class SchedulerJobs {

	@Scheduled(cron = "0 0 6 * * *") // Daily 6' o clock it will run
	public void mailAlertForISOAuditees() {

		try {
			int sleepPeriod = ThreadLocalRandom.current().nextInt(3, 60);
			Thread.sleep(sleepPeriod * 1000);
			String author = InetAddress.getLocalHost().getHostName();

			if (!author.equals("appstest.nfc.gov.in") && Constants.ENV.equals(Constants.LIVE)
					&& new SchedulerDAO().getLock("ISONCMAILALERT", author)) {

				new SchedulerController().sendMailToAdmin();

				new SchedulerController().sendMailAlertToISOAuditees();

			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (UnknownHostException e) {
			//
			e.printStackTrace();
		}

	}

}
