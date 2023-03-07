package com.nfc.main.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nfc.main.dao.CommonUtilDAO;
import com.nfc.main.dao.ISOAuditDetailsDAO;
import com.nfc.main.entities.IsoAuditDetails;
import com.nfc.main.entities.IsoAuditDetailsPK;
import com.nfc.main.entities.ViewIsoAuditDetails;
import com.nfc.main.objects.ISOAuditDetailsObj;

public class ISOAuditDetailsController {

	public List<Map<String, Object>> getISODetailsList(String auditId) {
		List<Map<String, Object>> auditList = new ISOAuditDetailsDAO().getISOAuditDetailsList(auditId);
		return auditList;
	}

	public ViewIsoAuditDetails getIsoAuditDetails(String auditId, Integer plantCode) {
		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsDAO().getISOAuditDetails(auditId, plantCode);
		return isoAuditDetails;
	}

	public Response saveIsoAuditDetails(ISOAuditDetailsObj isoAuditDetails) {

		Response ResObj = new Response();

		if (validationCheck(isoAuditDetails, ResObj)) {

			IsoAuditDetails auditDetailsPojo = prepareAuditDetailsPojo(new IsoAuditDetails(), isoAuditDetails, true);
			int result = new ISOAuditDetailsDAO().saveIsoAuditDetailsPojo(auditDetailsPojo);

			ResObj.setResult(result);
			if (result == 1) {
				ResObj.setMessage("Audit Details Saved Successfully");
			} else if (result == 0) {
				ResObj.setMessage("Error occured while saving audit details");
			}
		}
		return ResObj;

	}

	private Boolean validationCheck(ISOAuditDetailsObj isoAuditDetails, Response response) {

		Boolean isDuplicateRecord = new ISOAuditDetailsDAO().checkDuplicateRecordsInISOAuditDetails(isoAuditDetails.getAuditId(),isoAuditDetails.getPlantCode());
		if (isDuplicateRecord) {
			response.setResult(0);
			response.setMessage("Audit Details already saved for this plant");
			return false;
		}
		return true;
	}

	public Response updateIsoAuditDetails(ISOAuditDetailsObj isoAuditDetails) {
		IsoAuditDetails isoAuditDetailsPojo = new ISOAuditDetailsDAO()
				.getIsoAudiDetailsPojo(isoAuditDetails.getAuditId(), isoAuditDetails.getPlantCode());
		IsoAuditDetails auditDetailsPojo = prepareAuditDetailsPojo(isoAuditDetailsPojo, isoAuditDetails, false);
		Integer result = new ISOAuditDetailsDAO().updateIsoAuditDetailsPojo(auditDetailsPojo);

		Response ResObj = new Response();
		ResObj.setResult(result);
		if (result == 1) {
			ResObj.setMessage("Audit Details updated Successfully");
		} else if (result == 0) {
			ResObj.setMessage("Error occured while updating audit details");
		}

		return ResObj;
	}

	private IsoAuditDetails prepareAuditDetailsPojo(IsoAuditDetails isoAuditDetailsPojo,
			ISOAuditDetailsObj isoAuditDetails, boolean isSave) {

		if (isSave) {

			IsoAuditDetailsPK id = new IsoAuditDetailsPK();
			id.setAuditId(isoAuditDetails.getAuditId());
			id.setPlantCode(isoAuditDetails.getPlantCode());

			isoAuditDetailsPojo.setIsoAuditDetailsPK(id);
			isoAuditDetailsPojo.setCreatedBy(isoAuditDetails.getLoginEcNo());
			isoAuditDetailsPojo.setCreatedDate(new Date());
		}

		isoAuditDetailsPojo.setAuditDate(isoAuditDetails.getAuditDate());
		isoAuditDetailsPojo.setAuditee1Ecno(isoAuditDetails.getAuditee1EcNo());
		isoAuditDetailsPojo.setAuditee2Ecno(isoAuditDetails.getAuditee2EcNo());
		isoAuditDetailsPojo.setAuditor1Ecno(isoAuditDetails.getAuditor1EcNo());
		isoAuditDetailsPojo.setAuditor2Ecno(isoAuditDetails.getAuditor2EcNo());
		isoAuditDetailsPojo.setAuditorComments(isoAuditDetails.getAuditorComments());
		isoAuditDetailsPojo.setDetailsOfNoteWorthy(isoAuditDetails.getDetailsOfNoteWorthy());

		isoAuditDetailsPojo.setLastUpdatedDate(new Date());
		isoAuditDetailsPojo.setLastUpdatedBy(isoAuditDetails.getLoginEcNo());

		return isoAuditDetailsPojo;
	}
	
	public List<Map<String, Object>> getISODetailsList(Integer loginEcNo) {
		String managingPlants = new CommonUtilDAO().getManagingPlantsString(loginEcNo);
		List<Map<String, Object>> auditList = new ISOAuditDetailsDAO().getISODetailsListForListOfPlants(managingPlants);
		return auditList;
	}

}
