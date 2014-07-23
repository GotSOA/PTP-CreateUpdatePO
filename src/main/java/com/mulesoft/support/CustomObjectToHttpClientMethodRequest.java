package com.mulesoft.support;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transport.http.transformers.ObjectToHttpClientMethodRequest;

public class CustomObjectToHttpClientMethodRequest extends
		ObjectToHttpClientMethodRequest {

	@Override
	public Object transformMessage(MuleMessage msg, String outputEncoding)
			throws TransformerException {

		// transform the payload to string
		try {
			msg.setPayload(msg.getPayloadAsString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.transformMessage(msg, outputEncoding);
	}

}
