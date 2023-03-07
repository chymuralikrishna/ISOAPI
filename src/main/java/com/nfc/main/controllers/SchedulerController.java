package com.nfc.main.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nfc.main.dao.SchedulerDAO;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.util.MailIdFromEcNo;
import com.nfc.util.MailRestService;

public class SchedulerController {

	public void sendMailAlertToISOAuditees() {

		try {
			List<ISOAuditFinding> ISOAuditFindingList1 = new SchedulerDAO()
					.getISOAuditFindingListAboutToExpireIn7Days();
			List<ISOAuditFinding> ISOAuditFindingList2 = new SchedulerDAO().getExpiredISOAuditFindingList();
			List<ISOAuditFinding> ISOAuditFindingList3 = new SchedulerDAO()
					.getCorrectiveActionNotFiledISOAuditFindingList();

			List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();
			ISOAuditFindingList.addAll(ISOAuditFindingList1);
			ISOAuditFindingList.addAll(ISOAuditFindingList2);
			ISOAuditFindingList.addAll(ISOAuditFindingList3);

			List<String> recipients = new ArrayList<>();
			String body = "";
			String subject = "";
			List<String> recipientCC = new ArrayList<>();
			recipientCC.add(Constants.ISOINCHARGEMAILID);

			MailRestService mailRestService = new MailRestService();

			for (ISOAuditFinding isoAuditFinding : ISOAuditFindingList) {
				recipients = getAuditeesMailId(isoAuditFinding);

				subject = " ISO Audit " + isoAuditFinding.getIsoAuditFindingType() + " alert";

				body = "Dear Sir/Madam, " + "\r\n" + "\r\n";
				body = body + getBodySummary(isoAuditFinding);
				body = body + " \r\n";
				body = body + bodyWithISOAuditFindingDetails(isoAuditFinding);
				body = body + mailFooter();

				mailRestService.sendMail(recipients, recipientCC, subject, body);

			}
		} catch (Exception e) {
			e.printStackTrace();
			sendFailureMailToAdmin();
		}

	}

	private List<String> getAuditeesMailId(ISOAuditFinding isoAuditDetails) {
		List<String> auditeesMailIds = new ArrayList<>();
		MailIdFromEcNo mailIdFromEcNo = new MailIdFromEcNo();

		if (isoAuditDetails.getAuditee1EcNo() != null && isoAuditDetails.getAuditee1EcNo() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditee1EcNo()));

		if (isoAuditDetails.getAuditee2EcNo() != null && isoAuditDetails.getAuditee2EcNo() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditee2EcNo()));

		return auditeesMailIds;
	}

	private String getBodySummary(ISOAuditFinding isoAuditFinding) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String body = "";

		if (isoAuditFinding.isCorrectiveActionFiled()) {
			body = "     ISO Audit " + isoAuditFinding.getIsoAuditFindingType() + " has to be closed by "
					+ sdf.format(isoAuditFinding.getProposedCompletionDate()) + ". Please take appropriate action.";
		} else {
			body = "    Corrective Action is not yet filed for ISO Audit " + isoAuditFinding.getIsoAuditFindingType()
					+ ". Please take appropriate action.";
		}
		return body;
	}

	private String bodyWithISOAuditFindingDetails(ISOAuditFinding pojo) {
		String body = "";
		body = body + " Audit Id: " + pojo.getAuditId() + "\r\n";
		body = body + " Plant: " + pojo.getPlantName() + "-" + pojo.getPlantCode() + "\r\n";
		body = body + " Audit Finding Type: " + pojo.getIsoAuditFindingType() + "\r\n";
		body = body + " ISO Standard: " + pojo.getIsoStandard() + "\r\n";
		body = body + " Clause: " + pojo.getClause() + "\r\n";
		body = body + " Requirement: " + pojo.getRequirement() + "\r\n";
		body = body + " Failure: " + pojo.getFailure() + "\r\n";
		body = body + " Evidence: " + pojo.getEvidence() + "\r\n";

		return body;
	}

	private String mailFooter() {
		String body = "\r\n";
		body = body + " Please login to the ISO Application to take further action.";
		body = body + "\r\n" + "\r\n";
		body = body + "Regards, " + "\r\n " + "Team ISO Management System. " + " \r\n" + "\r\n";
		body = body + "Note: 1.Please don’t reply to this email. " + " \r\n";
		body = body + "     2.In case of any further assistance, send your query to fu-spt@nfc.gov.in";
		return body;

	}

	public void sendMailToAdmin() {
		List<String> recipients = new ArrayList<>();
		recipients.add(Constants.TESTMAILID);
		String body = "ISO Audit NC mail scheduler ran successfully";
		String subject = "ISO Audit NC mail scheduler ran successfully";
		List<String> recipientCC = new ArrayList<>();

		new MailRestService().sendMail(recipients, recipientCC, subject, body);
	}

	public void sendFailureMailToAdmin() {
		List<String> recipients = new ArrayList<>();
		recipients.add(Constants.TESTMAILID);
		String body = "ISO Audit NC mail scheduler error";
		String subject = "ISO Audit NC mail scheduler error. check logs";
		List<String> recipientCC = new ArrayList<>();

		new MailRestService().sendMail(recipients, recipientCC, subject, body);
	}

}
