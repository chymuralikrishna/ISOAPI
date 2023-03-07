package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.nfc.main.controllers.Constants;
import com.nfc.main.entities.IsoAuditors;
import com.nfc.main.objects.ISOAuditors;
import com.nfc.main.objects.SelectItem;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditorsDAO {

	public boolean isISOAuditor(Integer ecNO) {
		String query = "select count(*) > 0 from iso_auditors where ecno = " + ecNO + " and role_name = '"
				+ Constants.ISOAUDITOR + "'";
		boolean result = new HibernateQueryISO().getBooleanValue(query);
		return result;

	}

	public boolean isISOIncharge(Integer ecNO) {
		String query = "select count(*) > 0 from iso_auditors where ecno = " + ecNO + " and role_name = '"
				+ Constants.ISOINCHARGE + "'";
		boolean result = new HibernateQueryISO().getBooleanValue(query);
		return result;

	}

	public List<ISOAuditors> getISOAuditorsAndInchargeList(Integer unitCode) {

		List<ISOAuditors> ISOAuditorsList = new ArrayList<>();

		try {
			String query = "select ecno, role_name,username, plant_desc,plant_code,for_unit_desc  from view_iso_auditors"
					+ " where for_unit_code = " + unitCode;
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			for (Object[] obj : objList) {
				ISOAuditors temp = new ISOAuditors();

				temp.setAuditorEcNo((Integer) obj[0]);
				temp.setRole((String) obj[1]);
				temp.setAuditorName((String) obj[2]);
				temp.setPlantName((String) obj[3]);
				temp.setPlantCode((Integer) obj[4]);
				temp.setForUnitDescription((String) obj[5]);

				ISOAuditorsList.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditorsList;

	}

	public Integer saveISOAuditorPojo(IsoAuditors pojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(pojo);
		return result;
	}

	public Integer deleteISOAuditor(Integer auditorEcNo, String role) {
		String query = "Delete from iso_auditors where ecno = " + auditorEcNo + " and role_name = '" + role + "'";
		Integer result = new HibernateQueryISO().executeUpdateQueryOnTable(query);
		return result;
	}

	public List<SelectItem> getISOAuditorsList(Integer unitCode) {
		String query = "select username || '-' || ecno, ecno from view_iso_auditors where role_name = '" + Constants.ISOAUDITOR + "'"
				+ " and for_unit_code = " + unitCode;
		List<SelectItem> ISOAuditorsList = new HibernateQueryISO().getSelectItemListWithIntegerValueFromSession(query);
		return ISOAuditorsList;
	}

	public List<SelectItem> getInchargeUnitList(Integer loginEcNo) {
		String query = "select unit_desc, unit_code from cmn_unit_mst where unit_code in ("
				+ " select for_unit_code from  view_iso_auditors where ecno = " + loginEcNo + " )";
		List<SelectItem> unitList = new HibernateQueryISO().getSelectItemListWithIntegerValueFromSession(query);
		return unitList;
	}

}
