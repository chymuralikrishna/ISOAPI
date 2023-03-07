package com.nfc.main.apiControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.Constants;
import com.nfc.main.controllers.ISOAuditFindingLogsController;
import com.nfc.main.controllers.ISOAuditFindingsController;
import com.nfc.main.controllers.Response;
import com.nfc.main.dao.CommonUtilDAO;
import com.nfc.main.entities.IsoAuditFindings;
import com.nfc.main.entities.IsoAuditFindingsLogs;
import com.nfc.main.objects.CorrectiveActionReportByAuditee;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.main.objects.ISOAuditFindingByAuditor;
import com.nfc.main.objects.RenderButtons;
import com.nfc.main.objects.SelectItem;

@CrossOrigin(origins = "*")
@RestController
public class ISOAuditFindingsAPIs {

	@RequestMapping(value = "/getAuditFindingTypes", method = RequestMethod.GET)
	public List<SelectItem> getISORolesList() {
		List<SelectItem> isoRoles = new ArrayList<>();
		isoRoles.add(new SelectItem(Constants.NC, Constants.NC));
		isoRoles.add(new SelectItem(Constants.O, Constants.O));
		isoRoles.add(new SelectItem(Constants.OI, Constants.OI));
		return isoRoles;
	}

	@RequestMapping(value = "/saveISOAuditFindingByAuditor", method = RequestMethod.POST)
	public Response saveIsoAuditFindingByAuditor(@RequestBody ISOAuditFindingByAuditor isoAuditFinding) {
		Response result = new ISOAuditFindingsController().saveISOAuditFindingByISOAuditor(isoAuditFinding);
		return result;
	}

	@RequestMapping(value = "/updateISOAuditFindingByAuditor", method = RequestMethod.POST)
	public Response updateIsoAuditFindingByAuditor(@RequestBody ISOAuditFindingByAuditor isoAuditFinding) {
		Response result = new ISOAuditFindingsController().updateISOAuditFindingByISOAuditor(isoAuditFinding);
		return result;
	}

	@RequestMapping(value = "/saveCorrectiveActionReport", method = RequestMethod.POST)
	public Response saveCorrectiveActionReport(@RequestBody CorrectiveActionReportByAuditee correctiveAction) {
		Response response = new ISOAuditFindingsController().saveCorrectiveActionReport(correctiveAction);
		return response;
	}

	@RequestMapping(value = "/getISOAuditFindingsList", method = RequestMethod.GET)
	public List<ISOAuditFinding> getISOAuditFindingsList(@RequestParam String auditId,
			@RequestParam Integer plantCode) {
		List<ISOAuditFinding> ISOAuditFindingList = new ISOAuditFindingsController().getAuditFindingList(plantCode,
				auditId);
		return ISOAuditFindingList;
	}

	@RequestMapping(value = "/getISOAuditFindingDetails", method = RequestMethod.GET)
	public IsoAuditFindings getISOAuditFindingDetails(@RequestParam String auditId, @RequestParam Integer plantCode,
			@RequestParam Integer findingNo) {
		IsoAuditFindings isoAuditFindings = new ISOAuditFindingsController().getISOAuditFindingDetails(auditId,
				plantCode, findingNo);
		return isoAuditFindings;
	}

	@RequestMapping(value = "/getISOAuditFindingsListForManagingPlants", method = RequestMethod.GET)
	public List<ISOAuditFinding> getISOAuditFindingsListForManagingPlants(@RequestParam Integer loginEcNo) {
		List<ISOAuditFinding> ISOAuditFindingList = new ISOAuditFindingsController().getAuditFindingList(loginEcNo);
		return ISOAuditFindingList;
	}

	@RequestMapping(value = "/getISOAuditFindingLogsList", method = RequestMethod.GET)
	public List<Map<String, Object>> getISOAuditFindingLogsList(@RequestParam String auditId,
			@RequestParam Integer plantCode, @RequestParam Integer findingNo) {
		List<Map<String, Object>> auditFindingLogs = new ISOAuditFindingLogsController()
				.getISOAuditFindingLogsList(auditId, plantCode, findingNo);
		return auditFindingLogs;
	}

	@RequestMapping(value = "/getRenderButtons", method = RequestMethod.GET)
	public RenderButtons getRenderButtons(@RequestParam String auditId, @RequestParam Integer plantCode,
			@RequestParam Integer findingNo,@RequestParam Integer loginEcNo,
			@RequestParam String loginRole) {

		RenderButtons renderButtons = new ISOAuditFindingsController().getRenderButtons(auditId, plantCode,
				findingNo,loginEcNo,loginRole);
		return renderButtons;

	}
	
	@RequestMapping(value = "/getISOAuditFindingsSummaryList", method = RequestMethod.GET)
	public List<ISOAuditFinding> getISOAuditFindingsSummaryList(@RequestParam String auditId) {
		List<ISOAuditFinding> ISOAuditFindingList = new ISOAuditFindingsController().getISOAuditFindingsSummaryList(auditId);
		return ISOAuditFindingList;
	}

}
