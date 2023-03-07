package com.nfc.main.apiControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.ISOAuditHdrController;
import com.nfc.main.objects.AuditHdrSaveAPIResponse;
import com.nfc.main.objects.ISOAudit;
import com.nfc.main.objects.SelectItem;

@RestController
@CrossOrigin(origins = "*")
public class ISOAuditHdrAPIs {

	@RequestMapping(value = "/getISOAuditList", method = RequestMethod.GET)
	public List<ISOAudit> getIsoAuditList(@RequestParam Integer unitCode) {
		List<ISOAudit> isoAuditList = new ISOAuditHdrController().getIsoAuditList(unitCode);
		return isoAuditList;
	}

	@RequestMapping(value = "/getISOAuditHdr", method = RequestMethod.GET)
	public ISOAudit getIsoAuditHeader(@RequestParam String auditId) {
		ISOAudit isoAuditHdr = new ISOAuditHdrController().getIsoAuditHdr(auditId);
		return isoAuditHdr;
	}

	@RequestMapping(value = "/saveISOAuditHdr", method = RequestMethod.POST)
	public AuditHdrSaveAPIResponse saveIsoAuditHeader(@RequestBody ISOAudit isoAuditHdr) {
		AuditHdrSaveAPIResponse result = new ISOAuditHdrController().saveIsoAuditHdr(isoAuditHdr);
		return result;
	}

	@RequestMapping(value = "/updateISOAuditHdr", method = RequestMethod.POST)
	public Integer updateIsoAuditHeader(@RequestBody ISOAudit isoAuditHdr) {
		Integer result = new ISOAuditHdrController().updateIsoAuditHdr(isoAuditHdr);
		return result;
	}

	@RequestMapping(value = "/getISOAuditIDList", method = RequestMethod.GET)
	public List<SelectItem> getIsoAuditIDList(@RequestParam Integer unitCode) {
		List<SelectItem> isoAuditList = new ISOAuditHdrController().getISOAuditIDList(unitCode);
		return isoAuditList;
	}
	
	@RequestMapping(value = "/getOpenISOAuditIDList", method = RequestMethod.GET)
	public List<SelectItem> getOpenISOAuditIDList(@RequestParam Integer unitCode) {
		List<SelectItem> isoAuditList = new ISOAuditHdrController().getOpenISOAuditIDList(unitCode);
		return isoAuditList;
	}

}
