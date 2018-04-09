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
@Table(name = "client_hours_view")
public class ClientHoursInfo {

  @Id
  private Long id;

  private String client;

  @Column(name = "week_ending")
  private Date weekEnding;

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
   * @return the client
   */
  public String getClient() {

    return client;
  }

  /**
   * @param client
   *          the client to set
   */
  public void setClient(String client) {

    this.client = client;
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

}
