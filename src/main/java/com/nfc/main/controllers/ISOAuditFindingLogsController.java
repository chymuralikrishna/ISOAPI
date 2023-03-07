package com.nfc.main.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nfc.main.dao.ISOAuditFindingLogsDAO;
import com.nfc.main.entities.IsoAuditFindingsLogs;
import com.nfc.main.entities.IsoAuditFindingsLogsPK;
import com.nfc.main.objects.RenderButtons;

public class ISOAuditFindingLogsController {

	public Integer saveISOAuditFindingLogs(String compositeId, Integer loginEcNo, String description, String remarks) {
		IsoAuditFindingsLogs pojo = new IsoAuditFindingsLogs();

		IsoAuditFindingsLogsPK id = new IsoAuditFindingsLogsPK();
		id.setAuditFindingId(compositeId);
		id.setCreatedDate(new Date());

		pojo.setIsoAuditFindingsLogsPK(id);
		pojo.setCreatedBy(loginEcNo);
		pojo.setDescription(description);
		pojo.setRemarks(remarks);

		Integer result = new ISOAuditFindingLogsDAO().saveISOAuditFindingLogsPojo(pojo);
		return result;

	}

	public List<Map<String, Object>> getISOAuditFindingLogsList(String auditId, Integer plantCode, Integer findingNo) {
		List<Map<String, Object>> auditLogs = new ISOAuditFindingLogsDAO().getISOAuditFindingLogsList(auditId,
				plantCode, findingNo);
		return auditLogs;
	}
	

}
