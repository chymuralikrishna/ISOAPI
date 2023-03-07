package com.nfc.main.apiControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.Constants;
import com.nfc.main.controllers.ISOAuditorsController;
import com.nfc.main.objects.ISOAuditorSave;
import com.nfc.main.objects.ISOAuditors;
import com.nfc.main.objects.SelectItem;

@CrossOrigin(origins = "*")
@RestController
public class ISOAuditorsAPIs {

//	@RequestMapping(value = "/getInchargeUnitList", method = RequestMethod.GET)
//	List<SelectItem> getInchargeUnitList(@RequestParam Integer loginEcNo) {
//		List<SelectItem> inchargeUnitList = new ISOAuditorsController().getInchargeUnitList(loginEcNo);
//		return inchargeUnitList;
//	}

	@RequestMapping(value = "/getISORolesList", method = RequestMethod.GET)
	List<SelectItem> getISORolesList() {
		List<SelectItem> isoRoles = new ArrayList<>();
		isoRoles.add(new SelectItem(Constants.ISOAUDITOR, Constants.ISOAUDITOR));
		isoRoles.add(new SelectItem(Constants.ISOINCHARGE, Constants.ISOINCHARGE));
		return isoRoles;
	}

	@RequestMapping(value = "/getISOAuditorsAndInchargeList", method = RequestMethod.GET)
	List<ISOAuditors> getISOAuditorsAndInchargeList(@RequestParam Integer unitCode) {
		List<ISOAuditors> isoAuditList = new ISOAuditorsController().getISOAuditorsAndInchargeList(unitCode);
		return isoAuditList;
	}

	@RequestMapping(value = "/saveNewISOAuditor", method = RequestMethod.POST)
	Integer saveISOAuditor(@RequestBody ISOAuditorSave isoAuditor) {
		Integer result = new ISOAuditorsController().saveISOAuditor(isoAuditor);
		return result;
	}

	@RequestMapping(value = "/deleteISOAuditor", method = RequestMethod.DELETE)
	Integer deleteISOAuditor(@RequestParam Integer auditorEcNo, @RequestParam String role) {
		Integer result = new ISOAuditorsController().deleteISOAuditor(auditorEcNo, role);
		return result;
	}

	@RequestMapping(value = "/getISOAuditorsList", method = RequestMethod.GET)
	List<SelectItem> getIsoAuditorsList(@RequestParam Integer unitCode) {
		List<SelectItem> isoAuditList = new ISOAuditorsController().getISOAuditorsList(unitCode);
		return isoAuditList;
	}

}
