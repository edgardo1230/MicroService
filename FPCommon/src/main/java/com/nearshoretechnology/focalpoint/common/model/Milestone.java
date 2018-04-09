/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.nearshoretechnology.focalpoint.common.model.enums.MilestoneStatus;

/**
 * @author earteaga
 *
 */
@Entity
public class Milestone {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min = 2, max = 50)
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date plannedStart;

  @NotNull
  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date plannedEnd;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date actualStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date actualEnd;
  
  private MilestoneStatus status;

  private Boolean billable;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id")
  private Project project;

  /**
   * 
   */
  public Milestone() {

    status = MilestoneStatus.CURRENT;
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
   * @return the plannedEnd
   */
  public Date getPlannedEnd() {

    return plannedEnd;
  }

  /**
   * @param plannedEnd
   *          the plannedEnd to set
   */
  public void setPlannedEnd(Date plannedEnd) {

    this.plannedEnd = plannedEnd;
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
   * @return the actualEnd
   */
  public Date getActualEnd() {

    return actualEnd;
  }

  /**
   * @param actualEnd
   *          the actualEnd to set
   */
  public void setActualEnd(Date actualEnd) {

    this.actualEnd = actualEnd;
  }
  
  /**
   * @return the status
   */
  public MilestoneStatus getStatus() {
  
    return status;
  }
  
  /**
   * @param status the status to set
   */
  public void setStatus(MilestoneStatus status) {
  
    this.status = status;
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
