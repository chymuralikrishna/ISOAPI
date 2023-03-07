package com.nfc.main.dao;

import java.util.List;
import java.util.Map;

import com.nfc.main.entities.IsoAuditFindingsLogs;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditFindingLogsDAO {

	public Integer saveISOAuditFindingLogsPojo(IsoAuditFindingsLogs pojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(pojo);
		return result;
	}

	public List<Map<String, Object>> getISOAuditFindingLogsList(String auditId, Integer plantCode, Integer findingNo) {
		String compositeId = auditId + "/" + plantCode + "/" + findingNo;
		String query = "select * from view_iso_logs where audit_finding_id = '"+compositeId+"'"
				+ " order by created_date desc";
		List<Map<String, Object>> auditLogs = new HibernateQueryISO().getJsonResult(query);
		return auditLogs;
	}

}
