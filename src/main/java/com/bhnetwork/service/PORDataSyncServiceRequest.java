package com.bhnetwork.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "porDataSyncServiceRequest", propOrder = {
    "poNumber",
    "porId",
    "vendor"
})
public class PORDataSyncServiceRequest {
	@XmlElement(required=true)
	private String porId;
	@XmlElement(required=true)
	private String vendor;
	@XmlElement(required=true)
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
