package com.nfc.main.apiControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.ISOAuditFindingsController;
import com.nfc.main.controllers.ISOAuditNCController;
import com.nfc.main.controllers.Response;
import com.nfc.main.objects.DashBoardCountForAuditors;
import com.nfc.main.objects.DashBoardCountsForPlants;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.main.objects.ISOAuditFindingClosure;
import com.nfc.main.objects.ISONCDetails;

@CrossOrigin(origins = "*")
@RestController
public class ISOAuditNCAPIs {

	@RequestMapping(value = "/getDashboardCountsForPlant", method = RequestMethod.GET)
	public DashBoardCountsForPlants getDashboardCountsForPlant(@RequestParam Integer loginEcNo) {
		DashBoardCountsForPlants dashBoardCount = new ISOAuditNCController().getDashboardCountsForPlant(loginEcNo);
		return dashBoardCount;
	}

	@RequestMapping(value = "/getDashboardCountsForAuditors", method = RequestMethod.GET)
	public DashBoardCountForAuditors getDashboardCountsForAuditors(@RequestParam Integer loginEcNo) {
		DashBoardCountForAuditors dashBoardCount = new ISOAuditNCController().getDashboardCountsForAuditors(loginEcNo);
		return dashBoardCount;
	}

	@RequestMapping(value = "/getDashboardCountsForISOIncharge", method = RequestMethod.GET)
	public DashBoardCountForAuditors getDashboardCountsForISOIncharge() {
		DashBoardCountForAuditors dashBoardCount = new ISOAuditNCController().getDashboardCountsForISOIncharge();
		return dashBoardCount;
	}

	@RequestMapping(value = "/getNCListPendingForCorrectiveAction", method = RequestMethod.GET)
	public List<ISOAuditFinding> getNCListPendingForCorrectiveAction(@RequestParam Integer loginEcNo) {
		List<ISOAuditFinding> ncListPendingForCorrectiveAction = new ISOAuditNCController()
				.getNCListPendingForCorrectiveAction(loginEcNo);
		return ncListPendingForCorrectiveAction;
	}

	@RequestMapping(value = "/getNCListPendingForManagingPlants", method = RequestMethod.GET)
	public List<ISOAuditFinding> getNCListPendingForManagingPlants(@RequestParam Integer loginEcNo) {
		List<ISOAuditFinding> ncListPendingForManagingPlants = new ISOAuditNCController()
				.getNCListPendingForManagingPlants(loginEcNo);
		return ncListPendingForManagingPlants;
	}

	@RequestMapping(value = "/getNCListSubmittedForApproval", method = RequestMethod.GET)
	public List<ISOAuditFinding> getNCListSubmittedForApproval(@RequestParam Integer loginEcNo,
			@RequestParam String loginRole) {
		List<ISOAuditFinding> ncListSubmittedForApproval = new ISOAuditNCController()
				.getNCListSubmittedForApproval(loginEcNo, loginRole);
		return ncListSubmittedForApproval;
	}

	@RequestMapping(value = "/submitForClosure", method = RequestMethod.POST)
	public Response submitForClosure(@RequestBody ISOAuditFindingClosure auditFindingClosure) {
		Response response = new ISOAuditNCController().submitForClosure(auditFindingClosure);
		return response;
	}

	@RequestMapping(value = "/recommendClosure", method = RequestMethod.POST)
	public Response recommendClosure(@RequestBody ISOAuditFindingClosure auditFindingClosure) {
		Response response = new ISOAuditNCController().recommendClosure(auditFindingClosure);
		return response;
	}

	@RequestMapping(value = "/approveClosure", method = RequestMethod.POST)
	public Response approveClosure(@RequestBody ISOAuditFindingClosure auditFindingClosure) {
		Response response = new ISOAuditNCController().approveClosure(auditFindingClosure);
		return response;
	}

	@RequestMapping(value = "/returnClosure", method = RequestMethod.POST)
	public Response returnClosure(@RequestBody ISOAuditFindingClosure auditFindingClosure) {
		Response response = new ISOAuditNCController().returnClosure(auditFindingClosure);
		return response;
	}

}
