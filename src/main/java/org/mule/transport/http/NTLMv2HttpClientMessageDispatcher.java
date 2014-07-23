package org.mule.transport.http;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.mule.api.MuleEvent;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.transport.DispatchException;

public class NTLMv2HttpClientMessageDispatcher extends
		HttpClientMessageDispatcher {

	public NTLMv2HttpClientMessageDispatcher(OutboundEndpoint endpoint) {
		super(endpoint);
	}

	
	protected HttpMethod execute(MuleEvent event, HttpMethod httpMethod) throws Exception
    {
        // TODO set connection timeout buffer etc
        try
        {
            URI uri = endpoint.getEndpointURI().getUri();

            this.processCookies(event);
            HttpClient client = new HttpClient();
    		AuthPolicy.registerAuthScheme(AuthPolicy.NTLM,
    				com.mule.support.NTLMv2Scheme.class);
    		client.getParams().setAuthenticationPreemptive(true);

    		Credentials defaultcreds = new NTCredentials((String)event.getFlowVariable("username"), (String)event.getFlowVariable("password"),
    				(String)event.getFlowVariable("domain"), (String)event.getFlowVariable("realm"));

    		client.getState().setCredentials(AuthScope.ANY, defaultcreds);
            // TODO can we use the return code for better reporting?
            client.executeMethod(getHostConfig(uri), httpMethod);

            return httpMethod;
        }
        catch (IOException e)
        {
            // TODO employ dispatcher reconnection strategy at this point
            throw new DispatchException(event, getEndpoint(), e);
        }
        catch (Exception e)
        {
            throw new DispatchException(event, getEndpoint(), e);
        }

    }

}
