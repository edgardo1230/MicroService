/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.dto;

import com.nearshoretechnology.focalpoint.common.model.Project;

/**
 * @author ernesto.arteaga
 *
 */
public class ProjectStats {

  private String color;

  private String project;

  private Double hours;

  /**
   * 
   */
  public ProjectStats(Project project) {
    this.color = project.getCompany().getColor();
    this.project = project.getName();
    hours = 0.0;
  }

  public void addHours(Double hours) {
    this.hours += hours;
  }

  /**
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * @return the project
   */
  public String getProject() {
    return project;
  }

  /**
   * @return the hours
   */
  public Double getHours() {
    return hours;
  }

}
