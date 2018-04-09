package com.nearshoretechnology.focalpoint.common.model.enums;


public enum ProjectMethodology {

  NTS_AGILE("NearShore Custom Agile Method"),
  NST_WATERFALL("Nearshore Custom Waterfall Method"),
  CLIENT_METHOD("Client Methodology");
  
  private String label;

  /**
   * @param label
   */
  private ProjectMethodology(String label) {

    this.label = label;
  }
  
  public String getLabel() {

    return label;
  }
}
