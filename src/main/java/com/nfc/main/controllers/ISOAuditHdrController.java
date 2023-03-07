package com.nfc.main.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.nfc.main.dao.ISOAuditHdrDAO;
import com.nfc.main.entities.IsoAuditHdr;
import com.nfc.main.objects.AuditHdrSaveAPIResponse;
import com.nfc.main.objects.ISOAudit;
import com.nfc.main.objects.SelectItem;

public class ISOAuditHdrController {

	public List<ISOAudit> getIsoAuditList(Integer unitCode) {
		List<ISOAudit> isoAuditList = new ISOAuditHdrDAO().getIsoAuditList(unitCode);
		return isoAuditList;
	}

	public ISOAudit getIsoAuditHdr(String auditId) {
		ISOAudit isoAuditHdr = new ISOAuditHdrDAO().getIsoAuditHdr(auditId);
		return isoAuditHdr;
	}

	public AuditHdrSaveAPIResponse saveIsoAuditHdr(ISOAudit isoAuditHdr) {
		IsoAuditHdr isoAuditHdrPojo = prepareAuditHdrPojo(new IsoAuditHdr(), isoAuditHdr, true);
		Integer result = new ISOAuditHdrDAO().saveIsoAuditHdr(isoAuditHdrPojo);

		AuditHdrSaveAPIResponse response = new AuditHdrSaveAPIResponse();
		response.setAuditId(isoAuditHdrPojo.getAuditId());
		response.setResult(result);

		if (result > 0) {
			response.setMessage("ISO Audit Created succesfully");
		} else {
			response.setMessage("Error occured while creating ISO Audit");
		}

		return response;
	}

	public Integer updateIsoAuditHdr(ISOAudit isoAuditHdr) {

		IsoAuditHdr isoAuditHdrPojo = new ISOAuditHdrDAO().getIsoAuditHdrPojo(isoAuditHdr.getAuditId());
		isoAuditHdrPojo = prepareAuditHdrPojo(isoAuditHdrPojo, isoAuditHdr, false);

		Integer result = new ISOAuditHdrDAO().updateIsoAuditHdr(isoAuditHdrPojo);
		return result;
	}

	private IsoAuditHdr prepareAuditHdrPojo(IsoAuditHdr isoAuditHdrPojo, ISOAudit isoAuditHdr, boolean isSave) {

		if (isSave) {

			String newAuditId = generateNewAuditId(isoAuditHdr.getAuditType());

			isoAuditHdrPojo.setAuditId(newAuditId);
			isoAuditHdrPojo.setCreatedBy(isoAuditHdr.getLoginEcNo());
			isoAuditHdrPojo.setCreatedDate(new Date());
			isoAuditHdrPojo.setUnitCode(isoAuditHdr.getUnitCode());
		}

		isoAuditHdrPojo.setAuditStartDate(isoAuditHdr.getStartDate());
		isoAuditHdrPojo.setAuditEndDate(isoAuditHdr.getEndDate());
		isoAuditHdrPojo.setAuditType(isoAuditHdr.getAuditType());
		isoAuditHdrPojo.setAuditComments(isoAuditHdr.getAuditComments());
		isoAuditHdrPojo.setAuditAgency(isoAuditHdr.getAuditAgency());
		isoAuditHdrPojo.setStatus(isoAuditHdr.getStatus());

		isoAuditHdrPojo.setLastUpdatedBy(isoAuditHdr.getLoginEcNo());
		isoAuditHdrPojo.setLastUpdatedOn(new Date());
		return isoAuditHdrPojo;
	}

	private String generateNewAuditId(String auditType) {
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer slNo = new ISOAuditHdrDAO().getNextSlNoOfAudit(auditType, year);

		// for internal audit:- ISO/IA/YEAR/SLNO
		// FOR EXTERNAL AUDIT:- ISO/IA/YEAR/SLNO
		String newAuditId = "ISO/" + auditType.charAt(0) + "A/" + year + "/" + slNo;
		return newAuditId;

	}

	public List<SelectItem> getISOAuditIDList(Integer unitCode) {
		List<SelectItem> auditIdList = new ISOAuditHdrDAO().getISOAuditIDList(unitCode);
		return auditIdList;
	}
	
	public List<SelectItem> getOpenISOAuditIDList(Integer unitCode) {
		List<SelectItem> auditIdList = new ISOAuditHdrDAO().getOpenISOAuditIDList(unitCode);
		return auditIdList;
	}

}
