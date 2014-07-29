package com.bhnetwork.service;

import javax.jws.WebService;

@WebService
public interface PORDataSyncService {
	
	public PORDataSyncServiceResponse porDataSync(PORDataSyncServiceRequest porDataSyncServiceRequest);
	
}
