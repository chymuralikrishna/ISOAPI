package com.nfc.main.controllers;

import com.nfc.main.dao.ISOAuditorsDAO;
import com.nfc.main.dao.ViewNfcUsersDAO;
import com.nfc.main.objects.ISOLoginValidate;

public class ISOLoginController {

	public boolean isValidLogin(ISOLoginValidate isoLoginValidate) {
		boolean isValidLogin = false;
		Integer ecNo = isoLoginValidate.getEcNo();

		if (isoLoginValidate.isPlantLogin()) {
			// only officers should be able to login
			isValidLogin = new ViewNfcUsersDAO().isOfficer(ecNo);
		} else if (isoLoginValidate.isIsoLogin()) {
//			isValidLogin = new ViewNfcUsersDAO().isISOOfficer(ecNo) || new ViewNfcUsersDAO().isISOManagingPlant(ecNo);
			
			isValidLogin = new ISOAuditorsDAO().isISOIncharge(ecNo);
		} else if (isoLoginValidate.isIsoAuditorLogin()) {
			isValidLogin = new ISOAuditorsDAO().isISOAuditor(ecNo);
		}

		return isValidLogin;
	}

}
