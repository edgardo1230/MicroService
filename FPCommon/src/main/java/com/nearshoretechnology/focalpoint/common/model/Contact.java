/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author osilva
 *
 */
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String name;

	@Size(min = 0, max = 10)
	private String type;

	@Size(min = 0, max = 40)
	private String address1;

	@Size(min = 0, max = 40)
	private String address2;

	@Size(min = 0, max = 40)
	private String address3;

	@Size(min = 0, max = 40)
	private String city;

	@NotEmpty
	@Size(min = 0, max = 40)
	private String state;
	
	@NotEmpty
	@Size(min = 0, max = 40)
	private String country;

	@Size(min = 5, max = 9)
	private String zip;

	@Size(min = 0, max = 30)
	private String fax;

	@Email
	@NotNull
	private String email1;

	@Email
	private String email2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Size(min = 1, message = "may not be empty")
	@Transient
	private String listPhones;
	
	@Size(min = 0)
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ContactPhone> phones;
	
	@OneToMany(mappedBy = "contact")
	private List<CustomerRole> customerRoles;

	private boolean active;

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
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {

		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {

		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ContactPhone> getPhones() {
		return phones;
	}

	public void setPhones(List<ContactPhone> phones) {
		this.phones = phones;
	}

	public String getListPhones() {
		return listPhones;
	}

	public void setListPhones(String listPhones) {
		this.listPhones = listPhones;
	}

	/**
	 * @return the customerRoles
	 */
	public List<CustomerRole> getCustomerRoles() {
		return customerRoles;
	}

	/**
	 * @param customerRoles the customerRoles to set
	 */
	public void setCustomerRoles(List<CustomerRole> customerRoles) {
		this.customerRoles = customerRoles;
	}

}
