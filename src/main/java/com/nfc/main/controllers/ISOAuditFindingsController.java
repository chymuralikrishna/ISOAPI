package com.nfc.main.controllers;

import java.util.Date;
import java.util.List;

import com.nfc.main.dao.CommonUtilDAO;
import com.nfc.main.dao.ISOAuditDetailsDAO;
import com.nfc.main.dao.ISOAuditFindingsDAO;
import com.nfc.main.entities.IsoAuditFindings;
import com.nfc.main.entities.IsoAuditFindingsPK;
import com.nfc.main.objects.CorrectiveActionReportByAuditee;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.main.objects.ISOAuditFindingByAuditor;
import com.nfc.main.objects.RenderButtons;

public class ISOAuditFindingsController {

	public Response saveISOAuditFindingByISOAuditor(ISOAuditFindingByAuditor isoAuditFinding) {
		IsoAuditFindings pojo = prepareIsoAuditFindingPojoByISOAuditor(new IsoAuditFindings(), isoAuditFinding, true);
		Integer result = new ISOAuditFindingsDAO().saveISOAuditFinding(pojo);
		saveEntryInAuditFindingLogs(pojo, "ISO Audit Finding Details Saved", "ISO Audit Finding Details Saved");

		Response response = new Response();
		response.setResult(result);
		if (result > 0) {
			response.setMessage("ISO Audit Finding details saved successfully");
		} else {
			response.setMessage("Error occurred while saving ISO Audit Finding Details");
		}

		return response;
	}

	public Response updateISOAuditFindingByISOAuditor(ISOAuditFindingByAuditor isoAuditFinding) {

		IsoAuditFindings isoAuditNCPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(isoAuditFinding.getAuditId(),
				isoAuditFinding.getPlantCode(), isoAuditFinding.getFindingNumber());

		IsoAuditFindings pojo = prepareIsoAuditFindingPojoByISOAuditor(isoAuditNCPojo, isoAuditFinding, false);
		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(pojo);
		saveEntryInAuditFindingLogs(pojo, "ISO Audit Finding Details updated", "ISO Audit Finding Details updated");

		Response response = new Response();
		response.setResult(result);
		if (result > 0) {
			response.setMessage("ISO Audit Finding details updated successfully");
		} else {
			response.setMessage("Error occurred while saving ISO Audit Finding Details");
		}
		return response;

	}

	private IsoAuditFindings prepareIsoAuditFindingPojoByISOAuditor(IsoAuditFindings isoAuditNCPojo,
			ISOAuditFindingByAuditor isoAuditNC, boolean isSave) {

		if (isSave) {

			IsoAuditFindingsPK id = new IsoAuditFindingsPK();

			id.setAuditId(isoAuditNC.getAuditId());
			id.setPlantCode(isoAuditNC.getPlantCode());

			Integer newFindingNo = new ISOAuditFindingsDAO().generateNewFindingNumber(isoAuditNC.getAuditId(),
					isoAuditNC.getPlantCode());

			id.setFindingNo(newFindingNo);

			isoAuditNCPojo.setIsoAuditFindingsPK(id);

			isoAuditNCPojo.setCreatedBy(isoAuditNC.getLoginEcNo());
			isoAuditNCPojo.setCreatedDate(new Date());

			isoAuditNCPojo.setCorrectiveActionFiled(false);
		}

		isoAuditNCPojo.setLastUpdatedBy(isoAuditNC.getLoginEcNo());
		isoAuditNCPojo.setLastUpdatedOn(new Date());

		isoAuditNCPojo.setFindingTypeId(isoAuditNC.getIsoAuditFindingType());
		isoAuditNCPojo.setIsoStandard(isoAuditNC.getIsoStandard());
		isoAuditNCPojo.setClause(isoAuditNC.getClause());
		isoAuditNCPojo.setRequirement(isoAuditNC.getRequirement());
		isoAuditNCPojo.setFailure(isoAuditNC.getFailure());
		isoAuditNCPojo.setEvidence(isoAuditNC.getEvidence());
		isoAuditNCPojo.setStatus(Constants.OPEN);
		isoAuditNCPojo.setDescription(isoAuditNC.getDocumentDescription());
		isoAuditNCPojo.setDmsFileName(isoAuditNC.getDmsFileName());

		return isoAuditNCPojo;
	}

	private void saveEntryInAuditFindingLogs(IsoAuditFindings pojo, String description, String comments) {
		String compositeId = pojo.getIsoAuditFindingsPK().getAuditId() + "/"
				+ pojo.getIsoAuditFindingsPK().getPlantCode() + "/" + pojo.getIsoAuditFindingsPK().getFindingNo();
		new ISOAuditFindingLogsController().saveISOAuditFindingLogs(compositeId, pojo.getLastUpdatedBy(), description,
				comments);
	}

	public Response saveCorrectiveActionReport(CorrectiveActionReportByAuditee correctiveAction) {

		IsoAuditFindings isoAuditFindingPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(
				correctiveAction.getAuditId(), correctiveAction.getPlantCode(), correctiveAction.getFindingNo());

		IsoAuditFindings pojo = prepareIsoAuditFindingPojoWithCorrectiveActionReport(isoAuditFindingPojo,
				correctiveAction);
		Integer result = new ISOAuditFindingsDAO().updateISOAuditFinding(pojo);
		saveEntryInAuditFindingLogs(pojo, "Corrective Action Report Saved Successfully",
				"Corrective Action Report Saved Successfully");

		Response response = new Response();
		response.setResult(result);

		if (result > 0) {
			response.setMessage("Corrective Action Details Saved Successfully");
		} else {
			response.setMessage("Error occured while saving Corrective Action Details");
		}

		return response;

	}

