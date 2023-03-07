package com.nfc.main.objects;

import java.sql.Date;

public class CorrectiveActionReportByAuditee {

	private String auditId;
	private Integer plantCode;
	private Integer findingNo;

	private String proposedCorrection;
	private String rootCause;
	private String proposedCorrectiveAction;
	private Date proposedCompletionDate;

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

	public String getProposedCorrection() {
		return proposedCorrection;
	}

	public void setProposedCorrection(String proposedCorrection) {
		this.proposedCorrection = proposedCorrection;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getProposedCorrectiveAction() {
		return proposedCorrectiveAction;
	}

	public void setProposedCorrectiveAction(String proposedCorrectiveAction) {
		this.proposedCorrectiveAction = proposedCorrectiveAction;
	}

	public Date getProposedCompletionDate() {
		return proposedCompletionDate;
	}

	public void setProposedCompletionDate(Date proposedCompletionDate) {
		this.proposedCompletionDate = proposedCompletionDate;
	}

	public Integer getLoginEcNo() {
		return loginEcNo;
	}

	public void setLoginEcNo(Integer loginEcNo) {
		this.loginEcNo = loginEcNo;
	}

}
