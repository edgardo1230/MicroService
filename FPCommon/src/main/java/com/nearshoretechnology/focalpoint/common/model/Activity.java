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

import com.nearshoretechnology.focalpoint.common.exception.PreconditionFailedException;
import com.nearshoretechnology.focalpoint.common.exception.UnprocessableEntityException;

/**
 * @author earteaga
 *
 */
@Entity
public class Activity {
  
  public static final double MINIMUM_INCREMENT_OF_TIME = 0.5;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Date start;

  @Column(nullable = false)
  private Date end;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "timesheet_id")
  private Timesheet timesheet;
  
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;
  
  /**
   * 
   */
  public Activity() {

    super();
  }
  
  /**
   * @param start
   * @param end
   */
  public Activity(Date start, Date end) {

    this();
    
    if ( start == null || end == null ) {
      throw new PreconditionFailedException("Start or End date must be provided.");
    } else if ( end.before(start) ) {
      throw new PreconditionFailedException("End date must be after start date.");
    }
    
    this.start = start;
    this.end = end;
    
    if ( this.getTimeSpent() == 0 || (this.getTimeSpent() % MINIMUM_INCREMENT_OF_TIME) != 0 ) {
      throw new UnprocessableEntityException("Minimum increment to log time is half hour.");
    }
  }
  
  public Double getTimeSpent() {
    
    long secs = 0;
    int mins = 0;
    
    secs = (end.getTime() - start.getTime()) / 1000;
    mins = (int) (secs / 60);
    
    return mins / 60.0;
    
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
    if (!(obj instanceof Activity))
      return false;
    Activity other = (Activity) obj;
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
  
  public boolean isBillable() {
    
    return task.getBillable();
    
  }

  /**
   * @return the task
   */
  public Task getTask() {

    return task;
  }

  /**
   * @param task
   *          the task to set
   */
  public void setTask(Task task) {

    this.task = task;
  }

  /**
   * @return the timesheet
   */
  public Timesheet getTimesheet() {

    return timesheet;
  }

  /**
   * @param timesheet
   *          the timesheet to set
   */
  public void setTimesheet(Timesheet timesheet) {

    this.timesheet = timesheet;
  }

  /**
   * @return the invoice
   */
  public Invoice getInvoice() {

    return invoice;
  }

  /**
   * @param invoice
   *          the invoice to set
   */
  public void setInvoice(Invoice invoice) {

    this.invoice = invoice;
  }
}
