package com.nfc.main.apiControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.DocumentSaveController;
import com.nfc.main.controllers.Response;
import com.nfc.main.entities.DmsAttachments;


@CrossOrigin(origins = "*")
@RestController
public class DocumentSaveAPIs {

	@RequestMapping(value = "/saveDocumentDetails", method = RequestMethod.POST)
	public Response saveDocumentDetails(@RequestBody DmsAttachments dmsDocumentDetails) {
		Response result = new DocumentSaveController().saveDocumentDetails(dmsDocumentDetails);
		return result;
	}

	@RequestMapping(value = "/getDocumentDetailsList", method = RequestMethod.GET)
	public List<DmsAttachments> getDocumentDetailsList(@RequestParam String auditId, @RequestParam Integer plantCode,
			@RequestParam Integer findingNo) {
		List<DmsAttachments> attachmentDetailsList = new DocumentSaveController().getDocumentDetailsList(auditId,
				plantCode, findingNo);
		return attachmentDetailsList;
	}

}
