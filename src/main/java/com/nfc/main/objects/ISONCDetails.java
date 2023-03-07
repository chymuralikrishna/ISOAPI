package com.nfc.main.objects;

import java.util.Date;

public class ISONCDetails {

	private String auditId;
	private Integer plantCode;
	private String plantName;
	private Integer findingNo;

	private String requirement;
	private String failure;
	private String status;

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Integer getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(Integer plantCode) {
		this.plantCode = plantCode;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public Integer getFindingNo() {
		return findingNo;
	}

	public void setFindingNo(Integer findingNo) {
		this.findingNo = findingNo;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
