/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model.enums;


/**
 * @author earteaga
 *
 */
public enum CustomerRole {

  SPONSOR("Sponsor"),
  OWNER("Owner"),
  POC("Point Of Contact");
  
  private String label;

  /**
   * @param label
   */
  private CustomerRole(String label) {

    this.label = label;
  }
  
  public String getLabel() {

    return label;
  }
}
