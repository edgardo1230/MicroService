/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model.enums;


/**
 * @author earteaga
 *
 */
public enum TaskType {
  
  ANALYSIS("Analysis"),
  ARCHITECTURE("Architecture"),
  DESIGN("Design"),
  DEVELOPMENT("Development"),
  SQE("SQE"),
  QUALITY_ASSURANCE("Quality Assurance"),
  REQUIREMENTS("Requirements"),
  PROJECT_MANAGEMENT("Project Management"),
  TRAINING("Training"),
  PTO("PTO"),
  HOLIDAY("Holiday");

  private String label;

  /**
   * @param label
   */
  private TaskType(String label) {

    this.label = label;
  }
  
  public String getLabel() {

    return label;
  }
}
