package com.nfc.main.objects;

import java.io.Serializable;

public class ISOLoginValidate implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ecNo;
	private boolean isPlantLogin;
	private boolean isIsoLogin;
	private boolean isIsoAuditorLogin;

	public Integer getEcNo() {
		return ecNo;
	}

	public void setEcNo(Integer ecNo) {
		this.ecNo = ecNo;
	}

	public boolean isPlantLogin() {
		return isPlantLogin;
	}

	public void setPlantLogin(boolean isPlantLogin) {
		this.isPlantLogin = isPlantLogin;
	}

	public boolean isIsoLogin() {
		return isIsoLogin;
	}

	public void setIsoLogin(boolean isIsoLogin) {
		this.isIsoLogin = isIsoLogin;
	}

	public boolean isIsoAuditorLogin() {
		return isIsoAuditorLogin;
	}

	public void setIsoAuditorLogin(boolean isIsoAuditorLogin) {
		this.isIsoAuditorLogin = isIsoAuditorLogin;
	}

}
