package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nfc.main.controllers.Constants;
import com.nfc.main.objects.DashBoardCountForAuditors;
import com.nfc.main.objects.DashBoardCountsForPlants;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditNCDAO {

	public DashBoardCountsForPlants getDashboardCountsForPlant(String reportingEcNos, String managingPlants,
			boolean isManager) {
		
		DashBoardCountsForPlants dashBoardCount = new DashBoardCountsForPlants();

		String pendingNcCountQuery = "select cast(count(*) as int) from iso_audit_findings where "
				+ " finding_type_id in " + Constants.auditFindingTypes + " and plant_code in (" + managingPlants
				+ ") and " + " status != '" + Constants.CLOSED + "' and corrective_action_filed is true";

		Integer pendingNcCount = new HibernateQueryISO().getIntegerValue(pendingNcCountQuery);

		if (isManager) {
			String NCCountSubmittedForApprovalQuery = "select cast(count(*) as int) from iso_audit_findings where "
					+ " finding_type_id in " + Constants.auditFindingTypes + " and submitted_for_closure_by_ecno in ("
					+ reportingEcNos + ") and " + " status = '" + Constants.SUBMITTED + "'";
			Integer NCCountSubmittedForApproval = new HibernateQueryISO()
					.getIntegerValue(NCCountSubmittedForApprovalQuery);
			dashBoardCount.setNCCountSubmittedForApproval(NCCountSubmittedForApproval);
		}

		String correctiveActionForNCPendingCountQuery = "select cast(count(*) as int) from iso_audit_findings where "
				+ " finding_type_id in " + Constants.auditFindingTypes + " and plant_code in (" + managingPlants
				+ ") and " + " status = '" + Constants.OPEN + "' and corrective_action_filed is false";
		Integer correctiveActionForNCPendingCount = new HibernateQueryISO()
				.getIntegerValue(correctiveActionForNCPendingCountQuery);

		
		dashBoardCount.setManagingPlantsPendingNCCount(pendingNcCount);
		dashBoardCount.setCorrectiveActionForNCPendingCount(correctiveActionForNCPendingCount);

		return dashBoardCount;
	}

	public DashBoardCountForAuditors getDashBoardCountForAuditor(Integer auditorEcNo) {
		String query = "select cast(count(*) as int) from view_iso_findings where" + " finding_type_id in "
				+ Constants.auditFindingTypes + " and status = '" + Constants.RECOMMENDED + "'"
				+ " and ( auditor1_ecno = " + auditorEcNo + " or auditor2_ecno= " + auditorEcNo + ")";
		Integer count = new HibernateQueryISO().getIntegerValue(query);
		DashBoardCountForAuditors dashBoardCount = new DashBoardCountForAuditors();
		dashBoardCount.setNCCountSubmittedForClosure(count);
		return dashBoardCount;
	}

	public DashBoardCountForAuditors getDashBoardCountForISOIncharge() {
		String query = "select cast(count(*) as int) from iso_audit_findings where" + " finding_type_id in "
				+ Constants.auditFindingTypes + " and status = '" + Constants.RECOMMENDED + "'";
		Integer count = new HibernateQueryISO().getIntegerValue(query);
		DashBoardCountForAuditors dashBoardCount = new DashBoardCountForAuditors();
		dashBoardCount.setNCCountSubmittedForClosure(count);
		return dashBoardCount;
	}

	public List<ISOAuditFinding> getNCListPendingForCorrectiveAction(String managingPlants) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date,status from view_iso_findings ";

			String whereCondition = " where finding_type_id in " + Constants.auditFindingTypes + " and plant_code in ("
					+ managingPlants + ") and " + " status = '" + Constants.OPEN
					+ "' and corrective_action_filed is false";

			query = query + whereCondition;
			query = query + " order by audit_date desc";

			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;

	}

	public List<ISOAuditFinding> getNCListPendingForManagingPlants(String managingPlants) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date,status"
					+ " from view_iso_findings";

			String whereCondition = " where finding_type_id in " + Constants.auditFindingTypes + " and plant_code in ("
					+ managingPlants + ") and " + " status != '" + Constants.CLOSED
					+ "' and corrective_action_filed is true";

			query = query + whereCondition;
			query = query + " order by audit_date desc";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;

	}

	public List<ISOAuditFinding> getNCListSubmittedForApprovalToPlantManager(String reportingEcNos) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date, status"
					+ " from view_iso_findings";

			String whereCondition = " where finding_type_id in " + Constants.auditFindingTypes
					+ " and submitted_for_closure_by_ecno in (" + reportingEcNos + ") and " + " status = '"
					+ Constants.SUBMITTED + "'";

			query = query + whereCondition;
			query = query + " order by audit_date desc";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;

	}

	public List<ISOAuditFinding> getNCListSubmittedForApprovalToISOAuditor(Integer auditorEcNo) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date, status"
					+ " from view_iso_findings ";

			String whereCondition = " where finding_type_id in " + Constants.auditFindingTypes + " and status = '"
					+ Constants.RECOMMENDED + "' and ( auditor1_ecno = " + auditorEcNo + " or auditor2_ecno= "
					+ auditorEcNo + ")";

			query = query + whereCondition;
			query = query + " order by audit_date desc";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;

	}

	public List<ISOAuditFinding> getNCListSubmittedForApprovalToISOIncharge() {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date, status"
					+ " from view_iso_findings";

			String whereCondition = " where finding_type_id in " + Constants.auditFindingTypes + " and status = '"
					+ Constants.RECOMMENDED + "'";

			query = query + whereCondition;
			query = query + " order by audit_date desc";
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
			temp.setStatus((String) obj[10]);

			ISOAuditFindingList.add(temp);
		}

		return ISOAuditFindingList;
	}

}
