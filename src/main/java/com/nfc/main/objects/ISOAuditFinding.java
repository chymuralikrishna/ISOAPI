package com.nfc.main.objects;

import java.util.Date;

public class ISOAuditFinding {

	private String auditId;
	private Integer plantCode;
	private Integer findingNumber;

	private String isoAuditFindingType;
	private String isoStandard;
	private String clause;

	private String requirement;
	private String failure;
	private String evidence;

	private String status;

	private Date auditDate;
	private String plantName;

	private Integer auditee1EcNo;
	private Integer auditee2EcNo;
	private Date proposedCompletionDate;

	private boolean isCorrectiveActionFiled;

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

	public Integer getFindingNumber() {
		return findingNumber;
	}

	public void setFindingNumber(Integer findingNumber) {
		this.findingNumber = findingNumber;
	}

	public String getIsoAuditFindingType() {
		return isoAuditFindingType;
	}

	public void setIsoAuditFindingType(String isoAuditFindingType) {
		this.isoAuditFindingType = isoAuditFindingType;
	}

	public String getIsoStandard() {
		return isoStandard;
	}

	public void setIsoStandard(String isoStandard) {
		this.isoStandard = isoStandard;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
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

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
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

	public Date getProposedCompletionDate() {
		return proposedCompletionDate;
	}

	public void setProposedCompletionDate(Date proposedCompletionDate) {
		this.proposedCompletionDate = proposedCompletionDate;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public boolean isCorrectiveActionFiled() {
		return isCorrectiveActionFiled;
	}

	public void setCorrectiveActionFiled(boolean isCorrectiveActionFiled) {
		this.isCorrectiveActionFiled = isCorrectiveActionFiled;
	}

}
