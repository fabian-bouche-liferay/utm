package com.liferay.samples.fbo.utm.model;

import java.io.Serializable;

public class UTM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String source;
	private String medium;
	private String campaign;
	private String term;
	private String content;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getMedium() {
		return medium;
	}
	
	public void setMedium(String medium) {
		this.medium = medium;
	}
	
	public String getCampaign() {
		return campaign;
	}
	
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	
	public String getTerm() {
		return term;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

}
