package com.liferay.samples.fbo.utm.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.samples.fbo.utm.constants.UTMKeys;
import com.liferay.samples.fbo.utm.model.UTM;
import com.liferay.samples.fbo.utm.thread.local.UTMThreadLocal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"before-filter=URL Rewrite Filter",
				"dispatcher=REQUEST",
				"dispatcher=FORWARD",
				"servlet-context-name=",
				"servlet-filter-name=UTM Filter",
				"url-pattern=/*"
			},
		service = Filter.class
	)
public class UTMServletFilter extends BaseFilter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

		boolean set = false;
		
		String utmCampaign = ParamUtil.get(PortalUtil.getOriginalServletRequest(httpServletRequest), UTMKeys.UTM_CAMPAIGN, StringPool.BLANK);
		String utmContent = ParamUtil.get(PortalUtil.getOriginalServletRequest(httpServletRequest), UTMKeys.UTM_CONTENT, StringPool.BLANK);
		String utmMedium = ParamUtil.get(PortalUtil.getOriginalServletRequest(httpServletRequest), UTMKeys.UTM_MEDIUM, StringPool.BLANK);
		String utmSource = ParamUtil.get(PortalUtil.getOriginalServletRequest(httpServletRequest), UTMKeys.UTM_SOURCE, StringPool.BLANK);
		String utmTerm = ParamUtil.get(PortalUtil.getOriginalServletRequest(httpServletRequest), UTMKeys.UTM_TERM, StringPool.BLANK);

		UTM utm = new UTM();

		if(
				!StringPool.BLANK.equals(utmCampaign) ||
				!StringPool.BLANK.equals(utmContent) || 
				!StringPool.BLANK.equals(utmMedium) || 
				!StringPool.BLANK.equals(utmSource) || 
				!StringPool.BLANK.equals(utmTerm)) {

			utm.setCampaign(utmCampaign);
			utm.setContent(utmContent);
			utm.setMedium(utmMedium);
			utm.setSource(utmSource);
			utm.setTerm(utmTerm);

			_log.debug("#UTM CAMPAIGN: " + utm.getCampaign());
			
			PortalUtil.getOriginalServletRequest(httpServletRequest).getSession().setAttribute(UTMKeys.UTM_SESSION_ATTRIBUTE, utm);
			
			set = true;

		}

		
		if(!set) {
		
			utm = (UTM) PortalUtil.getOriginalServletRequest(httpServletRequest).getSession().getAttribute(UTMKeys.UTM_SESSION_ATTRIBUTE);
			
		}
		
		UTMThreadLocal.setUTM(utm);
		
		super.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	@Override
	protected Log getLog() {
		return _log;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(
			UTMServletFilter.class);

}
