package com.bhnetwork.integration.msgprocessors;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.ExceptionPayload;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.LoggerMessageProcessor;
import org.mule.api.transport.PropertyScope;

/**
 * Search through Exception chain to determine if exception was caused by a connectivity error,
 * indicating whether the original message can be retried.
 * 
 * @author srata
 *
 */
public class ErrorMessageMap extends LoggerMessageProcessor {
	
/*
 * formats exception as a map which velocity template will format
 * 
 */

    @Override
    public MuleEvent process(MuleEvent muleEvent) throws MuleException {
        
        ExceptionPayload ep = muleEvent.getMessage().getExceptionPayload();
        Map errorMessage = new HashMap();
        errorMessage.put("system", "PTP");
        if (ep == null) {
            logger.error("Exception payload is null");
        	errorMessage.put("message", "Exception payload is null");    
        	errorMessage.put("integrationStep", muleEvent.getMessage().getProperty("integrationStep", PropertyScope.SESSION).toString()); 
        } else {
        	errorMessage.put("message", ep.getException().getCause().getMessage());		
        	errorMessage.put("integrationStep", muleEvent.getMessage().getProperty("integrationStep", PropertyScope.SESSION).toString());  
		}
		muleEvent.setSessionVariable("error", errorMessage);     // set error in session
        return muleEvent;
    }

}