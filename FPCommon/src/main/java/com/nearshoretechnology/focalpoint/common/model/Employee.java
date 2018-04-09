/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import static com.nearshoretechnology.focalpoint.common.security.SecurityGrant.ADMIN;
import static com.nearshoretechnology.focalpoint.common.security.SecurityGrant.FINANCE_MANAGER;
import static com.nearshoretechnology.focalpoint.common.security.SecurityGrant.PROJECT_MANAGER;
import static com.nearshoretechnology.focalpoint.common.security.SecurityGrant.RESOURCE_MANAGER;/**
 * @author earteaga
 *
 */
@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, columnDefinition = "Decimal(10,2) default 0.00")
  private Double rate;

  private String avatarUrl;

  @OneToOne
  @JoinColumn(name = "office_id")
  private Office office;
  
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "employee")
  private List<Timesheet> timesheets;

  @OneToMany(mappedBy = "employee")
  private List<Task> tasks;
  
  @ManyToMany(mappedBy = "resources")
  @Fetch(FetchMode.SELECT)
  @OrderBy("name")
  private List<Project> projects;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "manager_has_resource", 
      joinColumns = { 
          @JoinColumn(name = "manager_id", referencedColumnName = "id") }, 
      inverseJoinColumns = { 
          @JoinColumn(name = "resource_id", referencedColumnName = "id") })
  @Fetch(FetchMode.SELECT)
  private List<Employee> resources;
  
  @ManyToMany(mappedBy = "resources")
  @Fetch(FetchMode.SELECT)
  private List<Employee> managers;
  
  /**
   * 
   */
  public Employee() {

    this.rate = 0.00;
    this.avatarUrl = DEFAULT_AVATAR;
  }
  
  public String getFullname() {
    
    return user.getFullname(); 
  }
  
  public String getUsername() {
    
    return user.getUsername();
  }
  
  /**
   * @return the manager
   */
  public Employee getManager() {

    return managers.isEmpty() ? null : managers.get(0);
  }

  public boolean hasAsAssigned(Employee employee) {
    
    return this.resources.contains(employee);
  }
  
  public boolean hasGrant(String grant) {
    
    return this.user.getAuthorities().contains(new Grant(grant));
  }
  
  public boolean isAdmin() {
    
    return hasGrant(ADMIN);
  }
  
  public boolean isResourceManager() {
    return hasGrant(RESOURCE_MANAGER);
  }
  
  public boolean isProjectManager() {
    
    return hasGrant(PROJECT_MANAGER);
  }
  
  public boolean isFinanceManager() {
    
    return hasGrant(FINANCE_MANAGER);
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
    if (!(obj instanceof Employee))
      return false;
    Employee other = (Employee) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
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
   * @return the office
   */
  public Office getOffice() {

    return office;
  }

  /**
   * @param office
   *          the office to set
   */
  public void setOffice(Office office) {

    this.office = office;
  }

  /**
   * @return the rate
   */
  public Double getRate() {

    return rate;
  }

  /**
   * @param rate
   *          the rate to set
   */
  public void setRate(Double rate) {

    this.rate = rate;
  }

  /**
   * @return the avatarUrl
   */
  public String getAvatarUrl() {

    return avatarUrl;
  }

  /**
   * @param avatarUrl
   *          the avatarUrl to set
   */
  public void setAvatarUrl(String avatarUrl) {

    this.avatarUrl = avatarUrl;
  }

  /**
   * @return the user
   */
  public User getUser() {

    return user;
  }

  /**
   * @param user
   *          the user to set
   */
  public void setUser(User user) {

    this.user = user;
  }

  /**
   * @return the timesheets
   */
  public List<Timesheet> getTimesheets() {

    return timesheets;
  }

  /**
   * @param timesheets
   *          the timesheets to set
   */
  public void setTimesheets(List<Timesheet> timesheets) {

    this.timesheets = timesheets;
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
            .sorted((e1, e2) -> e1.getUser().getFullname().compareTo(e2.getUser().getFullname()))
            .collect(Collectors.toList());
  }
  
  /**
   * @return the resources IDs
   */
  public List<Long> getResourcesIds() {

    return resources == null
        ? new ArrayList<>()
        : resources
            .stream()
            .mapToLong(Employee::getId)
            .boxed()
            .collect(Collectors.toList());
  }

  /**
   * @param resources
   *          the resources to set
   */
  public void setResources(List<Employee> resources) {

    this.resources = resources;
  }
  
  /**
   * @return the managers
   */
  public List<Employee> getManagers() {

    return managers;
  }

  /**
   * @param managers
   *          the managers to set
   */
  public void setManagers(List<Employee> managers) {

    this.managers = managers;
  }

  public static final String EMPLOYEE_DOMAIN = "@nearshoretechnology.com";
  
  public static final String DEFAULT_AVATAR = "/img/avatar.png";
}
