/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nfc.main.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

/**
 *
 * @author NFC
 */
@Entity
@Table(name = "iso_audit_hdr")
@NamedQueries({ @NamedQuery(name = "IsoAuditHdr.findAll", query = "SELECT i FROM IsoAuditHdr i") })
public class IsoAuditHdr implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Size(max = 20)
	@Column(name = "audit_type")
	private String auditType;
	@Column(name = "audit_start_date")
	@Temporal(TemporalType.DATE)
	private Date auditStartDate;
	@Column(name = "audit_end_date")
	@Temporal(TemporalType.DATE)
	private Date auditEndDate;
	// @Size(max = 50)
	@Column(name = "audit_agency")
	private String auditAgency;
	@Id
	@Basic(optional = false)
	// @NotNull
	// @Size(min = 1, max = 20)
	@Column(name = "audit_id")
	private String auditId;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name = "last_updated_by")
	private Integer lastUpdatedBy;
	@Column(name = "last_updated_on")
	@Temporal(TemporalType.DATE)
	private Date lastUpdatedOn;
	// @Size(max = 10)
	@Column(name = "status")
	private String status;
	// @Size(max = 500)
	@Column(name = "audit_comments")
	private String auditComments;
	@Column(name = "unit_code")
	private Integer unitCode;

	public IsoAuditHdr() {
	}

	public IsoAuditHdr(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public Date getAuditStartDate() {
		return auditStartDate;
	}

	public void setAuditStartDate(Date auditStartDate) {
		this.auditStartDate = auditStartDate;
	}

	public Date getAuditEndDate() {
		return auditEndDate;
	}

	public void setAuditEndDate(Date auditEndDate) {
		this.auditEndDate = auditEndDate;
	}

	public String getAuditAgency() {
		return auditAgency;
	}

	public void setAuditAgency(String auditAgency) {
		this.auditAgency = auditAgency;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
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

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuditComments() {
		return auditComments;
	}

	public void setAuditComments(String auditComments) {
		this.auditComments = auditComments;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (auditId != null ? auditId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof IsoAuditHdr)) {
			return false;
		}
		IsoAuditHdr other = (IsoAuditHdr) object;
		if ((this.auditId == null && other.auditId != null)
				|| (this.auditId != null && !this.auditId.equals(other.auditId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "test.IsoAuditHdr[ auditId=" + auditId + " ]";
	}

}
