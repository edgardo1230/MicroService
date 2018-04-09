/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model.enums;


/**
 * @author earteaga
 *
 */
public enum ProjectType {
  
  ONGOING("Ongoing"),
  TEAM_ENHANCEMENT("Team enhancement");
  
  private String label;

  /**
   * @param label
   */
  private ProjectType(String label) {

    this.label = label;
  }
  
  public String getLabel() {

    return label;
  }
}
