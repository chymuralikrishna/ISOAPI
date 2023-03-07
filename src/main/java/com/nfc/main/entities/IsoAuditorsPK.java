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
public class IsoAuditorsPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	// @NotNull
	@Column(name = "ecno")
	private int ecno;
	@Basic(optional = false)
	// @NotNull
	// @Size(min = 1, max = 50)
	@Column(name = "role_name")
	private String roleName;

	@Basic(optional = false)
	// @NotNull
	@Column(name = "for_unit_code")
	private int forUnitCode;

	public IsoAuditorsPK() {
	}

	public int getEcno() {
		return ecno;
	}

	public void setEcno(int ecno) {
		this.ecno = ecno;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getForUnitCode() {
		return forUnitCode;
	}

	public void setForUnitCode(int forUnitCode) {
		this.forUnitCode = forUnitCode;
	}

}
