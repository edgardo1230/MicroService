/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author osilva
 *
 */
@Entity
public class CustomerRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id")
	private Contact contact;

	private Long date_id;
	
	private String description;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date activeDate;
	  
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date deactiveDate;
	
	@NotNull
	private String role;
	

	/**
	 * @return the id
	 */
	public Long getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @return the date_id
	 */
	public Long getDate_id() {
		return date_id;
	}

	/**
	 * @param date_id the date_id to set
	 */
	public void setDate_id(Long date_id) {
		this.date_id = date_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the activeDate
	 */
	public Date getActiveDate() {
		return activeDate;
	}

	/**
	 * @param activeDate the activeDate to set
	 */
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * @return the deactiveDate
	 */
	public Date getDeactiveDate() {
		return deactiveDate;
	}

	/**
	 * @param deactiveDate the deactiveDate to set
	 */
	public void setDeactiveDate(Date deactiveDate) {
		this.deactiveDate = deactiveDate;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	
}
