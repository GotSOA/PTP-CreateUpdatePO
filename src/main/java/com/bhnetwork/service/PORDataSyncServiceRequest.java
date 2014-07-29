package com.bhnetwork.service;


public class PORDataSyncServiceRequest {
	
	private String porId;
	private String vendor;
	private String poNumber;

	public String getPorId() {
		return porId;
	}

	public void setPorId(String porId) {
		this.porId = porId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
}
