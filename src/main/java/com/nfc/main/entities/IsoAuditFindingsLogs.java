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
import javax.persistence.EmbeddedId;
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
@Table(name = "iso_audit_findings_logs")
@NamedQueries({
    @NamedQuery(name = "IsoAuditFindingsLogs.findAll", query = "SELECT i FROM IsoAuditFindingsLogs i")})
public class IsoAuditFindingsLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IsoAuditFindingsLogsPK isoAuditFindingsLogsPK;
    @Column(name = "created_by")
    private Integer createdBy;
//    @Size(max = 500)
    @Column(name = "remarks")
    private String remarks;
//    @Size(max = 100)
    @Column(name = "description")
    private String description;

    public IsoAuditFindingsLogs() {
    }

    public IsoAuditFindingsLogs(IsoAuditFindingsLogsPK isoAuditFindingsLogsPK) {
        this.isoAuditFindingsLogsPK = isoAuditFindingsLogsPK;
    }

    public IsoAuditFindingsLogs(String auditFindingId, Date createdDate) {
        this.isoAuditFindingsLogsPK = new IsoAuditFindingsLogsPK(auditFindingId, createdDate);
    }

    public IsoAuditFindingsLogsPK getIsoAuditFindingsLogsPK() {
        return isoAuditFindingsLogsPK;
    }

    public void setIsoAuditFindingsLogsPK(IsoAuditFindingsLogsPK isoAuditFindingsLogsPK) {
        this.isoAuditFindingsLogsPK = isoAuditFindingsLogsPK;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isoAuditFindingsLogsPK != null ? isoAuditFindingsLogsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IsoAuditFindingsLogs)) {
            return false;
        }
        IsoAuditFindingsLogs other = (IsoAuditFindingsLogs) object;
        if ((this.isoAuditFindingsLogsPK == null && other.isoAuditFindingsLogsPK != null) || (this.isoAuditFindingsLogsPK != null && !this.isoAuditFindingsLogsPK.equals(other.isoAuditFindingsLogsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.IsoAuditFindingsLogs[ isoAuditFindingsLogsPK=" + isoAuditFindingsLogsPK + " ]";
    }
    
}
