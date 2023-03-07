package com.nfc.main.objects;

public class ISOAuditFindingClosure {
	private String auditId;
	private Integer plantCode;
	private Integer findingNo;

	private String comments;

	private Integer loginEcNo;

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

	public Integer getFindingNo() {
		return findingNo;
	}

	public void setFindingNo(Integer findingNo) {
		this.findingNo = findingNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getLoginEcNo() {
		return loginEcNo;
	}

	public void setLoginEcNo(Integer loginEcNo) {
		this.loginEcNo = loginEcNo;
	}

}
