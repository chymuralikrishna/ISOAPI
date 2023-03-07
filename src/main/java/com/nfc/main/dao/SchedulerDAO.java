package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nfc.main.controllers.Constants;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.util.HibernateQueryFusion;
import com.nfc.util.HibernateQueryISO;

public class SchedulerDAO {

	public boolean getLock(String jobType, String author) {
		return new HibernateQueryFusion().getBooleanValue("select executescheduler from  execute_scheduler( '" + jobType + "', '" + author
				+ "','ISO', '" + new java.sql.Date(new Date().getTime()) + "')");
	}

	public List<ISOAuditFinding> getCorrectiveActionNotFiledISOAuditFindingList() {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select auditFinding.audit_id,auditFinding.plant_code,auditFinding.iso_standard,"
					+ " auditFinding.clause,auditFinding.requirement,auditFinding.failure,auditFinding.finding_no, "
					+ " auditFinding.finding_type_id, auditDtl.plant_short_desc, auditDtl.audit_date, "
					+ " auditDtl.auditee1_ecno, auditDtl.auditee2_ecno, auditFinding.proposed_completion_date, "
					+ " auditFinding.evidence,auditFinding.corrective_action_filed from  "
					+ " ( select * from iso_audit_findings  where corrective_action_filed is false "
					+ " and finding_type_id in " + Constants.auditFindingTypes + " ) auditFinding " + " left join "
					+ " view_iso_audit_details auditDtl "
					+ " on auditFinding.audit_id = auditDtl.audit_id and auditFinding.plant_code = auditDtl.plant_code";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;
	}

	public List<ISOAuditFinding> getISOAuditFindingListAboutToExpireIn7Days() {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select auditFinding.audit_id,auditFinding.plant_code,auditFinding.iso_standard,"
					+ " auditFinding.clause,auditFinding.requirement,auditFinding.failure,auditFinding.finding_no, "
					+ " auditFinding.finding_type_id, auditDtl.plant_short_desc, auditDtl.audit_date, "
					+ " auditDtl.auditee1_ecno, auditDtl.auditee2_ecno, auditFinding.proposed_completion_date, "
					+ " auditFinding.evidence,auditFinding.corrective_action_filed  from  "
					+ " ( select * from iso_audit_findings  where proposed_completion_date > current_date "
					+ " and proposed_completion_date < current_date + interval '7 days' and status = 'OPEN'"
					+ " and finding_type_id in " + Constants.auditFindingTypes + " ) auditFinding " + " left join "
					+ " view_iso_audit_details auditDtl "
					+ " on auditFinding.audit_id = auditDtl.audit_id and auditFinding.plant_code = auditDtl.plant_code";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;
	}

	public List<ISOAuditFinding> getExpiredISOAuditFindingList() {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select auditFinding.audit_id,auditFinding.plant_code,auditFinding.iso_standard,"
					+ " auditFinding.clause,auditFinding.requirement,auditFinding.failure,auditFinding.finding_no, "
					+ " auditFinding.finding_type_id, auditDtl.plant_short_desc, auditDtl.audit_date, "
					+ " auditDtl.auditee1_ecno, auditDtl.auditee2_ecno, auditFinding.proposed_completion_date,"
					+ " auditFinding.evidence,auditFinding.corrective_action_filed  from  "
					+ " ( select * from iso_audit_findings  where proposed_completion_date < current_date "
					+ "  and status = 'OPEN' and finding_type_id in " + Constants.auditFindingTypes + " ) auditFinding "
					+ " left join " + " view_iso_audit_details auditDtl "
					+ " on auditFinding.audit_id = auditDtl.audit_id and auditFinding.plant_code = auditDtl.plant_code";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;
	}

	private List<ISOAuditFinding> getISOAuditFindingList(List<Object[]> objList) {
		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		for (Object[] obj : objList) {
			ISOAuditFinding temp = new ISOAuditFinding();

			temp.setAuditId((String) obj[0]);
			temp.setPlantCode((Integer) obj[1]);
			temp.setIsoStandard((String) obj[2]);
			temp.setClause((String) obj[3]);
			temp.setRequirement((String) obj[4]);
			temp.setFailure((String) obj[5]);
			temp.setFindingNumber((Integer) obj[6]);
			temp.setIsoAuditFindingType((String) obj[7]);
			temp.setPlantName((String) obj[8]);
			temp.setAuditDate((Date) obj[9]);
			temp.setAuditee1EcNo((Integer) obj[10]);
			temp.setAuditee2EcNo((Integer) obj[11]);
			temp.setProposedCompletionDate((Date) obj[12]);
			temp.setEvidence((String) obj[13]);
			temp.setCorrectiveActionFiled((boolean) obj[14]);

			ISOAuditFindingList.add(temp);
		}

		return ISOAuditFindingList;
	}

}
