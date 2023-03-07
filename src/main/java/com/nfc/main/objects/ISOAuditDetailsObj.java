package com.nfc.main.objects;

import java.util.Date;

public class ISOAuditDetailsObj {

	private String auditId;
	private Date auditDate;
	private Integer plantCode;
	private String plantName;

	private Integer auditor1EcNo;
	private Integer auditor2EcNo;
	private Integer auditee1EcNo;
	private Integer auditee2EcNo;

	private String auditor1Name;
	private String auditor2Name;
	private String auditee1Name;
	private String auditee2Name;

	private String auditorComments;
	private String detailsOfNoteWorthy;
	private Integer numberOfNCs;
	private Integer numberOfNCsPending;

	private Integer loginEcNo;
	
	

	public ISOAuditDetailsObj() {
		
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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

	public Integer getAuditor1EcNo() {
		return auditor1EcNo;
	}

	public void setAuditor1EcNo(Integer auditor1EcNo) {
		this.auditor1EcNo = auditor1EcNo;
	}

	public Integer getAuditor2EcNo() {
		return auditor2EcNo;
	}

	public void setAuditor2EcNo(Integer auditor2EcNo) {
		this.auditor2EcNo = auditor2EcNo;
	}

	public Integer getAuditee1EcNo() {
		return auditee1EcNo;
	}

	public void setAuditee1EcNo(Integer auditee1EcNo) {
		this.auditee1EcNo = auditee1EcNo;
	}

	public Integer getAuditee2EcNo() {
		return auditee2EcNo;
	}

	public void setAuditee2EcNo(Integer auditee2EcNo) {
		this.auditee2EcNo = auditee2EcNo;
	}

	public String getAuditor1Name() {
		return auditor1Name;
	}

	public void setAuditor1Name(String auditor1Name) {
		this.auditor1Name = auditor1Name;
	}

	public String getAuditor2Name() {
		return auditor2Name;
	}

	public void setAuditor2Name(String auditor2Name) {
		this.auditor2Name = auditor2Name;
	}

	public String getAuditee1Name() {
		return auditee1Name;
	}

	public void setAuditee1Name(String auditee1Name) {
		this.auditee1Name = auditee1Name;
	}

	public String getAuditee2Name() {
		return auditee2Name;
	}

	public void setAuditee2Name(String auditee2Name) {
		this.auditee2Name = auditee2Name;
	}

	public String getAuditorComments() {
		return auditorComments;
	}

	public void setAuditorComments(String auditorComments) {
		this.auditorComments = auditorComments;
	}

	public Integer getNumberOfNCs() {
		return numberOfNCs;
	}

	public void setNumberOfNCs(Integer numberOfNCs) {
		this.numberOfNCs = numberOfNCs;
	}

	public Integer getNumberOfNCsPending() {
		return numberOfNCsPending;
	}

	public void setNumberOfNCsPending(Integer numberOfNCsPending) {
		this.numberOfNCsPending = numberOfNCsPending;
	}

	public Integer getLoginEcNo() {
		return loginEcNo;
	}

	public void setLoginEcNo(Integer loginEcNo) {
		this.loginEcNo = loginEcNo;
	}

	public String getDetailsOfNoteWorthy() {
		return detailsOfNoteWorthy;
	}

	public void setDetailsOfNoteWorthy(String detailsOfNoteWorthy) {
		this.detailsOfNoteWorthy = detailsOfNoteWorthy;
	}

}