	private IsoAuditFindings prepareIsoAuditFindingPojoWithCorrectiveActionReport(IsoAuditFindings isoAuditFindingPojo,
			CorrectiveActionReportByAuditee correctiveAction)

	{
		isoAuditFindingPojo.setProposedCorrection(correctiveAction.getProposedCorrection());
		isoAuditFindingPojo.setRootCause(correctiveAction.getRootCause());
		isoAuditFindingPojo.setProposedCorrectiveAction(correctiveAction.getProposedCorrectiveAction());
		isoAuditFindingPojo.setProposedCompletionDate(correctiveAction.getProposedCompletionDate());
		isoAuditFindingPojo.setLastUpdatedBy(correctiveAction.getLoginEcNo());
		isoAuditFindingPojo.setLastUpdatedOn(new Date());

		isoAuditFindingPojo.setCorrectiveActionFiled(true);
		isoAuditFindingPojo.setCorrectiveActionFiledOn(new Date());
		isoAuditFindingPojo.setCorrectiveActionFiledByEcno(correctiveAction.getLoginEcNo());
		return isoAuditFindingPojo;
	}

	public List<ISOAuditFinding> getAuditFindingList(Integer plantCode, String auditId) {
		List<ISOAuditFinding> ISOAuditFindingByAuditList = new ISOAuditFindingsDAO().getISOAuditFindingList(plantCode,
				auditId);
		return ISOAuditFindingByAuditList;
	}

	public IsoAuditFindings getISOAuditFindingDetails(String auditId, Integer plantCode, Integer findingNo) {
		IsoAuditFindings isoAuditFindings = new ISOAuditFindingsDAO().getISOAuditFindingDetails(auditId, plantCode,
				findingNo);

		return isoAuditFindings;
	}

	public List<ISOAuditFinding> getAuditFindingList(Integer loginEcNo) {
		String managingPlants = new CommonUtilDAO().getManagingPlantsString(loginEcNo);
		List<ISOAuditFinding> ISOAuditFindingByAuditList = new ISOAuditFindingsDAO()
				.getISOAuditFindingListForListOfPlants(managingPlants);
		return ISOAuditFindingByAuditList;
	}

	public RenderButtons getRenderButtons(String auditId, Integer plantCode, Integer findingNo, Integer loginEcNo,
			String loginRole) {
		RenderButtons renderButtons = new RenderButtons();

		IsoAuditFindings isoAuditNCPojo = new ISOAuditFindingsDAO().getISOAuditFindingPojo(auditId, plantCode,
				findingNo);

		if (loginRole.equals(Constants.PLANT)) {

			List<String> managingPlants = new CommonUtilDAO().getListOfManagingPlants(loginEcNo);

			if (managingPlants.contains(plantCode.toString()) && isoAuditNCPojo.getCorrectiveActionFiled() != null
					&& !isoAuditNCPojo.getCorrectiveActionFiled()) {
				renderButtons.setRenderSaveCorrectiveActionButton(true);
				return renderButtons;
			}

			if (managingPlants.contains(plantCode.toString()) && (isoAuditNCPojo.getStatus().equals(Constants.OPEN)
					|| isoAuditNCPojo.getStatus().equals(Constants.RETURNED))) {
				renderButtons.setRenderSubmitForClosureButton(true);
				return renderButtons;
			}
		}

		if (loginRole.equals(Constants.PLANT) && isoAuditNCPojo.getStatus().equals(Constants.SUBMITTED)) {

			boolean isManager = new CommonUtilDAO().isManager(loginEcNo, isoAuditNCPojo.getSubmittedForClosureByEcno());
			if (isManager) {
				renderButtons.setRenderRecommendForClosureButton(true);
				return renderButtons;
			}

			// if manager himself apply for closure
			if (loginEcNo.equals(isoAuditNCPojo.getSubmittedForClosureByEcno())) {
				isManager = new CommonUtilDAO().isManager(loginEcNo);
				renderButtons.setRenderRecommendForClosureButton(isManager);
			}

			return renderButtons;
		}

		if (isoAuditNCPojo.getStatus().equals(Constants.RECOMMENDED)) {

			if (loginRole.equals(Constants.ISOINCHARGE)) {
				renderButtons.setRenderApproveClosureButton(true);
				return renderButtons;
			}

			if (loginRole.equals(Constants.ISOAUDITOR)) {
				boolean isAuditorForThisAudit = new ISOAuditDetailsDAO().isAuditor(auditId, plantCode, loginEcNo);

				if (isAuditorForThisAudit) {
					renderButtons.setRenderApproveClosureButton(true);
				}

				return renderButtons;
			}

		}

		return renderButtons;
	}

	public List<ISOAuditFinding> getISOAuditFindingsSummaryList(String auditId) {
		List<ISOAuditFinding> ISOAuditFindingSummaryList = new ISOAuditFindingsDAO().getISOAuditFindingsSummaryList(auditId);
		return ISOAuditFindingSummaryList;
	}

}
