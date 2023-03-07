package com.nfc.main.controllers;

public class Constants {
	
	public static final String ENV = "LIVE";
	public static final String TESTING = "TESTING";
	public static final String LIVE = "LIVE";
	public static final String TESTMAILID = "msairam@nfc.gov.in";
	
	public static final String ISOINCHARGEMAILID="iso_sys@nfc.gov.in";

	public static final String PLANT = "PLANT";
	public static final String ISOAUDITOR = "ISO AUDITOR";
	public static final String ISOINCHARGE = "ISO INCHARGE";
	
	public static final String NC = "NC";
	public static final String OI = "OI";
	public static final String O = "O";
	
	
	//audit finding types that need to be closed and send alerts
	public static final String auditFindingTypes = "('NC','O')";
	
	//stores officer is not manager level post
	// but he is the incharge of stores division.
	//so he has to approve/reject iso audit nc's
	public static final String DESIGNATIONS_TO_BE_TREATED_AS_MANAGER = "('STOF')";
	
	//status of audit nc's
	
	public static final String OPEN = "OPEN";
	//public static final String PENDING = "PENDING";
	public static final String SUBMITTED = "SUBMITTED";
	public static final String RECOMMENDED = "RECOMMENDED";
	public static final String CLOSED = "CLOSED";
	public static final String RETURNED = "RETURNED";
	
}
