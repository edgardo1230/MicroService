package com.nearshoretechnology.focalpoint.common.model;

import static com.nearshoretechnology.focalpoint.common.util.DateUtils.asEndDate;
import static com.nearshoretechnology.focalpoint.common.util.DateUtils.inRange;
import static com.nearshoretechnology.focalpoint.common.util.RoundUtils.roundTwoDecimalHalfUp;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.gson.Gson;
import com.nearshoretechnology.focalpoint.common.dto.ProjectStats;
import com.nearshoretechnology.focalpoint.common.exception.PreconditionFailedException;
import com.nearshoretechnology.focalpoint.common.exception.UnprocessableEntityException;
import com.nearshoretechnology.focalpoint.common.model.enums.TimesheetStatus;
import com.nearshoretechnology.focalpoint.common.vo.UtilizationVO;
/**
 * @author earteaga
 *
 */
@Entity
public class Timesheet {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Date weekEnding;
  
  private transient Date weekBegining;

  @Column(nullable = false, columnDefinition = "Integer(10) default 1")
  private TimesheetStatus status;

  @Column(nullable = false, columnDefinition = "Decimal(10,2) default 0.00")
  private Double billableUtilization;

  @Column(nullable = false, columnDefinition = "Decimal(10,2) default 0.00")
  private Double nonBillableUtilization;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @OneToMany(mappedBy = "timesheet", fetch = FetchType.EAGER)
  @OrderBy("start DESC")
  private Set<Activity> activities;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
    name = "timesheet_has_note", 
    joinColumns = { 
        @JoinColumn(name = "timesheet_id", referencedColumnName = "id") 
    }, 
    inverseJoinColumns = { 
        @JoinColumn(name = "note_id", referencedColumnName = "id") 
    })
  @Fetch(FetchMode.SELECT)
  @OrderBy("date DESC")
  private List<Note> notes;
  
  private Integer weekWorkHours;
  
  private Integer billableWeekWorkHours;

  public Timesheet() {
    
    super();
    activities = new HashSet<Activity>();
    notes = new ArrayList<Note>();
  }

  public Timesheet(Employee employee) {

    this();
    this.weekEnding = getWeekEndingOfCurrentWeek();
    this.weekBegining = getWeekBegining(weekEnding);
    this.employee = employee;
    this.billableUtilization = 0.00;
    this.nonBillableUtilization = 0.00;
    this.status = TimesheetStatus.OPEN;
    this.weekWorkHours = WEEK_WORK_HRS;
    this.billableWeekWorkHours = BILLABLE_WEEK_WORK_HRS;
  }
  
  public Collection<ProjectStats> getStatistics() {
    Map<Long, ProjectStats> projectStats = new HashMap<Long, ProjectStats>();
    
    activities.forEach(a -> {
      Project p = a.getTask().getProject();
      
      if (!projectStats.containsKey(p.getId())) {
        projectStats.put(p.getId(), new ProjectStats(p));
      }
      
      projectStats.get(p.getId()).addHours(a.getTimeSpent());
    });
    
    return projectStats
              .values()
              .stream()
              .sorted((p1, p2) -> p2.getHours().compareTo(p1.getHours()))
              .collect(Collectors.toList());
  }
  
  public String getStatisticsAsJSON() {
    return new Gson().toJson(getStatistics());
  }
  
  
  /**
   * Verify that the provided dates belongs to the same week of the timesheet.
   * 
   * @param start
   * @param end
   * @throws UnprocessableEntityException unless belongs to the same week
   */
  public void startAndEndDateOnSameWeek(Date start, Date end) {
    
    if ( start == null || end == null ) {
      throw new PreconditionFailedException("Start or End date must be provided.");
    }
    
    if( weekBegining == null ) {
      weekBegining = getWeekBegining(weekEnding);
    }
    
    if (!(inRange(weekBegining, weekEnding, start) && inRange(weekBegining, weekEnding, end))) {
      throw new UnprocessableEntityException("The start or end date of the activity, does not belongs to the timesheet week.");
    }
    
  }
  
  public void addActivity(Activity activity) {
    this.activities.add(activity);
    updateUtilization();
  }
  
  public void removeActivity(Activity activity) {
    this.activities.remove(activity);
    updateUtilization();
  }
  
  public void removeActivities() {
    this.activities.clear();
    updateUtilization();
  }
  
  public void addNote(Note note) {
    this.notes.add(note);
  }
  
  public Double getStraightUtilization() {
    return (getBillableHours() / billableWeekWorkHours) * 100.0;
  }
  
  /**
   * Total utilization percentage
   * @return the totalUtilization
   */
  public Double getTotalUtilization() {
    return nonBillableUtilization + billableUtilization;
  }  
  
  /**
   * Total hours utilization
   * @return
   */
  public Double getTotalHours() {
    
    return roundTwoDecimalHalfUp( ( getTotalUtilization() / 100 ) * weekWorkHours );
  }
  
  /**
   * Billable hours utilization
   * @return
   */
  public Double getBillableHours() {
    
    return roundTwoDecimalHalfUp( ( billableUtilization / 100 ) * weekWorkHours );
  }
  
  /**
   * Non billable hours utilization
   * @return
   */
  public Double getNonBillableHours() {
    
    return roundTwoDecimalHalfUp( ( nonBillableUtilization / 100 ) * weekWorkHours );
  }

  public Boolean isOpen() {
    
    return TimesheetStatus.OPEN.equals(status);
  }

  public Boolean isPending() {
    
    return TimesheetStatus.PENDING.equals(status);
  }
  
  public Boolean isRejected() {
    
    return TimesheetStatus.REJECTED.equals(status);
  }

  public Boolean isApproved() {
    
    return TimesheetStatus.APPROVED.equals(status);
  }
  
  public Boolean isEditable() {

    return isOpen() || isRejected();
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
    this.weekBegining = getWeekBegining(weekEnding);
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
   * Billable utilization percentage
   * @return the billableUtilization
   */
  public Double getBillableUtilization() {

    return billableUtilization;
  }

  /**
   * @param billableUtilization
   *          the billableUtilization to set
   */
  public void setBillableUtilization(Double billableUtilization) {

    this.billableUtilization = billableUtilization;
  }

  /**
   * Non billable utilization percentage
   * @return the nonBillableUtilization
   */
  public Double getNonBillableUtilization() {

    return nonBillableUtilization;
  }

  /**
   * @param nonBillableUtilization
   *          the nonBillableUtilization to set
   */
  public void setNonBillableUtilization(Double nonBillableUtilization) {

    this.nonBillableUtilization = nonBillableUtilization;
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
   * @return the notes
   */
  public List<Note> getNotes() {

    return notes;
  }

  /**
   * @param notes
   *          the notes to set
   */
  public void setNotes(List<Note> notes) {

    this.notes = notes;
  }
  
  /**
   * @return the weekWorkHours
   */
  public Integer getWeekWorkHours() {
    return weekWorkHours;
  }

  /**
   * @param weekWorkHours the weekWorkHours to set
   */
  public void setWeekWorkHours(Integer weekWorkHours) {
    this.weekWorkHours = weekWorkHours;
  }

  public boolean isOfCurrentWeek() {
    return Timesheet.getWeekEndingOfCurrentWeek().equals(weekEnding);
  }

  /**
   * 
   */
  private void updateUtilization() {

    UtilizationVO utilization;
    double rate;
    
    utilization = calculateUtilizationWithActivities(activities);
    
    rate = roundTwoDecimalHalfUp( ( utilization.getBillable() / weekWorkHours ) * 100 );
    billableUtilization = rate;
    
    rate = roundTwoDecimalHalfUp( ( utilization.getNonBillable() / weekWorkHours ) * 100 );
    nonBillableUtilization = rate;
  }
  
  /**
   * 
   * @return weekEnding as endDate
   */
  public static Date getWeekEndingOfCurrentWeek() {
    
    LocalDate saturdayLocalDate = LocalDate.now().with(nextOrSame(SATURDAY));
    Date saturday = Date.from(saturdayLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return asEndDate(saturday);
  }
  
  /**
   * Return the week begining as Sunday from from given date.
   * 
   * @param weekEnding
   * @return weekBegining date as start date
   */
  public static Date getWeekBegining(Date weekEnding) {
    
    LocalDate endWeek = Instant.ofEpochMilli(weekEnding.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate startWeek = endWeek.with(previousOrSame(SUNDAY));
    return Date.from(startWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
  
  public static UtilizationVO calculateUtilizationWithActivities(Set<Activity> activities) {

    double billable = 0.00;
    double nonBillable = 0.00;
    
    for (Activity activity : activities) {

      if (activity.isBillable()) {

        billable += activity.getTimeSpent();
      } else {

        nonBillable += activity.getTimeSpent();
      }
    }

    return new UtilizationVO(billable, nonBillable);
  }
  
  public static final int WEEK_WORK_HRS = 44;
  
  public static final int BILLABLE_WEEK_WORK_HRS = 40;
}
