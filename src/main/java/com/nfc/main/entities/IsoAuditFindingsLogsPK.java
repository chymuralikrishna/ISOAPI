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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

/**
 *
 * @author NFC
 */
@Embeddable
public class IsoAuditFindingsLogsPK implements Serializable {

    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 40)
    @Column(name = "audit_finding_id")
    private String auditFindingId;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public IsoAuditFindingsLogsPK() {
    }

    public IsoAuditFindingsLogsPK(String auditFindingId, Date createdDate) {
        this.auditFindingId = auditFindingId;
        this.createdDate = createdDate;
    }

    public String getAuditFindingId() {
        return auditFindingId;
    }

    public void setAuditFindingId(String auditFindingId) {
        this.auditFindingId = auditFindingId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditFindingId != null ? auditFindingId.hashCode() : 0);
        hash += (createdDate != null ? createdDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IsoAuditFindingsLogsPK)) {
            return false;
        }
        IsoAuditFindingsLogsPK other = (IsoAuditFindingsLogsPK) object;
        if ((this.auditFindingId == null && other.auditFindingId != null) || (this.auditFindingId != null && !this.auditFindingId.equals(other.auditFindingId))) {
            return false;
        }
        if ((this.createdDate == null && other.createdDate != null) || (this.createdDate != null && !this.createdDate.equals(other.createdDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.IsoAuditFindingsLogsPK[ auditFindingId=" + auditFindingId + ", createdDate=" + createdDate + " ]";
    }
    
}
