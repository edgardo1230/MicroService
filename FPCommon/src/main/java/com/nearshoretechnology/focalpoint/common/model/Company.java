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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.nearshoretechnology.focalpoint.common.util.ColorUtils;

@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(max = 30)
  @Column(nullable = false)
  private String name;
  
  private String purchaseOrder;

  @NotNull
  @Size(max = 30)
  private String address1;

  @Size(max = 30)
  private String address2;
  
  @NotNull
  @NotEmpty
  private String state;

  @Size(max = 30)
  private String city;
  
  @NotNull
  @Size(min = 5, max = 9)
  private String zip;

  @Size(min = 0, max = 2083)
  private String url;
  
  @Column(nullable = false, columnDefinition="VARCHAR(7) default '#3A87AD'")
  private String color;
  
  private transient String textColor;
  
  @Column(name = "quick_books_customer_id")
  private String extReference;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("name ASC")
  private List<Project> projects;
  
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Contact> contacts;
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @OrderBy("date DESC")
  @JoinTable(
    name = "company_has_note", 
    joinColumns = {
      @JoinColumn(name = "company_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "note_id", referencedColumnName = "id") 
    })
  @Fetch(FetchMode.SELECT)
  private List<Note> notes;

  protected Company() {

  }

  public Company(String name, String address1, String state, String zip) {

    this.name = name;
    this.address1 = address1;
    this.state = state;
    this.zip = zip;
  }

  @Override
  public String toString() {

    return "Company [id=" + id + ", name=" + name + "]";
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Company))
      return false;
    Company other = (Company) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the purchaseOrder
   */
  public String getPurchaseOrder() {
  
    return purchaseOrder;
  }

  
  /**
   * @param purchaseOrder the purchaseOrder to set
   */
  public void setPurchaseOrder(String purchaseOrder) {
  
    this.purchaseOrder = purchaseOrder;
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

  public String getState() {

    return state;
  }

  public void setState(String state) {

    this.state = state;
  }

  public String getCity() {
		return city;
  }

  public void setCity(String city) {
		this.city = city;
  }

  public String getZip() {

    return zip;
  }

  public void setZip(String zip) {

    this.zip = zip;
  }

  public String getUrl() {

    return url;
  }

  public void setUrl(String url) {

    this.url = url;
  }
  
  /**
   * @return the color
   */
  public String getColor() {
  
    return color;
  }

  
  /**
   * @param color the color to set
   */
  public void setColor(String color) {
  
    this.color = color;
  }

  public String getTextColor() {
    
    if (textColor == null) {
    
      textColor = ColorUtils.getTextColor( color );
      
    }
    
    return textColor;
    
  }
  
  public String getAvatarText() {
    
    String nameHelper = this.name.toUpperCase().replaceAll("[^\\w\\s]", "").replaceAll("\\s+", " ");
    String[] name = nameHelper.split(" ");
    String avatar = "";
    
    if (name.length == 1) {
      avatar = name[0].substring(0, 2);
    } else {
      avatar = name[0].substring(0, 1) + name[1].substring(0, 1);
    }
    
    return avatar;
    
  }
  
  /**
   * @return the extReference
   */
  public String getExtReference() {
  
    return extReference;
  }

  /**
   * @param extReference the extReference to set
   */
  public void setExtReference(String extReference) {
  
    this.extReference = extReference;
  }

  /**
   * @return the projects
   */
  public List<Project> getProjects() {
  
    return projects;
  }
  
  /**
   * @param projects the projects to set
   */
  public void setProjects(List<Project> projects) {
  
    this.projects = projects;
  }
  
  /**
   * @return the notes
   */
  public List<Note> getNotes() {
  
    return notes;
  }
  
  /**
   * @param notes the notes to set
   */
  public void setNotes(List<Note> notes) {
  
    this.notes = notes;
  }

  public List<Contact> getContacts() {
	  return contacts;
  }

  public void setContacts(List<Contact> contacts) {
	  this.contacts = contacts;
  }
}
