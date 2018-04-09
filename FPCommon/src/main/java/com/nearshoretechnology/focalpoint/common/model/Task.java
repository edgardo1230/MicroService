/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.nearshoretechnology.focalpoint.common.model.enums.TaskStatus;
import com.nearshoretechnology.focalpoint.common.model.enums.TaskType;

/**
 * @author earteaga
 *
 */
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String description;

  private TaskStatus status;

  private TaskType type;
  
  private Boolean billable;

  private Boolean closeThisWeek;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @OneToMany(mappedBy = "task")
  @OrderBy("start DESC")
  private Set<Activity> activities;

  /**
   * 
   */
  public Task() {

    status = TaskStatus.OPEN;
  }

  public Company getCompany() {

    return project.getCompany();
  }

  public boolean canLogTime() {

    return !(status.equals(TaskStatus.CANCELLED) || status.equals(TaskStatus.CLOSED));
  }
  
  public boolean isEditable() {
    return activities
            .stream()
            .map(Activity::getTimesheet)
            .filter(t -> t.isApproved())
            .count() == 0;
  }

  public boolean isOpen() {

    return status.equals(TaskStatus.OPEN);
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
   * @return the description
   */
  public String getDescription() {

    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return the status
   */
  public TaskStatus getStatus() {

    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(TaskStatus status) {

    this.status = status;
  }

  /**
   * @return the type
   */
  public TaskType getType() {

    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(TaskType type) {

    this.type = type;
  }

  /**
   * @return the billable
   */
  public Boolean getBillable() {

    return billable;
  }

  /**
   * @param billable
   *          the billable to set
   */
  public void setBillable(Boolean billable) {

    this.billable = billable;
  }

  /**
   * @return the employee
   */
  public Employee getEmployee() {

    return employee;
  }

  /**
   * @param employee
   *          the employee to set
   */
  public void setEmployee(Employee employee) {

    this.employee = employee;
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
   * @return the closeThisWeek
   */
  public Boolean getCloseThisWeek() {

    return closeThisWeek;
  }

  /**
   * @param closeThisWeek
   *          the closeThisWeek to set
   */
  public void setCloseThisWeek(Boolean closeThisWeek) {

    this.closeThisWeek = closeThisWeek;
  }
}
