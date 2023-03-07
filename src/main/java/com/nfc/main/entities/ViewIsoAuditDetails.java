package com.nfc.main.entities;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "view_iso_audit_details")
@NamedQueries({ @NamedQuery(name = "ViewIsoAuditDetails.findAll", query = "SELECT v FROM ViewIsoAuditDetails v") })
public class ViewIsoAuditDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Size(max = 20)
	@Id
	@Column(name = "audit_id")
	private String auditId;
	@Column(name = "plant_code")
	private Integer plantCode;
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
	// @Size(max = 20)
	@Column(name = "plant_short_desc")
	private String plantShortDesc;
	// @Size(max = 100)
	@Column(name = "auditor1")
	private String auditor1;
	// @Size(max = 100)
	@Column(name = "auditor2")
	private String auditor2;
	// @Size(max = 100)
	@Column(name = "auditee1")
	private String auditee1;
	// @Size(max = 100)
	@Column(name = "auditee2")
	private String auditee2;
	@Column(name = "totalncs")
	private Integer totalncs;
	@Column(name = "pendingncs")
	private Integer pendingncs;
	@Column(name = "auditor_comments")
	private String auditorComments;
	@Column(name = "details_of_note_worthy")
	private String detailsOfNoteWorthy;

	public ViewIsoAuditDetails() {
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

	public String getPlantShortDesc() {
		return plantShortDesc;
	}

	public void setPlantShortDesc(String plantShortDesc) {
		this.plantShortDesc = plantShortDesc;
	}

	public String getAuditor1() {
		return auditor1;
	}

	public void setAuditor1(String auditor1) {
		this.auditor1 = auditor1;
	}

	public String getAuditor2() {
		return auditor2;
	}

	public void setAuditor2(String auditor2) {
		this.auditor2 = auditor2;
	}

	public String getAuditee1() {
		return auditee1;
	}

	public void setAuditee1(String auditee1) {
		this.auditee1 = auditee1;
	}

	public String getAuditee2() {
		return auditee2;
	}

	public void setAuditee2(String auditee2) {
		this.auditee2 = auditee2;
	}

	public Integer getTotalncs() {
		return totalncs;
	}

	public void setTotalncs(Integer totalncs) {
		this.totalncs = totalncs;
	}

	public Integer getPendingncs() {
		return pendingncs;
	}

	public void setPendingncs(Integer pendingncs) {
		this.pendingncs = pendingncs;
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

}
