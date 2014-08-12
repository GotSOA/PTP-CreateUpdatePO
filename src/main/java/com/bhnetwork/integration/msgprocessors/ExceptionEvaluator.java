package com.bhnetwork.integration.msgprocessors;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
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
public class ExceptionEvaluator extends LoggerMessageProcessor {
	
/*
 * INVENTORY of exceptions:
 * 
		1. TimeoutException (WaitAndCheck)			-> TimeoutException (on DAX and 3PS side)
		2. Generic Mule Exceptions					-> DispatchException, MessagingException, MuleException, AnnotationException, ComponentException
													-> org.mule.api.expression.ExpressionRuntimeException
		3. DataMapper exceptions?					-> TBD
 */

    @Override
    public MuleEvent process(MuleEvent muleEvent) throws MuleException {
        boolean isRetryableException = false;
        ExceptionPayload ep = muleEvent.getMessage().getExceptionPayload();
        Map errorMessage = new HashMap();
        errorMessage.put("system", "DAX");	// will map to systemInError in 3PS error node
        if (ep == null) {
            logger.error("Exception payload is null");
        	errorMessage.put("message", "Exception payload is null");    // will map to errorDescription in 3PS error node
        	errorMessage.put("daxStep", muleEvent.getMessage().getProperty("DaxStep", PropertyScope.SESSION).toString()); // will map to errorStep in 3PS error node
            // set error in session
        	muleEvent.setSessionVariable("error", errorMessage);              
        } else {
            Throwable cause = ep.getException();
            while (cause != null && !isRetryableException) {
            	// TODO: extend below the list of retry-able exceptions
                if (	cause instanceof ConnectException || 
                		cause instanceof UnknownHostException || 
                		cause instanceof SocketTimeoutException 
                	) {
                    isRetryableException = true;
                    logger.error("Message failed due to RETRY-able exception. ClassName: " + cause.getClass().getName() +  ", Message: " + cause.getMessage());
                }
                cause = cause.getCause();
            }
        }
        muleEvent.getMessage().setInvocationProperty("isRetryableException", isRetryableException);
        if(!isRetryableException){
        	errorMessage.put("message", ep.getException().getCause().getMessage());		// will map to errorDescription in 3PS error node
        	errorMessage.put("daxStep", muleEvent.getMessage().getProperty("DaxStep", PropertyScope.SESSION).toString());  // will map to errorStep in 3PS error node
        	// set error in session
        	muleEvent.setSessionVariable("error", errorMessage);
        	// DEBUGGING
        	System.out.println("Found non-retryable exception, error: " + errorMessage.toString());
        }
        else {
        	// DEBUGGING
        	System.out.println("Found retryable exception, error: " + ep.getMessage());
        	System.out.println("RETRYING... ");
        }
        return muleEvent;
    }

}