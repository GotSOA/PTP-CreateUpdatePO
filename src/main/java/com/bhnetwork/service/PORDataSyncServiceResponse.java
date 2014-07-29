package com.bhnetwork.service;


public class PORDataSyncServiceResponse {
	
	private String status;
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
