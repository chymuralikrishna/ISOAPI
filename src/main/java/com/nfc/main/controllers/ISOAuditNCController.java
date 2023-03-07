package com.nfc.main.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nfc.main.dao.ISOAuditFindingsDAO;
import com.nfc.main.dao.ISOAuditNCDAO;
import com.nfc.main.dao.ViewNfcUsersDAO;
import com.nfc.main.dao.CommonUtilDAO;
import com.nfc.main.dao.ISOAuditDetailsDAO;
import com.nfc.main.entities.IsoAuditFindings;
import com.nfc.main.entities.ViewIsoAuditDetails;
import com.nfc.main.objects.DashBoardCountForAuditors;
import com.nfc.main.objects.DashBoardCountsForPlants;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.main.objects.ISOAuditFindingClosure;
import com.nfc.util.MailIdFromEcNo;
import com.nfc.util.MailRestService;

public class ISOAuditNCController {

	public DashBoardCountsForPlants getDashboardCountsForPlant(Integer loginEcNo) {
		String managingPlants = new CommonUtilDAO().getManagingPlantsString(loginEcNo);
		String reportingEcNos = new CommonUtilDAO().getReportingEmployeesEcNos(loginEcNo);
		boolean isManager = new CommonUtilDAO().isManager(loginEcNo);
		DashBoardCountsForPlants dashBoardCount = new ISOAuditNCDAO().getDashboardCountsForPlant(reportingEcNos,
				managingPlants,isManager);
		return dashBoardCount;
	}

	public DashBoardCountForAuditors getDashboardCountsForAuditors(Integer loginEcNo) {
		DashBoardCountForAuditors dashBoardCount = new ISOAuditNCDAO().getDashBoardCountForAuditor(loginEcNo);
		return dashBoardCount;
	}

	public DashBoardCountForAuditors getDashboardCountsForISOIncharge() {
		DashBoardCountForAuditors dashBoardCount = new ISOAuditNCDAO().getDashBoardCountForISOIncharge();
		return dashBoardCount;
	}

	public List<ISOAuditFinding> getNCListPendingForCorrectiveAction(Integer loginEcNo) {
		String managingPlants = new CommonUtilDAO().getManagingPlantsString(loginEcNo);
		List<ISOAuditFinding> ISOAuditFindingList = new ISOAuditNCDAO()
				.getNCListPendingForCorrectiveAction(managingPlants);
		return ISOAuditFindingList;
	}

	public List<ISOAuditFinding> getNCListPendingForManagingPlants(Integer loginEcNo) {
		String managingPlants = new CommonUtilDAO().getManagingPlantsString(loginEcNo);
		List<ISOAuditFinding> ISOAuditFindingList = new ISOAuditNCDAO()
				.getNCListPendingForManagingPlants(managingPlants);
		return ISOAuditFindingList;
	}

	public List<ISOAuditFinding> getNCListSubmittedForApproval(Integer loginEcNo, String loginRole) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		switch (loginRole) {
		case Constants.PLANT:
			String reportingEcNos = new CommonUtilDAO().getReportingEmployeesEcNos(loginEcNo);
			ISOAuditFindingList = new ISOAuditNCDAO().getNCListSubmittedForApprovalToPlantManager(reportingEcNos);
			break;
		case Constants.ISOAUDITOR:
			ISOAuditFindingList = new ISOAuditNCDAO().getNCListSubmittedForApprovalToISOAuditor(loginEcNo);
			break;
		case Constants.ISOINCHARGE:
			ISOAuditFindingList = new ISOAuditNCDAO().getNCListSubmittedForApprovalToISOIncharge();
			break;
		default:
			break;
		}

