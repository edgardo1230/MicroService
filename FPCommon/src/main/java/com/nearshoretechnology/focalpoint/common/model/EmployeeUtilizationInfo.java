/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Employee Utilization (only for enabled resources at
 * Focalpoint)
 * 
 * @author earteaga
 *
 */
@Entity
@Table(name = "employee_utilization_view")
public class EmployeeUtilizationInfo {

  @Id
  private Long id;

  private String resource;

  private String manager;

  @Column(name = "week_ending")
  private Date weekEnding;
  
  private transient Timesheet timesheet;

  @Column(name = "total_hours")
  private Double totalHours;

  @Column(name = "billable")
  private Double billableHours;

  @Column(name = "non_billable")
  private Double nonBillableHours;

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
   * @return the resource
   */
  public String getResource() {

    return resource;
  }

  /**
   * @param resource
   *          the resource to set
   */
  public void setResource(String resource) {

    this.resource = resource;
  }

  /**
   * @return the manager
   */
  public String getManager() {

    return manager;
  }

  /**
   * @param manager
   *          the manager to set
   */
  public void setManager(String manager) {

    this.manager = manager;
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
   * @return the billableHours
   */
  public Double getBillableHours() {

    return billableHours;
  }

  /**
   * @param billableHours
   *          the billableHours to set
   */
  public void setBillableHours(Double billableHours) {

    this.billableHours = billableHours;
  }

  /**
   * @return the nonBillableHours
   */
  public Double getNonBillableHours() {

    return nonBillableHours;
  }

  /**
   * @param nonBillableHours
   *          the nonBillableHours to set
   */
  public void setNonBillableHours(Double nonBillableHours) {

    this.nonBillableHours = nonBillableHours;
  }

  public Double getBillablePercentage() {

    return (this.billableHours / Timesheet.WEEK_WORK_HRS) * 100.0;
  }

  public Double getNonBillablePercentage() {

    return (this.nonBillableHours / Timesheet.WEEK_WORK_HRS) * 100.0;
  }

  public Timesheet getTimesheet() {
    return timesheet;
  }

  public void setTimesheet(Timesheet timesheet) {
    this.timesheet = timesheet;
  }

}
