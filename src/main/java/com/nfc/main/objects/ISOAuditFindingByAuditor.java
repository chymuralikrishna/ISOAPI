package com.nfc.main.objects;

public class ISOAuditFindingByAuditor {

	private String auditId;
	private Integer plantCode;
	private Integer findingNumber;

	private String isoAuditFindingType;
	private String isoStandard;
	private String clause;
	private String requirement;
	private String failure;
	private String evidence;

	private Integer loginEcNo;

	private String dmsFileName;
	private String documentDescription;

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

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

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

	public Integer getLoginEcNo() {
		return loginEcNo;
	}

	public void setLoginEcNo(Integer loginEcNo) {
		this.loginEcNo = loginEcNo;
	}

	public String getIsoAuditFindingType() {
		return isoAuditFindingType;
	}

	public void setIsoAuditFindingType(String isoAuditFindingType) {
		this.isoAuditFindingType = isoAuditFindingType;
	}

	public String getDmsFileName() {
		return dmsFileName;
	}

	public void setDmsFileName(String dmsFileName) {
		this.dmsFileName = dmsFileName;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

}
