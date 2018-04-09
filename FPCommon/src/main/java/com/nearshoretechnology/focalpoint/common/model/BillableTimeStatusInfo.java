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
@Table(name = "billable_time_status_view")
public class BillableTimeStatusInfo {

  @Id
  private Long id;

  @Column(name = "week_ending")
  private Date weekEnding;

  private String resource;

  private TimesheetStatus status;

  private String customer;

  private String project;

  @Column(name = "time_spent")
  private Double totalHours;

  private Long invoice;

  @Column(name = "invoice_doc")
  private String invoiceDocument;

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
   * @return the customer
   */
  public String getCustomer() {

    return customer;
  }

  /**
   * @param customer
   *          the customer to set
   */
  public void setCustomer(String customer) {

    this.customer = customer;
  }

  /**
   * @return the project
   */
  public String getProject() {

    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(String project) {

    this.project = project;
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
   * @return the invoice
   */
  public Long getInvoice() {

    return invoice;
  }

  /**
   * @param invoice
   *          the invoice to set
   */
  public void setInvoice(Long invoice) {

    this.invoice = invoice;
  }

  /**
   * @return the invoiceDocument
   */
  public String getInvoiceDocument() {

    return invoiceDocument;
  }

  /**
   * @param invoiceDocument
   *          the invoiceDocument to set
   */
  public void setInvoiceDocument(String invoiceDocument) {

    this.invoiceDocument = invoiceDocument;
  }

}