		return ISOAuditFindingList;
	}

	public Response submitForClosure(ISOAuditFindingClosure auditFindingClosure) {
		IsoAuditFindings isoAuditFindingPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(
				auditFindingClosure.getAuditId(), auditFindingClosure.getPlantCode(),
				auditFindingClosure.getFindingNo());

		isoAuditFindingPojo.setStatus(Constants.SUBMITTED);
		isoAuditFindingPojo.setSubmittedForClosureByEcno(auditFindingClosure.getLoginEcNo());
		isoAuditFindingPojo.setSubmittedForClosureOn(new Date());

		isoAuditFindingPojo.setLastUpdatedBy(auditFindingClosure.getLoginEcNo());
		isoAuditFindingPojo.setLastUpdatedOn(new Date());
		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(isoAuditFindingPojo);

		Response response = new Response();
		response.setResult(result);

		if (result > 0) {
			saveEntryInAuditFindingLogs(isoAuditFindingPojo, "ISO Audit Finding Submitted For Closure",
					auditFindingClosure.getComments(), auditFindingClosure.getLoginEcNo());
			sendMailToManager(isoAuditFindingPojo);
			response.setMessage("ISO Audit Finding submitted for closure successfully");
		} else {
			response.setMessage("Error occured while processing your request");
		}

		return response;
	}

	public Response recommendClosure(ISOAuditFindingClosure auditFindingClosure) {
		IsoAuditFindings isoAuditFindingPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(
				auditFindingClosure.getAuditId(), auditFindingClosure.getPlantCode(),
				auditFindingClosure.getFindingNo());

		isoAuditFindingPojo.setStatus(Constants.RECOMMENDED);
		isoAuditFindingPojo.setRecommendedForClosureOn(new Date());
		isoAuditFindingPojo.setRecommendedForClosureByEcno(auditFindingClosure.getLoginEcNo());

		isoAuditFindingPojo.setLastUpdatedBy(auditFindingClosure.getLoginEcNo());
		isoAuditFindingPojo.setLastUpdatedOn(new Date());

		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(isoAuditFindingPojo);

		Response response = new Response();
		response.setResult(result);

		if (result > 0) {
			saveEntryInAuditFindingLogs(isoAuditFindingPojo, "ISO Audit Finding Recommended For Closure",
					auditFindingClosure.getComments(), auditFindingClosure.getLoginEcNo());

			sendMailToISOAuditor(isoAuditFindingPojo);

			response.setMessage("ISO Audit Finding Recommended for closure successfully");
		} else {
			response.setMessage("Error occured while processing your request");
		}

		return response;
	}

	public Response approveClosure(ISOAuditFindingClosure auditFindingClosure) {
		IsoAuditFindings isoAuditFindingPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(
				auditFindingClosure.getAuditId(), auditFindingClosure.getPlantCode(),
				auditFindingClosure.getFindingNo());

		isoAuditFindingPojo.setClosureApprovedByEcno(auditFindingClosure.getLoginEcNo());
		isoAuditFindingPojo.setClosureApprovedOn(new Date());
		isoAuditFindingPojo.setStatus(Constants.CLOSED);

		isoAuditFindingPojo.setLastUpdatedBy(auditFindingClosure.getLoginEcNo());
		isoAuditFindingPojo.setLastUpdatedOn(new Date());

		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(isoAuditFindingPojo);

		Response response = new Response();
		response.setResult(result);

		if (result > 0) {
			saveEntryInAuditFindingLogs(isoAuditFindingPojo, "ISO Audit Finding Closed",
					auditFindingClosure.getComments(), auditFindingClosure.getLoginEcNo());

			sendMailToAuditeeAfterClosureApproval(isoAuditFindingPojo);

			response.setMessage("ISO Audit Finding Closure approved successfully");
		} else {
			response.setMessage("Error occured while processing your request");
		}

		return response;
	}

	public Response returnClosure(ISOAuditFindingClosure auditFindingClosure) {
		IsoAuditFindings isoAuditFindingPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(
				auditFindingClosure.getAuditId(), auditFindingClosure.getPlantCode(),
				auditFindingClosure.getFindingNo());

		String status = isoAuditFindingPojo.getStatus();
		isoAuditFindingPojo.setStatus(Constants.RETURNED);

		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(isoAuditFindingPojo);

		Response response = new Response();
		response.setResult(result);

		if (result > 0) {
			saveEntryInAuditFindingLogs(isoAuditFindingPojo, "ISO Audit Finding Closure Returned",
					auditFindingClosure.getComments(), auditFindingClosure.getLoginEcNo());

			response.setMessage("ISO Audit Finding closure request returned successfully");

			if (status.equals(Constants.SUBMITTED)) {
				sendMailToAuditeeAfterRejectionByManager(isoAuditFindingPojo, auditFindingClosure.getLoginEcNo(),
						auditFindingClosure.getComments());
			} else if (status.equals(Constants.RECOMMENDED)) {
				sendMailToAuditeeAfterRejectionByISO(isoAuditFindingPojo, auditFindingClosure.getLoginEcNo(),
						auditFindingClosure.getComments());
			}

		} else {
			response.setMessage("Error occured while processing your request");
		}

		return response;
	}

	private void saveEntryInAuditFindingLogs(IsoAuditFindings pojo, String description, String comments,
			Integer loginEcno) {
		String compositeId = pojo.getIsoAuditFindingsPK().getAuditId() + "/"
				+ pojo.getIsoAuditFindingsPK().getPlantCode() + "/" + pojo.getIsoAuditFindingsPK().getFindingNo();
		new ISOAuditFindingLogsController().saveISOAuditFindingLogs(compositeId, loginEcno, description, comments);
	}

	private void sendMailToManager(IsoAuditFindings pojo) {

		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(
				pojo.getIsoAuditFindingsPK().getAuditId(), pojo.getIsoAuditFindingsPK().getPlantCode());
		List<String> recipientsCC = getAuditeesMailId(isoAuditDetails);

		if (isoAuditDetails.getAuditee1Ecno() != pojo.getSubmittedForClosureByEcno()
				&& isoAuditDetails.getAuditee2Ecno() != pojo.getSubmittedForClosureByEcno()) {
			String ccMailId = new MailIdFromEcNo().getMailIdFromEcNo(pojo.getSubmittedForClosureByEcno());
			recipientsCC.add(ccMailId);
		}

		List<String> recipients = new ArrayList<>();
		Integer managerEcNo = new CommonUtilDAO().getManagerEcNo(pojo.getSubmittedForClosureByEcno());
		String managerMailId = new MailIdFromEcNo().getMailIdFromEcNo(managerEcNo);
		recipients.add(managerMailId);

		String mailSubject = "ISO NC Closure Request Submitted for your approval";

		String userName = new ViewNfcUsersDAO().getUserName(pojo.getSubmittedForClosureByEcno());
		String body = "Dear Sir/Madam, " + "\r\n" + "\r\n";

		body = body + " ISO Audit NC is submitted for closure by " + userName;
		body = body + "\r\n" + "\r\n";
		body = body + bodyWithISOAuditFindingDetails(pojo);
		body = body + mailFooter();

		new MailRestService().sendMail(recipients, recipientsCC, mailSubject, body);

	}

	private List<String> getAuditeesMailId(ViewIsoAuditDetails isoAuditDetails) {
		List<String> auditeesMailIds = new ArrayList<>();
		MailIdFromEcNo mailIdFromEcNo = new MailIdFromEcNo();

		if (isoAuditDetails.getAuditee1Ecno() != null && isoAuditDetails.getAuditee1Ecno() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditee1Ecno()));

		if (isoAuditDetails.getAuditee2Ecno() != null && isoAuditDetails.getAuditee2Ecno() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditee2Ecno()));

		return auditeesMailIds;
	}

	private List<String> getAuditorsMailId(ViewIsoAuditDetails isoAuditDetails) {
		List<String> auditeesMailIds = new ArrayList<>();
		MailIdFromEcNo mailIdFromEcNo = new MailIdFromEcNo();

		if (isoAuditDetails.getAuditor1Ecno() != null && isoAuditDetails.getAuditor1Ecno() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditor1Ecno()));

		if (isoAuditDetails.getAuditor2Ecno() != null && isoAuditDetails.getAuditor2Ecno() != 0)
			auditeesMailIds.add(mailIdFromEcNo.getMailIdFromEcNo(isoAuditDetails.getAuditor2Ecno()));

		return auditeesMailIds;
	}

	private String bodyWithISOAuditFindingDetails(IsoAuditFindings pojo) {
		String body = "";
		body = body + " Audit Id: " + pojo.getIsoAuditFindingsPK().getAuditId() + "\r\n";
		body = body + " Plant Code: " + pojo.getIsoAuditFindingsPK().getPlantCode() + "\r\n";
		body = body + " ISO Standard: " + pojo.getIsoStandard() + "\r\n";
		body = body + " Clause: " + pojo.getClause() + "\r\n";
		body = body + " Requirement: " + pojo.getRequirement() + "\r\n";
		body = body + " Failure: " + pojo.getFailure() + "\r\n";
		body = body + " Evidence: " + pojo.getEvidence() + "\r\n";
		return body;
	}

	private String mailFooter() {
		String body = "";
		body = body + ". Please login to the ISO Application to take further action.";
		body = body + "\r\n" + "\r\n";
		body = body + "Regards, " + "\r\n " + "Team ISO Management System. " + " \r\n" + "\r\n";
		body = body + "Note: 1.Please don’t reply to this email. " + " \r\n";
		body = body + "     2.In case of any further assistance, send your query to fu-spt@nfc.gov.in";
		return body;

	}

	private void sendMailToISOAuditor(IsoAuditFindings pojo) {

		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(
				pojo.getIsoAuditFindingsPK().getAuditId(), pojo.getIsoAuditFindingsPK().getPlantCode());
		List<String> recipientsCC = getAuditeesMailId(isoAuditDetails);
		recipientsCC.add(Constants.ISOINCHARGEMAILID);

		if (isoAuditDetails.getAuditee1Ecno() != pojo.getSubmittedForClosureByEcno()
				&& isoAuditDetails.getAuditee2Ecno() != pojo.getSubmittedForClosureByEcno()) {
			String ccMailId = new MailIdFromEcNo().getMailIdFromEcNo(pojo.getSubmittedForClosureByEcno());
			recipientsCC.add(ccMailId);
		}

		List<String> recipients = getAuditorsMailId(isoAuditDetails);

		String mailSubject = "ISO NC Closure Request Submitted for your approval";

		String userName = new ViewNfcUsersDAO().getUserName(pojo.getSubmittedForClosureByEcno());
		String body = "Dear Sir/Madam, " + "\r\n" + "\r\n";

		body = body + " ISO Audit NC is submitted for closure by " + userName;

		body = body + "\r\n" + "\r\n";
		body = body + bodyWithISOAuditFindingDetails(pojo);
		body = body + " Closure Recommended by :"
				+ new ViewNfcUsersDAO().getUserName(pojo.getRecommendedForClosureByEcno()) + "\r\n";
		body = body + mailFooter();

		new MailRestService().sendMail(recipients, recipientsCC, mailSubject, body);

	}

	private void sendMailToAuditeeAfterClosureApproval(IsoAuditFindings pojo) {

		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(
				pojo.getIsoAuditFindingsPK().getAuditId(), pojo.getIsoAuditFindingsPK().getPlantCode());
		List<String> recipients = getAuditeesMailId(isoAuditDetails);

		if (isoAuditDetails.getAuditee1Ecno() != pojo.getSubmittedForClosureByEcno()
				&& isoAuditDetails.getAuditee2Ecno() != pojo.getSubmittedForClosureByEcno()) {
			String ccMailId = new MailIdFromEcNo().getMailIdFromEcNo(pojo.getSubmittedForClosureByEcno());
			recipients.add(ccMailId);
		}

		List<String> recipientsCC = getAuditorsMailId(isoAuditDetails);
		recipientsCC.add(Constants.ISOINCHARGEMAILID);

		String mailSubject = "ISO NC Closure Request Approved";

		String userName = new ViewNfcUsersDAO().getUserName(pojo.getClosureApprovedByEcno());
		String body = "Dear Sir/Madam, " + "\r\n" + "\r\n";

		body = body + " ISO Audit NC Closure request is approved by " + userName;

		body = body + "\r\n" + "\r\n";
		body = body + bodyWithISOAuditFindingDetails(pojo);
		body = body + " Closure Recommended by :"
				+ new ViewNfcUsersDAO().getUserName(pojo.getRecommendedForClosureByEcno()) + "\r\n";
		body = body + mailFooter();

		new MailRestService().sendMail(recipients, recipientsCC, mailSubject, body);

	}

	private void sendMailToAuditeeAfterRejectionByManager(IsoAuditFindings pojo, Integer loginEcNo, String comments) {
		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(
				pojo.getIsoAuditFindingsPK().getAuditId(), pojo.getIsoAuditFindingsPK().getPlantCode());
		List<String> recipients = getAuditeesMailId(isoAuditDetails);

		if (isoAuditDetails.getAuditee1Ecno() != pojo.getSubmittedForClosureByEcno()
				&& isoAuditDetails.getAuditee2Ecno() != pojo.getSubmittedForClosureByEcno()) {
			String ccMailId = new MailIdFromEcNo().getMailIdFromEcNo(pojo.getSubmittedForClosureByEcno());
			recipients.add(ccMailId);
		}

		List<String> recipientsCC = new ArrayList<>();
		recipientsCC.add(new MailIdFromEcNo().getMailIdFromEcNo(loginEcNo));

		String mailSubject = "ISO NC Closure Request returned ";

		String userName = new ViewNfcUsersDAO().getUserName(loginEcNo);
		String body = "Dear Sir/Madam, " + "\r\n" + "\r\n";

		body = body + " ISO Audit NC Closure request is returned by " + userName;

		body = body + "\r\n" + "\r\n";
		body = body + bodyWithISOAuditFindingDetails(pojo);
		body = body + " Remarks/Comments for rejection : " + comments + "\r\n";
		body = body + mailFooter();

		new MailRestService().sendMail(recipients, recipientsCC, mailSubject, body);
	}

	private void sendMailToAuditeeAfterRejectionByISO(IsoAuditFindings pojo, Integer loginEcNo, String comments) {
		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(
				pojo.getIsoAuditFindingsPK().getAuditId(), pojo.getIsoAuditFindingsPK().getPlantCode());
		List<String> recipients = getAuditeesMailId(isoAuditDetails);

		if (isoAuditDetails.getAuditee1Ecno() != pojo.getSubmittedForClosureByEcno()
				&& isoAuditDetails.getAuditee2Ecno() != pojo.getSubmittedForClosureByEcno()) {
			String ccMailId = new MailIdFromEcNo().getMailIdFromEcNo(pojo.getSubmittedForClosureByEcno());
			recipients.add(ccMailId);
		}

		List<String> recipientsCC = getAuditorsMailId(isoAuditDetails);
		recipientsCC.add(Constants.ISOINCHARGEMAILID);

		String mailSubject = "ISO NC Closure Request returned ";

		String userName = new ViewNfcUsersDAO().getUserName(loginEcNo);
		String body = "Dear Sir/Madam, " + "\r\n" + "\r\n";

		body = body + " ISO Audit NC Closure request is returned by " + userName;

		body = body + "\r\n" + "\r\n";
		body = body + bodyWithISOAuditFindingDetails(pojo);
		body = body + " Remarks/Comments for rejection : " + comments + "\r\n";
		body = body + mailFooter();

		new MailRestService().sendMail(recipients, recipientsCC, mailSubject, body);
	}

}
