package com.nfc.main.apiControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nfc.main.controllers.ISOLoginController;
import com.nfc.main.objects.ISOLoginValidate;

@CrossOrigin(origins = "*")
@RestController
public class ISOLoginAPI {

	@RequestMapping(value = "/isValidLogin", method = RequestMethod.POST)
	boolean isValidLogin(@RequestBody ISOLoginValidate isoLoginValidate) {
		boolean isValidLogin = new ISOLoginController().isValidLogin(isoLoginValidate);
		return isValidLogin;
	}

}
