package com.bhnetwork.integration.msgprocessors;

import org.mule.api.lifecycle.Callable;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import java.util.concurrent.TimeoutException;

public class SleepBeforeRetry implements Callable {
	
	@SuppressWarnings("deprecation")
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		MuleMessage muleMsg = eventContext.getMessage();

		// wait time between retries
		System.out.println("In SleepBeforeRetry, waitTimeBetweenRetries: " + muleMsg.getSessionProperty("waitTimeBetweenRetries"));
        Long waitTimeBetweenRetries = Long.parseLong((String) muleMsg.getSessionProperty("waitTimeBetweenRetries"));
        
        // maximum number of retries
        System.out.println("In SleepBeforeRetry, MaximumNumberOfRetries: " + muleMsg.getSessionProperty("MaximumNumberOfRetries"));
        Long MaximumNumberOfRetries = Long.parseLong((String) muleMsg.getSessionProperty("MaximumNumberOfRetries"));
        
        // current number of retries
        System.out.println("In SleepBeforeRetry, numberOfRetries: " + muleMsg.getSessionProperty("numberOfRetries"));
        Long numberOfRetries = Long.parseLong((String) muleMsg.getSessionProperty("numberOfRetries").toString());

        numberOfRetries++;

        // safe-guard to avoid recursing for ever
        if (numberOfRetries>=MaximumNumberOfRetries) {
        	System.out.println("SleepBeforeRetry:  reached maximum number of retries = " + MaximumNumberOfRetries + "... Aborting.");
        	throw new TimeoutException("Too many retry attempts (" + numberOfRetries + ")" );
        }
        else {
        	
            try {
            	// force thread to sleep
                Thread.sleep(waitTimeBetweenRetries);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        	// adjust numberOfRetries session prop
	        muleMsg.setSessionProperty("numberOfRetries", numberOfRetries.toString());

		}
        // log for debugging purposes
        System.out.println("SleepBeforeRetry: numberOfRetries = " + numberOfRetries + " on " + MaximumNumberOfRetries + "(max)");
        
        return muleMsg;
    }

}