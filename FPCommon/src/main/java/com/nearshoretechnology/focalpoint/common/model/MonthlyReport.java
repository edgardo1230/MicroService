package com.nearshoretechnology.focalpoint.common.model;

import static com.nearshoretechnology.focalpoint.common.util.DateUtils.asEndDate;
import static com.nearshoretechnology.focalpoint.common.util.DateUtils.asStartDate;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "monthly_reports")
public class MonthlyReport {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "month_ending")
  private Date monthEnding;

  private String schedule;

  private String hurdles;

  @Column(name = "next_steps")
  private String nextSteps;

  private String issues;

  private String resolutions;

  private String summary;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id")
  private Project project;

  public MonthlyReport() {

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    c.getActualMinimum(Calendar.DAY_OF_MONTH);
    
    monthEnding = asEndDate(c.getTime());
  }

  public MonthlyReport(Project project) {

    this();
    this.project = project;
    schedule = "";
    hurdles = "";
    nextSteps = "";
    issues = "";
    resolutions = "";
    summary = "";
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
   * @return the monthEnding
   */
  public Date getMonthEnding() {

    return monthEnding;
  }

  /**
   * @param monthEnding
   *          the monthEnding to set
   */
  public void setMonthEnding(Date monthEnding) {

    this.monthEnding = monthEnding;
  }
  
  public Date getMonthBegining() {
    
    Calendar c = Calendar.getInstance();
    c.setTime(monthEnding);
    c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
    
    return asStartDate(c.getTime());
  }
  
  public Date getNextMonthEnding() {
    
    Calendar c = Calendar.getInstance();
    c.setTime(monthEnding);
    c.add(Calendar.MONTH, 1);
    
    return asEndDate(c.getTime());
  }

  /**
   * @return the schedule
   */
  public String getSchedule() {

    return schedule;
  }

  /**
   * @param schedule
   *          the schedule to set
   */
  public void setSchedule(String schedule) {

    this.schedule = schedule;
  }

  /**
   * @return the hurdles
   */
  public String getHurdles() {

    return hurdles;
  }

  /**
   * @param hurdles
   *          the hurdles to set
   */
  public void setHurdles(String hurdles) {

    this.hurdles = hurdles;
  }

  /**
   * @return the nextSteps
   */
  public String getNextSteps() {

    return nextSteps;
  }

  /**
   * @param nextSteps
   *          the nextSteps to set
   */
  public void setNextSteps(String nextSteps) {

    this.nextSteps = nextSteps;
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
   * @return the resolutions
   */
  public String getResolutions() {

    return resolutions;
  }

  /**
   * @param resolutions
   *          the resolutions to set
   */
  public void setResolutions(String resolutions) {

    this.resolutions = resolutions;
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
