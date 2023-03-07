/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nfc.main.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

/**
 *
 * @author NFC
 */
@Embeddable
public class IsoAuditDetailsPK implements Serializable {

    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
    @Column(name = "audit_id")
    private String auditId;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "plant_code")
    private int plantCode;

    public IsoAuditDetailsPK() {
    }

    public IsoAuditDetailsPK(String auditId, int plantCode) {
        this.auditId = auditId;
        this.plantCode = plantCode;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditId != null ? auditId.hashCode() : 0);
        hash += (int) plantCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IsoAuditDetailsPK)) {
            return false;
        }
        IsoAuditDetailsPK other = (IsoAuditDetailsPK) object;
        if ((this.auditId == null && other.auditId != null) || (this.auditId != null && !this.auditId.equals(other.auditId))) {
            return false;
        }
        if (this.plantCode != other.plantCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.IsoAuditDetailsPK[ auditId=" + auditId + ", plantCode=" + plantCode + " ]";
    }
    
}
