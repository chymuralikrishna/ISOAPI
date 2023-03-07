package com.nfc.main.controllers;

import java.util.Date;
import java.util.List;

import com.nfc.main.dao.ISOAuditorsDAO;
import com.nfc.main.entities.IsoAuditors;
import com.nfc.main.entities.IsoAuditorsPK;
import com.nfc.main.objects.ISOAuditorSave;
import com.nfc.main.objects.ISOAuditors;
import com.nfc.main.objects.SelectItem;

public class ISOAuditorsController {

	public List<ISOAuditors> getISOAuditorsAndInchargeList(Integer unitCode) {
		List<ISOAuditors> isoAuditorsList = new ISOAuditorsDAO().getISOAuditorsAndInchargeList(unitCode);
		return isoAuditorsList;
	}

	public Integer saveISOAuditor(ISOAuditorSave isoAuditor) {
		IsoAuditors isoAuditorPojo = prepareISOAuditorPojo(isoAuditor);
		Integer result = new ISOAuditorsDAO().saveISOAuditorPojo(isoAuditorPojo);
		return result;
	}

	private IsoAuditors prepareISOAuditorPojo(ISOAuditorSave isoAuditor) {
		IsoAuditors isoAuditorPojo = new IsoAuditors();
		IsoAuditorsPK pk = new IsoAuditorsPK();
		pk.setEcno(isoAuditor.getAuditorEcNo());
		pk.setRoleName(isoAuditor.getRole());
		pk.setForUnitCode(isoAuditor.getForUnitCode());
		
		isoAuditorPojo.setIsoAuditorsPK(pk);
		isoAuditorPojo.setCreatedBy(isoAuditor.getCreatedBy());
		isoAuditorPojo.setCreatedDate(new Date());
		

		return isoAuditorPojo;
	}

	public Integer deleteISOAuditor(Integer auditorEcNo,String role) {
		Integer result = new ISOAuditorsDAO().deleteISOAuditor(auditorEcNo, role);
		return result;
	}
	
	public List<SelectItem> getISOAuditorsList(Integer unitCode) {
		List<SelectItem> isoAuditorsList = new ISOAuditorsDAO().getISOAuditorsList(unitCode);
		return isoAuditorsList;
	}
	
	public List<SelectItem> getInchargeUnitList(Integer loginEcNo) {
		List<SelectItem> inchargeUnitList = new ISOAuditorsDAO().getInchargeUnitList(loginEcNo);
		return inchargeUnitList;
	}

}
