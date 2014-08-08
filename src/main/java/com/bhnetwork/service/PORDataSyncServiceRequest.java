package com.bhnetwork.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "porDataSyncServiceRequest", propOrder = {
    "poNumber",
    "porId",
    "vendorId"
})
public class PORDataSyncServiceRequest {
	
	@XmlElement(required=true)
	private String poNumber;
	@XmlElement(required=true)
	private String porId;
	@XmlElement(required=true)
	private String vendorId;
	
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPorId() {
		return porId;
	}

	public void setPorId(String porId) {
		this.porId = porId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
		
}
