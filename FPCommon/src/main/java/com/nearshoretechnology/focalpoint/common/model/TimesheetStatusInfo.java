/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nearshoretechnology.focalpoint.common.model.enums.TimesheetStatus;

/**
 * @author earteaga
 *
 */
@Entity
@Table(name = "timesheet_status_view")
public class TimesheetStatusInfo {

  @Id
  private Long id;

  @Column(name = "week_ending")
  private Date weekEnding;

  private String resource;

  private String manager;

  private TimesheetStatus status;

  @Column(name = "total_hours")
  private Double totalHours;

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
   * @return the status
   */
  public TimesheetStatus getStatus() {

    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(TimesheetStatus status) {

    this.status = status;
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

}
