package com.nearshoretechnology.focalpoint.common.dto;

import java.util.Date;

public class ProjectSnapshot {

	private String name;

	private String description;

	private String sponsor;

	private String manager;

	private String methodology;

	private String stage;

	private Date closureDate;

	private Double completion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMethodology() {
		return methodology;
	}

	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	public Double getCompletion() {
		return completion;
	}

	public void setCompletion(Double completion) {
		this.completion = completion;
	}

}
