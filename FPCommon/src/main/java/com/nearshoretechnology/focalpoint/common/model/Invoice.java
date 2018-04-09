/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import static com.nearshoretechnology.focalpoint.common.model.Timesheet.calculateUtilizationWithActivities;
import static com.nearshoretechnology.focalpoint.common.model.Timesheet.getWeekEndingOfCurrentWeek;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author earteaga
 *
 */
@Entity
@Table(name = "invoices")
public class Invoice {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date date;
  
  private Date start;
  
  private Date end;
  
  private Double totalHours;
  
  private String reference;

  private String referenceId;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company_id")
  private Company company;
  
  @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Activity> activities;
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
    name = "invoice_has_project", 
    joinColumns = {
      @JoinColumn(name = "invoice_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "project_id", referencedColumnName = "id") 
    })
  @Fetch(FetchMode.SELECT)
  @OrderBy("name")
  private List<Project> projects;
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
    name = "invoice_has_resources", 
    joinColumns = {
      @JoinColumn(name = "invoice_id", referencedColumnName = "id") 
    },
    inverseJoinColumns = {
      @JoinColumn(name = "resource_id", referencedColumnName = "id") 
    })
  @Fetch(FetchMode.SELECT)
  private List<Employee> resources;
  
  /**
   * 
   */
  public Invoice() {
    activities = new HashSet<Activity>();
  }

  /**
   * @param company
   */
  public Invoice(Company company) {

    this.company = company;
    this.date = getWeekEndingOfCurrentWeek();
    this.totalHours = 0.0;
    this.activities = new HashSet<Activity>();
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Invoice))
      return false;
    Invoice other = (Invoice) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public void calculateTotalHours() {
    
    this.totalHours = calculateUtilizationWithActivities(activities).getTotal();
  }
  
  public void addAll(Set<Activity> activities) {
    
    for (Activity activity : activities) {
      activity.setInvoice(this);
      this.activities.add(activity);
    }
    
    calculateTotalHours();
  }
  
  public List<Activity> removeAllActivities() {
    
    List<Activity> toRemove = new ArrayList<>(activities);
    activities = new HashSet<>();
    
    for (Activity activity : toRemove) {
      activity.setInvoice(null);
    }
    
    totalHours = 0.0;
    
    return toRemove;
  }

  /**
   * @return the id
   */
  public Long getId() {

    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return the date
   */
  public Date getDate() {

    return date;
  }

  /**
   * @param date
   *          the date to set
   */
  public void setDate(Date date) {

    this.date = date;
  }

  /**
   * @return the start
   */
  public Date getStart() {

    return start;
  }

  /**
   * @param start
   *          the start to set
   */
  public void setStart(Date start) {

    this.start = start;
  }

  /**
   * @return the end
   */
  public Date getEnd() {

    return end;
  }

  /**
   * @param end
   *          the end to set
   */
  public void setEnd(Date end) {

    this.end = end;
  }

  /**
   * @return the totalHours
   */
  public Double getTotalHours() {

    return totalHours;
  }

  /**
   * @param totalHours
   *          the totalHours to set
   */
  public void setTotalHours(Double totalHours) {

    this.totalHours = totalHours;
  }

  /**
   * @return the reference
   */
  public String getReference() {

    return reference;
  }

  /**
   * @param reference
   *          the reference to set
   */
  public void setReference(String reference) {

    this.reference = reference;
  }

  /**
   * @return the company
   */
  public Company getCompany() {

    return company;
  }

  /**
   * @param company
   *          the company to set
   */
  public void setCompany(Company company) {

    this.company = company;
  }

  /**
   * @return the activities
   */
  public Set<Activity> getActivities() {

    return activities;
  }

  /**
   * @param activities
   *          the activities to set
   */
  public void setActivities(Set<Activity> activities) {

    this.activities = activities;
  }

  /**
   * @return the projects
   */
  public List<Project> getProjects() {

    return projects;
  }

  /**
   * @param projects
   *          the projects to set
   */
  public void setProjects(List<Project> projects) {

    this.projects = projects;
  }

  /**
   * @return the resources
   */
  public List<Employee> getResources() {

    return resources == null
         ? null
         : resources
            .stream()
            .sorted((e1, e2) -> e1.getUser().getLname().compareTo(e2.getUser().getLname()))
            .collect(Collectors.toList());
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  /**
   * @param resources
   *          the resources to set
   */
  public void setResources(List<Employee> resources) {

    this.resources = resources;
  }
  
  public static final long ALL_PROJECTS = -1L;
  
  public static final long ALL_RESOURCES = -1L;
  
  public static final String PENDING = "pending";
  
  public static final String SUBMITTED = "submitted";
  
  public static final String QB_SYNC_HREF = "/management/finance/invoicing/new?invoice=";
}
