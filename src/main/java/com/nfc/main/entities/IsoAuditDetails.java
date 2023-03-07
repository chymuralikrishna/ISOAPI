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
@Table(name = "iso_audit_details")
@NamedQueries({ @NamedQuery(name = "IsoAuditDetails.findAll", query = "SELECT i FROM IsoAuditDetails i") })
public class IsoAuditDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected IsoAuditDetailsPK isoAuditDetailsPK;
	@Column(name = "auditor1_ecno")
	private Integer auditor1Ecno;
	@Column(name = "auditor2_ecno")
	private Integer auditor2Ecno;
	@Column(name = "auditee1_ecno")
	private Integer auditee1Ecno;
	@Column(name = "auditee2_ecno")
	private Integer auditee2Ecno;
	@Column(name = "audit_date")
	@Temporal(TemporalType.DATE)
	private Date auditDate;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "last_updated_by")
	private Integer lastUpdatedBy;
	@Column(name = "last_updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	// @Size(max = 2000)
	@Column(name = "auditor_comments")
	private String auditorComments;
	// @Size(max = 2000)
	@Column(name = "details_of_note_worthy")
	private String detailsOfNoteWorthy;

	public IsoAuditDetails() {
	}

	public IsoAuditDetails(IsoAuditDetailsPK isoAuditDetailsPK) {
		this.isoAuditDetailsPK = isoAuditDetailsPK;
	}

	public IsoAuditDetails(String auditId, int plantCode) {
		this.isoAuditDetailsPK = new IsoAuditDetailsPK(auditId, plantCode);
	}

	public IsoAuditDetailsPK getIsoAuditDetailsPK() {
		return isoAuditDetailsPK;
	}

	public void setIsoAuditDetailsPK(IsoAuditDetailsPK isoAuditDetailsPK) {
		this.isoAuditDetailsPK = isoAuditDetailsPK;
	}

	public Integer getAuditor1Ecno() {
		return auditor1Ecno;
	}

	public void setAuditor1Ecno(Integer auditor1Ecno) {
		this.auditor1Ecno = auditor1Ecno;
	}

	public Integer getAuditor2Ecno() {
		return auditor2Ecno;
	}

	public void setAuditor2Ecno(Integer auditor2Ecno) {
		this.auditor2Ecno = auditor2Ecno;
	}

	public Integer getAuditee1Ecno() {
		return auditee1Ecno;
	}

	public void setAuditee1Ecno(Integer auditee1Ecno) {
		this.auditee1Ecno = auditee1Ecno;
	}

	public Integer getAuditee2Ecno() {
		return auditee2Ecno;
	}

	public void setAuditee2Ecno(Integer auditee2Ecno) {
		this.auditee2Ecno = auditee2Ecno;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getAuditorComments() {
		return auditorComments;
	}

	public void setAuditorComments(String auditorComments) {
		this.auditorComments = auditorComments;
	}

	public String getDetailsOfNoteWorthy() {
		return detailsOfNoteWorthy;
	}

	public void setDetailsOfNoteWorthy(String detailsOfNoteWorthy) {
		this.detailsOfNoteWorthy = detailsOfNoteWorthy;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (isoAuditDetailsPK != null ? isoAuditDetailsPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof IsoAuditDetails)) {
			return false;
		}
		IsoAuditDetails other = (IsoAuditDetails) object;
		if ((this.isoAuditDetailsPK == null && other.isoAuditDetailsPK != null)
				|| (this.isoAuditDetailsPK != null && !this.isoAuditDetailsPK.equals(other.isoAuditDetailsPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "test.IsoAuditDetails[ isoAuditDetailsPK=" + isoAuditDetailsPK + " ]";
	}

}
