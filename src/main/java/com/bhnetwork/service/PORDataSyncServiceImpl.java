package com.bhnetwork.service;

public class PORDataSyncServiceImpl implements PORDataSyncService {

	public PORDataSyncServiceResponse porDataSync(
			PORDataSyncServiceRequest porDataSyncServiceRequest) {
		PORDataSyncServiceResponse response = new PORDataSyncServiceResponse();
		response.setStatus(PORDataSyncServiceStatus.ACK.value());
		response.setPorDataSyncServiceRequest(porDataSyncServiceRequest);
		return response;
	}

}
