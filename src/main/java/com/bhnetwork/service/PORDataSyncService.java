package com.bhnetwork.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,
	use=SOAPBinding.Use.LITERAL,
	parameterStyle=SOAPBinding.ParameterStyle.BARE)
@WebService
public interface PORDataSyncService {
	
	public PORDataSyncServiceResponse porDataSync(PORDataSyncServiceRequest porDataSyncServiceRequest);
	
}
