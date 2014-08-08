package com.bhnetwork.service;

import javax.jws.WebService;

@WebService(endpointInterface = "com.bhnetwork.service.PORDataSyncService",
			serviceName = "PORDataSyncService")
public class PORDataSyncServiceImpl implements PORDataSyncService {

	public PORDataSyncServiceResponse porDataSync(
			PORDataSyncServiceRequest porDataSyncServiceRequest) {
		PORDataSyncServiceResponse response = new PORDataSyncServiceResponse();
		response.setStatus(PORDataSyncServiceStatus.ACK.value());
		response.setPorDataSyncServiceRequest(porDataSyncServiceRequest);
		return response;
	}

}
