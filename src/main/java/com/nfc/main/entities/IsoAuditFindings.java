/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nfc.main.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.Size;

/**
 *
 * @author NFC
 */
@Entity
@Table(name = "iso_audit_findings")
@NamedQueries({ @NamedQuery(name = "IsoAuditFindings.findAll", query = "SELECT i FROM IsoAuditFindings i") })
public class IsoAuditFindings implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected IsoAuditFindingsPK isoAuditFindingsPK;
	// @Size(max = 20)
	@Column(name = "iso_standard")
	private String isoStandard;
	// @Size(max = 20)
	@Column(name = "clause")
	private String clause;
	// @Size(max = 2000)
	@Column(name = "requirement")
	private String requirement;
	// @Size(max = 2000)
	@Column(name = "failure")
	private String failure;
	// @Size(max = 2000)
	@Column(name = "evidence")
	private String evidence;
	// @Size(max = 2000)
	@Column(name = "proposed_correction")
	private String proposedCorrection;
	// @Size(max = 2000)
	@Column(name = "root_cause")
	private String rootCause;
	// @Size(max = 2000)
	@Column(name = "proposed_corrective_action")
	private String proposedCorrectiveAction;
	@Column(name = "proposed_completion_date")
	@Temporal(TemporalType.DATE)
	private Date proposedCompletionDate;
	// @Size(max = 10)
	@Column(name = "status")
	private String status;
	// @Size(max = 2000)
	@Column(name = "verification_of_corrective_action")
	private String verificationOfCorrectiveAction;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "last_updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;
	@Column(name = "last_updated_by")
	private Integer lastUpdatedBy;
	// @Size(max = 10)
	@Column(name = "finding_type_id")
	private String findingTypeId;
	@Column(name = "submitted_for_closure_by_ecno")
	private Integer submittedForClosureByEcno;
	@Column(name = "submitted_for_closure_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedForClosureOn;
	@Column(name = "recommended_for_closure_by_ecno")
	private Integer recommendedForClosureByEcno;
	@Column(name = "recommended_for_closure_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recommendedForClosureOn;
	@Column(name = "closure_approved_by_ecno")
	private Integer closureApprovedByEcno;
	@Column(name = "closure_approved_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closureApprovedOn;
	@Column(name = "corrective_action_filed")
	private Boolean correctiveActionFiled;
	@Column(name = "corrective_action_filed_by_ecno")
	private Integer correctiveActionFiledByEcno;
	@Column(name = "corrective_action_filed_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date correctiveActionFiledOn;
	// @Size(max = 100)
	@Column(name = "description")
	private String description;
	// @Size(max = 100)
	@Column(name = "dms_file_name")
	private String dmsFileName;

	public IsoAuditFindings() {
	}

	public IsoAuditFindings(IsoAuditFindingsPK isoAuditFindingsPK) {
		this.isoAuditFindingsPK = isoAuditFindingsPK;
	}

	public IsoAuditFindings(String auditId, int plantCode, int findingNo) {
		this.isoAuditFindingsPK = new IsoAuditFindingsPK(auditId, plantCode, findingNo);
	}

	public IsoAuditFindingsPK getIsoAuditFindingsPK() {
		return isoAuditFindingsPK;
	}

	public void setIsoAuditFindingsPK(IsoAuditFindingsPK isoAuditFindingsPK) {
		this.isoAuditFindingsPK = isoAuditFindingsPK;
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

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVerificationOfCorrectiveAction() {
		return verificationOfCorrectiveAction;
	}

	public void setVerificationOfCorrectiveAction(String verificationOfCorrectiveAction) {
		this.verificationOfCorrectiveAction = verificationOfCorrectiveAction;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getFindingTypeId() {
		return findingTypeId;
	}

	public void setFindingTypeId(String findingTypeId) {
		this.findingTypeId = findingTypeId;
	}

	public Integer getSubmittedForClosureByEcno() {
		return submittedForClosureByEcno;
	}

	public void setSubmittedForClosureByEcno(Integer submittedForClosureByEcno) {
		this.submittedForClosureByEcno = submittedForClosureByEcno;
	}

	public Date getSubmittedForClosureOn() {
		return submittedForClosureOn;
	}

	public void setSubmittedForClosureOn(Date submittedForClosureOn) {
		this.submittedForClosureOn = submittedForClosureOn;
	}

	public Integer getRecommendedForClosureByEcno() {
		return recommendedForClosureByEcno;
	}

	public void setRecommendedForClosureByEcno(Integer recommendedForClosureByEcno) {
		this.recommendedForClosureByEcno = recommendedForClosureByEcno;
	}

	public Date getRecommendedForClosureOn() {
		return recommendedForClosureOn;
	}

	public void setRecommendedForClosureOn(Date recommendedForClosureOn) {
		this.recommendedForClosureOn = recommendedForClosureOn;
	}

	public Integer getClosureApprovedByEcno() {
		return closureApprovedByEcno;
	}

	public void setClosureApprovedByEcno(Integer closureApprovedByEcno) {
		this.closureApprovedByEcno = closureApprovedByEcno;
	}

	public Date getClosureApprovedOn() {
		return closureApprovedOn;
	}

	public void setClosureApprovedOn(Date closureApprovedOn) {
		this.closureApprovedOn = closureApprovedOn;
	}

	public Boolean getCorrectiveActionFiled() {
		return correctiveActionFiled;
	}

	public void setCorrectiveActionFiled(Boolean correctiveActionFiled) {
		this.correctiveActionFiled = correctiveActionFiled;
	}

	public Integer getCorrectiveActionFiledByEcno() {
		return correctiveActionFiledByEcno;
	}

	public void setCorrectiveActionFiledByEcno(Integer correctiveActionFiledByEcno) {
		this.correctiveActionFiledByEcno = correctiveActionFiledByEcno;
	}

	public Date getCorrectiveActionFiledOn() {
		return correctiveActionFiledOn;
	}

	public void setCorrectiveActionFiledOn(Date correctiveActionFiledOn) {
		this.correctiveActionFiledOn = correctiveActionFiledOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDmsFileName() {
		return dmsFileName;
	}

	public void setDmsFileName(String dmsFileName) {
		this.dmsFileName = dmsFileName;
	}

}
