/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.nearshoretechnology.focalpoint.common.model.enums.ProjectMethodology;
import com.nearshoretechnology.focalpoint.common.model.enums.ProjectStage;
import com.nearshoretechnology.focalpoint.common.model.enums.ProjectType;

/**
 * @author earteaga
 *
 */
@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  private String scope;

  private BigDecimal budget;
  
  private boolean billable;
  
  private ProjectMethodology methodology;
  
  private ProjectStage stage;
  
  private ProjectType type;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date plannedStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date plannedDelivery;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date forecastedStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date forecastedDelivery;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date actualStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date actualClose;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company_id")
  private Company company;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "manager_id")
  private Employee manager;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @OrderBy("date DESC")
  @JoinTable(
      name = "project_has_note", 
      joinColumns = { 
          @JoinColumn(name = "project_id", referencedColumnName = "id") },
      inverseJoinColumns = { 
          @JoinColumn(name = "note_id", referencedColumnName = "id") })
  private List<Note> notes;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinTable(
      name = "project_has_resource", 
      joinColumns = { 
          @JoinColumn(name = "project_id", referencedColumnName = "id") }, 
      inverseJoinColumns = { 
          @JoinColumn(name = "resource_id", referencedColumnName = "id") })
  @Fetch(FetchMode.SELECT)
  private Set<Employee> resources;

  @OneToMany(mappedBy = "project")
  private List<Task> tasks;

  @OneToMany(mappedBy = "project")
  private List<Milestone> milestones;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CustomerRole> customerRoles;
  
  @OneToMany(mappedBy = "project")
  private List<WeeklyReport> weeklyReports;
  
  @OneToMany(mappedBy = "project")
  private List<MonthlyReport> monthlyReports;

  @Transient
  private String listContacts;
  
  private String purchaseOrder;

  public Project() {
    this.notes = new ArrayList<Note>();
    this.customerRoles = new ArrayList<CustomerRole>();
    this.resources = new HashSet<Employee>();
  }
  
  public boolean hasAsResource(Employee resource) {

    return resources.contains(resource);
  }

  public boolean isClose() {

    return actualClose != null;
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
    if (!(obj instanceof Project))
      return false;
    Project other = (Project) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public void addNote(Note note) {
    this.notes.add(note);
  }
  
  public void addCustomerRole(CustomerRole customerRole) {
    
    customerRole.setProject(this);
    this.customerRoles.add(customerRole);
  }
  
  public void addResource(Employee resource) {
    
    this.resources.add(resource);
  }

  /**
   * @return the id
   */
  public Long getId() {

    return id;
  }

  public Long getRef() {

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
   * @return the name
   */
  public String getName() {

    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the scope
   */
  public String getScope() {

    return scope;
  }

  /**
   * @param scope
   *          the scope to set
   */
  public void setScope(String scope) {

    this.scope = scope;
  }

  /**
   * @return the budget
   */
  public BigDecimal getBudget() {

    return budget;
  }

  /**
   * @param budget
   *          the budget to set
   */
  public void setBudget(BigDecimal budget) {

    this.budget = budget;
  }
    
  /**
   * @return the billable
   */
  public boolean isBillable() {
  
    return billable;
  }
  
  /**
   * @param billable the billable to set
   */
  public void setBillable(boolean billable) {
  
    this.billable = billable;
  }
  
  /**
   * @return the methodology
   */
  public ProjectMethodology getMethodology() {
  
    return methodology;
  }
  
  /**
   * @param methodology the methodology to set
   */
  public void setMethodology(ProjectMethodology methodology) {
  
    this.methodology = methodology;
  }
  
  /**
   * @return the stage
   */
  public ProjectStage getStage() {
  
    return stage;
  }

  /**
   * @param stage the stage to set
   */
  public void setStage(ProjectStage stage) {
  
    this.stage = stage;
  }
  
  /**
   * @return the type
   */
  public ProjectType getType() {
  
    return type;
  }
  
  /**
   * @param type the type to set
   */
  public void setType(ProjectType type) {
  
    this.type = type;
  }

  /**
   * @return the plannedStart
   */
  public Date getPlannedStart() {

    return plannedStart;
  }

  /**
   * @param plannedStart
   *          the plannedStart to set
   */
  public void setPlannedStart(Date plannedStart) {

    this.plannedStart = plannedStart;
  }

  /**
   * @return the forecastedStart
   */
  public Date getForecastedStart() {

    return forecastedStart;
  }

  /**
   * @param forecastedStart
   *          the forecastedStart to set
   */
  public void setForecastedStart(Date forecastedStart) {

    this.forecastedStart = forecastedStart;
  }

  /**
   * @return the plannedDelivery
   */
  public Date getPlannedDelivery() {

    return plannedDelivery;
  }

  /**
   * @param plannedDelivery
   *          the plannedDelivery to set
   */
  public void setPlannedDelivery(Date plannedDelivery) {

    this.plannedDelivery = plannedDelivery;
  }

  /**
   * @return the forecastedDelivery
   */
  public Date getForecastedDelivery() {

    return forecastedDelivery;
  }

  /**
   * @param forecastedDelivery
   *          the forecastedDelivery to set
   */
  public void setForecastedDelivery(Date forecastedDelivery) {

    this.forecastedDelivery = forecastedDelivery;
  }

  /**
   * @return the actualStart
   */
  public Date getActualStart() {

    return actualStart;
  }

  /**
   * @param actualStart
   *          the actualStart to set
   */
  public void setActualStart(Date actualStart) {

    this.actualStart = actualStart;
  }

  /**
   * @return the actualClose
   */
  public Date getActualClose() {

    return actualClose;
  }

  /**
   * @param actualClose
   *          the actualClose to set
   */
  public void setActualClose(Date actualClose) {

    this.actualClose = actualClose;
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
   * @return the manager
   */
  public Employee getManager() {

    return manager;
  }

  /**
   * @param manager
   *          the manager to set
   */
  public void setManager(Employee manager) {

    this.manager = manager;
  }

  public List<Note> getNotes() {

    return notes;
  }

  public void setNotes(List<Note> notes) {

    this.notes = notes;
  }

  /**
   * @return the customerRoles
   */
  public List<CustomerRole> getCustomerRoles() {

    return customerRoles;
  }

  /**
   * @param customerRoles
   *          the customerRoles to set
   */
  public void setCustomerRoles(List<CustomerRole> customerRoles) {

    this.customerRoles = customerRoles;
  }

  /**
   * @return the listContacts
   */
  public String getListContacts() {

    return listContacts;
  }

  /**
   * @param listContacts
   *          the listContacts to set
   */
  public void setListContacts(String listContacts) {

    this.listContacts = listContacts;
  }

  /**
   * @return the resources
   */
  public Set<Employee> getResources() {

    return resources;
  }

  /**
   * @param resources
   *          the resources to set
   */
  public void setResources(Set<Employee> resources) {

    this.resources = resources;
  }

  /**
   * @return the tasks
   */
  public List<Task> getTasks() {

    return tasks;
  }

  /**
   * @param tasks
   *          the tasks to set
   */
  public void setTasks(List<Task> tasks) {

    this.tasks = tasks;
  }

  /**
   * @return the milestones
   */
  public List<Milestone> getMilestones() {

    return milestones;
  }

  /**
   * @param milestones
   *          the milestones to set
   */
  public void setMilestones(List<Milestone> milestones) {

    this.milestones = milestones;
  }
  
  /**
   * @return the weeklyReports
   */
  public List<WeeklyReport> getWeeklyReports() {
  
    return weeklyReports;
  }

  /**
   * @param weeklyReports the weeklyReports to set
   */
  public void setWeeklyReports(List<WeeklyReport> weeklyReports) {
  
    this.weeklyReports = weeklyReports;
  }

  /**
   * @return the monthlyReports
   */
  public List<MonthlyReport> getMonthlyReports() {
  
    return monthlyReports;
  }

  
  /**
   * @param monthlyReports the monthlyReports to set
   */
  public void setMonthlyReports(List<MonthlyReport> monthlyReports) {
  
    this.monthlyReports = monthlyReports;
  }

  public String getPurchaseOrder() {
	  return purchaseOrder;
  }

  public void setPurchaseOrder(String purchaseOrder) {
	  this.purchaseOrder = purchaseOrder;
  }

}
