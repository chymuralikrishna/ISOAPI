package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.nfc.main.controllers.Constants;
import com.nfc.util.HibernateQueryFusion;

public class CommonUtilDAO {

	public List<String> getListOfManagingPlants(Integer ecNo) {
		String query = "select cast(plant_code as string) from est_desig_plant_code_mapping_dtl where ecno = " + ecNo
				+ " union " + " select  cast(plant_code as string) from view_nfcusers where ecno = " + ecNo;
		List<String> plantCodes = new HibernateQueryFusion().getListOfStringFromFusion(query);
		return plantCodes;
	}

	public String getManagingPlantsString(Integer ecNo) {
		String query = "select cast(plant_code as string) from est_desig_plant_code_mapping_dtl where ecno = " + ecNo
				+ " union " + " select  cast(plant_code as string) from view_nfcusers where ecno = " + ecNo;
		List<String> plantCodes = new HibernateQueryFusion().getListOfStringFromFusion(query);

		String managingPlants = String.join(",", plantCodes);
		return managingPlants;
	}

	public String getReportingEmployeesEcNos(Integer ecNo) {
		String query = " select cast(ecno as String) from get_reporting_employee_list(" + ecNo + ")"
				+ " where depth!=0";

		List<String> ecNoList = new HibernateQueryFusion().getListOfStringFromFusion(query);

		if (ecNoList == null || ecNoList.size() == 0) {
			ecNoList = new ArrayList<>();
			ecNoList.add("'0'");
		}

		return String.join(",", ecNoList);
	}

	public boolean isManager(Integer loginEcNo, Integer submittedForClosureByEcNo) {

		String query = "select count(*)> 0 from  get_reporting_hierarchy_list(" + submittedForClosureByEcNo + ") "
				+ " where ( level_id <= 1400 or desig_code in " + Constants.DESIGNATIONS_TO_BE_TREATED_AS_MANAGER
				+ " ) " + " and reporting_ecno = " + loginEcNo;

		boolean result = new HibernateQueryFusion().getBooleanValue(query);
		return result;
	}

	public boolean isManager(Integer ecNo) {
		String query = "select count(*) > 0 from est_designation_mst "
				+ " where desig_code = (select desig_code from est_emp_assignment_det where ecno = " + ecNo + ") "
				+ " and ( level_id <=1400 or desig_code in " + Constants.DESIGNATIONS_TO_BE_TREATED_AS_MANAGER + ")";
		boolean result = new HibernateQueryFusion().getBooleanValue(query);
		return result;
	}

	public Integer getManagerEcNo(Integer ecNo) {
		String query = "select cast (reporting_ecno as Integer) from  get_reporting_hierarchy_list(" + ecNo + ") "
				+ " where level_id <= 1400 limit 1";
		Integer result = new HibernateQueryFusion().getIntegerValue(query);
		return result;
	}

	public String getReportingManagerName(Integer ecNo) {
		String query = "select username from view_nfcusers where ecno = ("
				+ " select reporting_ecno from get_reporting_hierarchy_list(" + ecNo + ")"
				+ " where  ( level_id <=1400 or desig_code in " + Constants.DESIGNATIONS_TO_BE_TREATED_AS_MANAGER + ") "
				+ " order by level_id desc limit 1 )";
		String result = new HibernateQueryFusion().getStringFromFussionSession(query);
		return result;
	}

}
