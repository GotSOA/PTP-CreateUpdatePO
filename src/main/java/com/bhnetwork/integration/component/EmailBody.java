package com.bhnetwork.integration.component;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service("EmailBody")
public class EmailBody implements Callable {

	@Autowired
	private VelocityEngine velocityEngine;
	
	/*public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}*/

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		Map notificationMessage = (Map) eventContext.getMessage().getProperty("error", PropertyScope.SESSION);
		eventContext.getMessage().setPayload(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/bhnetwork/integration/email/error-notification-content.vm", "UTF-8", notificationMessage));
		return eventContext.getMessage().getPayload();
	}

}
