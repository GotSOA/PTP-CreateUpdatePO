package com.bhnetwork.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "porDataSyncServiceResponse", propOrder = {
    "porDataSyncServiceRequest",
    "status"
})
public class PORDataSyncServiceResponse {
	
	@XmlElement(required=true)
	private String status;	
	@XmlElement(required=true)
	private PORDataSyncServiceRequest porDataSyncServiceRequest;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public PORDataSyncServiceRequest getPorDataSyncServiceRequest() {
		return porDataSyncServiceRequest;
	}

	public void setPorDataSyncServiceRequest(
			PORDataSyncServiceRequest porDataSyncServiceRequest) {
		this.porDataSyncServiceRequest = porDataSyncServiceRequest;
	}
	
}
