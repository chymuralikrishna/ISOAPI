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

/**
 *
 * @author NFC
 */
@Entity
@Table(name = "iso_auditors")
@NamedQueries({ @NamedQuery(name = "IsoAuditors.findAll", query = "SELECT i FROM IsoAuditors i") })
public class IsoAuditors implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected IsoAuditorsPK isoAuditorsPK;
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name = "created_by")
	private Integer createdBy;

	public IsoAuditors() {
	}

	public IsoAuditors(IsoAuditorsPK isoAuditorsPK) {
		this.isoAuditorsPK = isoAuditorsPK;
	}

	public IsoAuditorsPK getIsoAuditorsPK() {
		return isoAuditorsPK;
	}

	public void setIsoAuditorsPK(IsoAuditorsPK isoAuditorsPK) {
		this.isoAuditorsPK = isoAuditorsPK;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (isoAuditorsPK != null ? isoAuditorsPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof IsoAuditors)) {
			return false;
		}
		IsoAuditors other = (IsoAuditors) object;
		if ((this.isoAuditorsPK == null && other.isoAuditorsPK != null)
				|| (this.isoAuditorsPK != null && !this.isoAuditorsPK.equals(other.isoAuditorsPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "test.IsoAuditors[ isoAuditorsPK=" + isoAuditorsPK + " ]";
	}

}
