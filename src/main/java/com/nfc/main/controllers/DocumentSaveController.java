package com.nfc.main.controllers;

import java.util.Date;
import java.util.List;

import com.nfc.main.dao.DMSAttachmentsDAO;
import com.nfc.main.entities.DmsAttachments;

public class DocumentSaveController {

	public Response saveDocumentDetails(DmsAttachments dmsDocumentDetails) {
		dmsDocumentDetails.setUploadedOn(new Date());
		Integer result = new DMSAttachmentsDAO().saveDmsAttachmentsPojo(dmsDocumentDetails);

		Response response = new Response();
		response.setResult(result);

		if (result == 1) {
			response.setMessage("Document details saved succesfully");
		} else {
			response.setMessage("Error occured while saving document details");
		}

		return response;

	}

	public List<DmsAttachments> getDocumentDetailsList(String auditId, Integer plantCode, Integer findingNo) {
		List<DmsAttachments> attachmentDetailsList = new DMSAttachmentsDAO().getDocumentDetailsList(auditId, plantCode,
				findingNo);
		return attachmentDetailsList;
	}

}
