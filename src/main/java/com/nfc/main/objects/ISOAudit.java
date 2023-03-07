package com.nfc.main.objects;

import java.util.Date;

public class ISOAudit {

	private String auditId;
	private String auditType;
	private Date startDate;
	private Date endDate;
	private String auditAgency;
	private String auditComments;
	private String status;

	private Integer createdBy;
	private Integer unitCode;
	private Integer loginEcNo;

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAuditAgency() {
		return auditAgency;
	}

	public void setAuditAgency(String auditAgency) {
		this.auditAgency = auditAgency;
	}

	public String getAuditComments() {
		return auditComments;
	}

	public void setAuditComments(String auditComments) {
		this.auditComments = auditComments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public Integer getLoginEcNo() {
		return loginEcNo;
	}

	public void setLoginEcNo(Integer loginEcNo) {
		this.loginEcNo = loginEcNo;
	}

}
