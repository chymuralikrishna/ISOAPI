package com.nfc.main.dao;

import com.nfc.util.HibernateQueryFusion;

public class ViewNfcUsersDAO {

	public boolean isOfficer(Integer ecNO) {
		String query = "select count(*) > 0 from view_nfcusers where ecno = " + ecNO + " and pay_category = '1'";
		boolean result = new HibernateQueryFusion().getBooleanValue(query);
		return result;

	}

	public boolean isISOOfficer(Integer ecNO) {
		String query = "select count(*) > 0 from view_nfcusers where ecno = " + ecNO + " "
				+ " and pay_category = '1' and plant_code = 94";
		boolean result = new HibernateQueryFusion().getBooleanValue(query);
		return result;

	}

	public boolean isISOManagingPlant(Integer ecNo) {
		String query = "select count(*) > 0 from est_desig_plant_code_mapping_dtl "
				+ " where plant_code = 94 and ecno = " + ecNo;
		boolean result = new HibernateQueryFusion().getBooleanValue(query);
		return result;
	}

	public String getUserName(Integer ecNo) {
		String query = "select username from view_nfcusers_all where ecno = " + ecNo;
		String result = new HibernateQueryFusion().getStringFromFussionSession(query);
		return result;
	}

}
