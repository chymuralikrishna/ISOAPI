package com.nfc.main.apiControllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.ISOAuditDetailsController;
import com.nfc.main.controllers.Response;
import com.nfc.main.entities.ViewIsoAuditDetails;
import com.nfc.main.objects.ISOAuditDetailsObj;

@CrossOrigin(origins = "*")
@RestController
public class ISOAuditDetailsAPIs {

	@RequestMapping(value = "/getISOAuditDetailsList", method = RequestMethod.GET)
	List<Map<String, Object>> getISODetailsList(@RequestParam String auditId) {
		List<Map<String, Object>> auditList = new ISOAuditDetailsController().getISODetailsList(auditId);
		return auditList;
	}

	@RequestMapping(value = "/getISOAuditDetails", method = RequestMethod.GET)
	ViewIsoAuditDetails getIsoAuditDetails(@RequestParam String auditId, @RequestParam Integer plantCode) {
		ViewIsoAuditDetails isoAuditDetails = new ISOAuditDetailsController().getIsoAuditDetails(auditId, plantCode);
		return isoAuditDetails;
	}

	@RequestMapping(value = "/saveISOAuditDetails", method = RequestMethod.POST)
	public Response saveIsoAuditDetails(@RequestBody ISOAuditDetailsObj isoAuditDetails) {
		Response result = new ISOAuditDetailsController().saveIsoAuditDetails(isoAuditDetails);
		return result;

	}

	@RequestMapping(value = "/updateISOAuditDetails", method = RequestMethod.POST)
	public Response updateIsoAuditDetails(@RequestBody ISOAuditDetailsObj isoAuditDetails) {
		Response result = new ISOAuditDetailsController().updateIsoAuditDetails(isoAuditDetails);
		return result;
	}

	@RequestMapping(value = "/getISOAuditDetailsListForManagingPlants", method = RequestMethod.GET)
	List<Map<String, Object>> getISODetailsList(@RequestParam Integer loginEcNo) {
		List<Map<String, Object>> auditList = new ISOAuditDetailsController().getISODetailsList(loginEcNo);
		return auditList;
	}
}
