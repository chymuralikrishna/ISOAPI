package com.nfc.main.dao;

import java.util.List;

import com.nfc.main.entities.DmsAttachments;
import com.nfc.util.HibernateQueryISO;

public class DMSAttachmentsDAO {

	public Integer saveDmsAttachmentsPojo(DmsAttachments dmsAttachmentPojo) {
		Integer result = new HibernateQueryISO().saveDetailsToFusionTable(dmsAttachmentPojo);
		return result;
	}

	public List<DmsAttachments> getDocumentDetailsList(String auditId, Integer plantCode, Integer findingNo) {
		String query = " from DmsAttachments where audit_id = '" + auditId + "' and plant_code =" + plantCode
				+ " and finding_no = " + findingNo;
		List<DmsAttachments> attachmentDetails = new HibernateQueryISO().createQueryAndGetList(query);
		return attachmentDetails;
	}

}
