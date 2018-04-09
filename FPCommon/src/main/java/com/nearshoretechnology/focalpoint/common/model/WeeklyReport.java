package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nearshoretechnology.focalpoint.common.model.enums.Semaphore;

@Entity
@Table(name = "weekly_reports")
public class WeeklyReport implements Cloneable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(columnDefinition = "VARCHAR(30)")
  private String sow;

  @Column(nullable = false)
  private Date weekEnding;

  @Column(nullable = false)
  private Semaphore scheduleHealth;

  @Column(nullable = false)
  private Semaphore budgetHealth;

  @Column(nullable = false)
  private Semaphore scopeHealth;

  @Column(nullable = false)
  private Semaphore resourcesHealth;

  @Column(nullable = false)
  private Semaphore overallHealth;

  @Column(columnDefinition = "VARCHAR(500)")
  private String issues;

  @Column(columnDefinition = "VARCHAR(500)")
  private String resolution;

  @Column(nullable = false, columnDefinition = "VARCHAR(3000)")
  private String summary;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id")
  private Project project;

  public WeeklyReport() {
    
  }
  
  public WeeklyReport(Project project) {

    this();
    this.project = project;
    issues = "";
    resolution = "";
    summary = "";
    scheduleHealth = Semaphore.GREEN;
    budgetHealth = Semaphore.GREEN;
    scopeHealth = Semaphore.GREEN;
    resourcesHealth = Semaphore.GREEN;
    overallHealth = Semaphore.GREEN;
    weekEnding = Timesheet.getWeekEndingOfCurrentWeek();
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  @Override
  public WeeklyReport clone() {

    WeeklyReport clone = null;
    
    try {
      
      clone = (WeeklyReport) super.clone();
      clone.setId(null);
      clone.setWeekEnding(Timesheet.getWeekEndingOfCurrentWeek());
      clone.setIssues("");
      clone.setResolution("");
      clone.setSummary("");
    } catch (CloneNotSupportedException e) {
      
      clone = new WeeklyReport(project);
    }
    
    return clone;
  }

  /**
   * @return
   * @see com.nearshoretechnology.focalpoint.entity.Project#getCompany()
   */
  public Company getCompany() {

    return project.getCompany();
  }

  /**
   * @return
   * @see com.nearshoretechnology.focalpoint.entity.Project#getMilestones()
   */
  public List<Milestone> getMilestones() {

    return project.getMilestones();
  }

  /**
   * @return
   * @see com.nearshoretechnology.focalpoint.entity.Project#getCustomerRoles()
   */
  public List<CustomerRole> getCustomerRoles() {

    return project.getCustomerRoles();
  }

  /**
   * @return
   * @see com.nearshoretechnology.focalpoint.entity.Project#getResources()
   */
  public Set<Employee> getResources() {

    return project.getResources();
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
   * @return the sow
   */
  public String getSow() {

    return sow;
  }

  /**
   * @param sow
   *          the sow to set
   */
  public void setSow(String sow) {

    this.sow = sow;
  }

  /**
   * @return the weekEnding
   */
  public Date getWeekEnding() {

    return weekEnding;
  }

  /**
   * @param weekEnding
   *          the weekEnding to set
   */
  public void setWeekEnding(Date weekEnding) {

    this.weekEnding = weekEnding;
  }

  /**
   * @return the scheduleHealth
   */
  public Semaphore getScheduleHealth() {

    return scheduleHealth;
  }

  /**
   * @param scheduleHealth
   *          the scheduleHealth to set
   */
  public void setScheduleHealth(Semaphore scheduleHealth) {

    this.scheduleHealth = scheduleHealth;
  }

  /**
   * @return the budgetHealth
   */
  public Semaphore getBudgetHealth() {

    return budgetHealth;
  }

  /**
   * @param budgetHealth
   *          the budgetHealth to set
   */
  public void setBudgetHealth(Semaphore budgetHealth) {

    this.budgetHealth = budgetHealth;
  }

  /**
   * @return the scopeHealth
   */
  public Semaphore getScopeHealth() {

    return scopeHealth;
  }

  /**
   * @param scopeHealth
   *          the scopeHealth to set
   */
  public void setScopeHealth(Semaphore scopeHealth) {

    this.scopeHealth = scopeHealth;
  }

  /**
   * @return the resourcesHealth
   */
  public Semaphore getResourcesHealth() {

    return resourcesHealth;
  }

  /**
   * @param resourcesHealth
   *          the resourcesHealth to set
   */
  public void setResourcesHealth(Semaphore resourcesHealth) {

    this.resourcesHealth = resourcesHealth;
  }

  /**
   * @return the overallHealth
   */
  public Semaphore getOverallHealth() {

    return overallHealth;
  }

  /**
   * @param overallHealth
   *          the overallHealth to set
   */
  public void setOverallHealth(Semaphore overallHealth) {

    this.overallHealth = overallHealth;
  }

  /**
   * @return the issues
   */
  public String getIssues() {

    return issues;
  }

  /**
   * @param issues
   *          the issues to set
   */
  public void setIssues(String issues) {

    this.issues = issues;
  }

  /**
   * @return the resolution
   */
  public String getResolution() {

    return resolution;
  }

  /**
   * @param resolution
   *          the resolution to set
   */
  public void setResolution(String resolution) {

    this.resolution = resolution;
  }

  /**
   * @return the summary
   */
  public String getSummary() {

    return summary;
  }

  /**
   * @param summary
   *          the summary to set
   */
  public void setSummary(String summary) {

    this.summary = summary;
  }

  /**
   * @return the project
   */
  public Project getProject() {

    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {

    this.project = project;
  }

}
