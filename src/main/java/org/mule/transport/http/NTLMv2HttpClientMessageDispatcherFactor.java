package org.mule.transport.http;

import org.mule.api.MuleException;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.transport.MessageDispatcher;
import org.mule.transport.http.HttpClientMessageDispatcherFactory;

public class NTLMv2HttpClientMessageDispatcherFactor extends HttpClientMessageDispatcherFactory{
	 /** {@inheritDoc} */
    public MessageDispatcher create(OutboundEndpoint endpoint) throws MuleException
    {
        return new NTLMv2HttpClientMessageDispatcher(endpoint);
    }
}
