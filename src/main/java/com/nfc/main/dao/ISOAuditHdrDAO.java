package com.nfc.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nfc.main.entities.IsoAuditHdr;
import com.nfc.main.objects.ISOAudit;
import com.nfc.main.objects.SelectItem;
import com.nfc.util.HibernateQueryISO;

public class ISOAuditHdrDAO {

	public Integer getNextSlNoOfAudit(String auditType, Integer year) {
		String query = "select cast(count(*) as Integer) from iso_audit_hdr "
				+ " where date_part('year',audit_start_date) = " + year + " and audit_type = '" + auditType + "'";
		Integer result = new HibernateQueryISO().getIntegerValue(query);
		return result + 1;

	}

	public List<ISOAudit> getIsoAuditList(Integer unitCode) {

		List<ISOAudit> ISOAuditList = new ArrayList<>();

		try {
			String query = "select audit_type,audit_start_date, audit_end_date,audit_id,audit_agency, status "
					+ " from iso_audit_hdr where unit_code = " + unitCode + " order by audit_start_date desc, audit_end_date desc";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			for (Object[] obj : objList) {
				ISOAudit temp = new ISOAudit();

				temp.setAuditType((String) obj[0]);
				temp.setStartDate((Date) obj[1]);
				temp.setEndDate((Date) obj[2]);
				temp.setAuditId((String) obj[3]);
				temp.setAuditAgency((String) obj[4]);
				temp.setStatus((String) obj[5]); 

				ISOAuditList.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ISOAuditList;
	}

	public ISOAudit getIsoAuditHdr(String auditId) {
		List<ISOAudit> ISOAuditList = new ArrayList<>();

		try {
			String query = "select audit_type,audit_start_date, audit_end_date,audit_id,audit_agency,audit_comments,"
					+ " status, unit_code " + " from iso_audit_hdr where audit_id = '" + auditId + "'";
			List<Object[]> objList = new HibernateQueryISO().getQueryResultFromSession(query);

			for (Object[] obj : objList) {
				ISOAudit temp = new ISOAudit();

				temp.setAuditType((String) obj[0]);
				temp.setStartDate((Date) obj[1]);
				temp.setEndDate((Date) obj[2]);
				temp.setAuditId((String) obj[3]);
				temp.setAuditAgency((String) obj[4]);
				temp.setAuditComments((String) obj[5]);
				temp.setStatus((String) obj[6]);
				temp.setUnitCode((Integer) obj[7]);

				ISOAuditList.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ISOAuditList != null && !ISOAuditList.isEmpty()) {
			return ISOAuditList.get(0);
		}

		return new ISOAudit();
	}

	@SuppressWarnings("unchecked")
	public IsoAuditHdr getIsoAuditHdrPojo(String auditId) {
		String query = " from IsoAuditHdr where audit_id = '" + auditId + "'";
		List<IsoAuditHdr> pojoList = new HibernateQueryISO().createQueryAndGetList(query);

		if (pojoList != null && !pojoList.isEmpty()) {
			return pojoList.get(0);
		}

		return new IsoAuditHdr();

	}

	public Integer saveIsoAuditHdr(IsoAuditHdr isoAuditHdrPojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(isoAuditHdrPojo);
		return result;
	}

	public Integer updateIsoAuditHdr(IsoAuditHdr isoAuditHdrPojo) {
		Integer result = new HibernateQueryISO().saveOrUpdateDetailsToFusionTable(isoAuditHdrPojo);
		return result;
	}

	public List<SelectItem> getISOAuditIDList(Integer unitCode) {
		String query = "select audit_id,audit_id from iso_audit_hdr " + " where unit_code = " + unitCode
				+ " order by audit_start_date desc";
		List<SelectItem> auditIdList = new HibernateQueryISO().getSelectItemListFromSession(query);
		return auditIdList;
	}
	
	public List<SelectItem> getOpenISOAuditIDList(Integer unitCode) {
		String query = "select audit_id,audit_id from iso_audit_hdr " + " where unit_code = " + unitCode
				+ " and status = 'Open' order by audit_start_date desc";
		List<SelectItem> auditIdList = new HibernateQueryISO().getSelectItemListFromSession(query);
		return auditIdList;
	}

}
