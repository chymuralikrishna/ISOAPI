package com.nfc.main.objects;

public class ISOAuditorSave {

	private Integer auditorEcNo;
	private String role;
	private Integer forUnitCode;
	private Integer createdBy;

	public Integer getAuditorEcNo() {
		return auditorEcNo;
	}

	public void setAuditorEcNo(Integer auditorEcNo) {
		this.auditorEcNo = auditorEcNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getForUnitCode() {
		return forUnitCode;
	}

	public void setForUnitCode(Integer forUnitCode) {
		this.forUnitCode = forUnitCode;
	}

}
