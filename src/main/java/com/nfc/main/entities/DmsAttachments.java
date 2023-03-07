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
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NFC
 */
@Entity
@Table(name = "dms_attachments")
@NamedQueries({ @NamedQuery(name = "DmsAttachments.findAll", query = "SELECT d FROM DmsAttachments d") })
public class DmsAttachments implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Size(max = 20)
	@Column(name = "audit_id")
	private String auditId;
	@Column(name = "plant_code")
	private Integer plantCode;
	@Column(name = "finding_no")
	private Integer findingNo;
	@Id
	@Basic(optional = false)
	// @NotNull
	// @Size(min = 1, max = 50)
	@Column(name = "dms_token_id")
	private String dmsTokenId;
	// @Size(max = 100)
	@Column(name = "description")
	private String description;
	// @Size(max = 100)
	@Column(name = "dms_file_name")
	private String dmsFileName;
	@Column(name = "uploaded_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadedOn;
	@Column(name = "uploaded_by_ecno")
	private Integer uploadedByEcno;

	public DmsAttachments() {
	}

	public DmsAttachments(String dmsTokenId) {
		this.dmsTokenId = dmsTokenId;
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

	public Integer getFindingNo() {
		return findingNo;
	}

	public void setFindingNo(Integer findingNo) {
		this.findingNo = findingNo;
	}

	public String getDmsTokenId() {
		return dmsTokenId;
	}

	public void setDmsTokenId(String dmsTokenId) {
		this.dmsTokenId = dmsTokenId;
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

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public Integer getUploadedByEcno() {
		return uploadedByEcno;
	}

	public void setUploadedByEcno(Integer uploadedByEcno) {
		this.uploadedByEcno = uploadedByEcno;
	}

}
