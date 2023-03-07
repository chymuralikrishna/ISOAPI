package com.nfc.main.dao;

import java.util.List;
import java.util.Map;

import com.nfc.main.entities.IsoAuditDetails;
import com.nfc.main.entities.ViewIsoAuditDetails;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditDetailsDAO {

	public List<Map<String, Object>> getISOAuditDetailsList(String auditId) {
		String query = "select * from view_iso_audit_details where audit_id = '" + auditId + "'"
				+ " order by audit_date desc";
		List<Map<String, Object>> auditList = new HibernateQueryISO().getJsonResult(query);
		return auditList;
	}

	public ViewIsoAuditDetails getISOAuditDetails(String auditId, Integer plantCode) {
		String query = " from ViewIsoAuditDetails where audit_id = '" + auditId + "' and plant_code = " + plantCode;
		List<ViewIsoAuditDetails> auditList = new HibernateQueryISO().createQueryAndGetList(query);

		if (auditList != null && !auditList.isEmpty()) {
			return auditList.get(0);
		}

		return new ViewIsoAuditDetails();
	}

	public IsoAuditDetails getIsoAudiDetailsPojo(String auditId, Integer plantCode) {
		String query = " from IsoAuditDetails where audit_id = '" + auditId + "' and plant_code= " + plantCode;
		List<IsoAuditDetails> auditDetailsList = new HibernateQueryISO().createQueryAndGetList(query);

		if (auditDetailsList != null && !auditDetailsList.isEmpty()) {
			return auditDetailsList.get(0);
		}
		return new IsoAuditDetails();
	}

	public Integer saveIsoAuditDetailsPojo(IsoAuditDetails pojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(pojo);
		return result;
	}

	public Integer updateIsoAuditDetailsPojo(IsoAuditDetails pojo) {
		Integer result = new HibernateQueryISO().saveOrUpdateDetailsToFusionTable(pojo);
		return result;
	}

	public Boolean checkDuplicateRecordsInISOAuditDetails(String auditId, Integer plantCode) {
		String query = "select count(*) > 0  from iso_audit_details" + " where audit_id='" + auditId
				+ "' and plant_code='" + plantCode + "'";
		Boolean result = new HibernateQueryISO().getBooleanValue(query);
		return result;
	}

	public List<Map<String, Object>> getISODetailsListForListOfPlants(String managingPlants) {
		String query = "select * from view_iso_audit_details where plant_code in (" + managingPlants + ")"
				+ " order by audit_date desc";
		List<Map<String, Object>> auditList = new HibernateQueryISO().getJsonResult(query);
		return auditList;
	}

	public boolean isAuditor(String auditId, Integer plantCode, Integer loginEcNo) {
		String query = "select count(*) > 0 from iso_audit_details " + " where audit_id = '" + auditId
				+ "' and plant_code  = " + plantCode + " " + " and (auditor1_ecno = " + loginEcNo
				+ " or auditor2_ecno = " + loginEcNo + ")";
		boolean result = new HibernateQueryISO().getBooleanValue(query);
		return result;
	}
}
