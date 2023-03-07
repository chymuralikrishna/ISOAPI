package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nfc.main.controllers.Constants;
import com.nfc.main.entities.IsoAuditFindings;
import com.nfc.main.objects.ISOAuditFinding;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditFindingsDAO {

	public Integer generateNewFindingNumber(String auditId, Integer plantCode) {
		String query = "select coalesce(max(finding_no),0) + 1 from iso_audit_findings " + " where audit_id = '"
				+ auditId + "' and plant_code = " + plantCode;
		Integer result = new HibernateQueryISO().getIntegerValue(query);
		return result;
	}

	@SuppressWarnings("unchecked")
	public IsoAuditFindings getISOAuditFindingPojo(String auditId, Integer plantCode, Integer NcNo) {
		String query = " from IsoAuditFindings where audit_id = '" + auditId + "' " + " and plant_code =" + plantCode
				+ " and finding_no =" + NcNo;
		List<IsoAuditFindings> pojoList = new HibernateQueryISO().createQueryAndGetList(query);

		if (pojoList != null && !pojoList.isEmpty()) {
			return pojoList.get(0);
		}
		return new IsoAuditFindings();
	}

	public Integer saveISOAuditFinding(IsoAuditFindings pojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(pojo);
		return result;
	}

	public Integer updateISOAuditFinding(IsoAuditFindings pojo) {
		Integer result = new HibernateQueryISO().saveOrUpdateDetailsToFusionTable(pojo);
		return result;
	}

	public List<ISOAuditFinding> getISOAuditFindingList(Integer plantCode, String auditId) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, status, corrective_action_filed, submitted_for_closure_by_ecno"
					+ " from iso_audit_findings where audit_id = '" + auditId + "' " + " and plant_code =" + plantCode
					+ " order by finding_no";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

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
				temp.setStatus((String) obj[8]);
				temp.setCorrectiveActionFiled((boolean) obj[9]);

				String status = temp.getStatus();

				switch (status) {

				case Constants.SUBMITTED:

					Integer submittedForClosureEcNo = (Integer) obj[10];
					String managerName = new CommonUtilDAO().getReportingManagerName(submittedForClosureEcNo);
					temp.setStatus(Constants.SUBMITTED + "( pending with " + managerName + ")");

					break;

				case Constants.RECOMMENDED:
					temp.setStatus(Constants.RECOMMENDED + "( pending with ISO Auditors )");
				default:
					break;
				}

				ISOAuditFindingList.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;
	}

	@SuppressWarnings("unchecked")
	public IsoAuditFindings getISOAuditFindingDetails(String auditId, Integer plantCode, Integer findingNo) {

		String query = " from IsoAuditFindings where audit_id = '" + auditId + "' and" + "  plant_code = " + plantCode
				+ " and finding_no = " + findingNo;
		List<IsoAuditFindings> auditFindingDetails = new HibernateQueryISO().createQueryAndGetList(query);

		if (auditFindingDetails != null && !auditFindingDetails.isEmpty())
			return auditFindingDetails.get(0);

		return new IsoAuditFindings();

	}

	public List<ISOAuditFinding> getISOAuditFindingListForListOfPlants(String managingPlants) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date, status, proposed_completion_date, "
					+ " submitted_for_closure_by_ecno from view_iso_findings where plant_code in (" + managingPlants
					+ ")" + " order by created_date desc";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			ISOAuditFindingList = getISOAuditFindingList(objList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditFindingList;
	}

	public List<ISOAuditFinding> getISOAuditFindingsSummaryList(String auditId) {

		List<ISOAuditFinding> ISOAuditFindingList = new ArrayList<>();

		try {
			String query = "select audit_id,plant_code,iso_standard,clause,requirement,"
					+ " failure,finding_no,finding_type_id, plant_short_desc, audit_date, status, proposed_completion_date, "
					+ " submitted_for_closure_by_ecno from view_iso_findings where audit_id = '" + auditId + "' "
					+ " order by plant_code";
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
			temp.setProposedCompletionDate((Date) obj[11]);

			String status = temp.getStatus();

			switch (status) {

			case Constants.SUBMITTED:

				Integer submittedForClosureEcNo = (Integer) obj[12];
				String managerName = new CommonUtilDAO().getReportingManagerName(submittedForClosureEcNo);
				temp.setStatus(Constants.SUBMITTED + "( pending with " + managerName + ")");

				break;

			case Constants.RECOMMENDED:
				temp.setStatus(Constants.RECOMMENDED + "( pending with ISO Auditors )");
			default:
				break;
			}

			ISOAuditFindingList.add(temp);
		}

		return ISOAuditFindingList;
	}

}
