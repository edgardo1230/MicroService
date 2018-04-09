package com.nearshoretechnology.focalpoint.common.model.enums;

public enum ProjectStage {

  DISCOVERY("Discovery"), 
  PLANNING("Planning"), 
  EXECUTION("Execution"), 
  MONITORING("Monitoring"), 
  CLOSEOUT("Closeout");
  
  private String label;

  /**
   * @param label
   */
  private ProjectStage(String label) {

    this.label = label;
  }
  
  public String getLabel() {

    return label;
  }
}
