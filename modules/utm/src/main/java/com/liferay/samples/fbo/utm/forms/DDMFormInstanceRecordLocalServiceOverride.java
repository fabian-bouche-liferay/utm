package com.liferay.samples.fbo.utm.forms;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceWrapper;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.samples.fbo.utm.constants.UTMKeys;
import com.liferay.samples.fbo.utm.model.UTM;
import com.liferay.samples.fbo.utm.thread.local.UTMThreadLocal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
    },
    service = ServiceWrapper.class
)
public class DDMFormInstanceRecordLocalServiceOverride extends DDMFormInstanceRecordLocalServiceWrapper {
	
    public DDMFormInstanceRecordLocalServiceOverride() {
        super(null);
    }

    @Override
    public DDMFormInstanceRecord addFormInstanceRecord(long userId, long groupId, long ddmFormInstanceId,
    		DDMFormValues ddmFormValues, ServiceContext serviceContext) throws PortalException {

    	UTM utm = UTMThreadLocal.getUTM();
    	
    	if(Validator.isNotNull(utm)) {
    		Map<String, Serializable> workflowContext = (Map<String, Serializable>) serviceContext.getAttribute("workflowContext");
    		if (workflowContext == null) {
    			workflowContext = new HashMap<String, Serializable>();
    		}

        	workflowContext.put(UTMKeys.UTM_CAMPAIGN, utm.getCampaign());
        	workflowContext.put(UTMKeys.UTM_CONTENT, utm.getContent());
        	workflowContext.put(UTMKeys.UTM_MEDIUM, utm.getMedium());
        	workflowContext.put(UTMKeys.UTM_SOURCE, utm.getSource());
        	workflowContext.put(UTMKeys.UTM_TERM, utm.getTerm());
        	
        	serviceContext.setAttribute("workflowContext", (Serializable) workflowContext);
    	}

    	return super.addFormInstanceRecord(userId, groupId, ddmFormInstanceId, ddmFormValues, serviceContext);
    }
	
}
