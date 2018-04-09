package com.nearshoretechnology.focalpoint.common.form;


/**
 * @author earteaga
 *
 */
public class AdminOfficeDTO extends BaseDTO {

  private Long officeId;

  private String office;

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    return "AdminOfficeDTO [officeId=" + officeId + ", office=" + office
        + ", username=" + username + "]";
  }

  /**
   * @return the officeId
   */
  public Long getOfficeId() {

    return officeId;
  }

  /**
   * @param officeId
   *          the officeId to set
   */
  public void setOfficeId(Long officeId) {

    this.officeId = officeId;
  }

  /**
   * @return the office
   */
  public String getOffice() {

    return office.trim();
  }

  /**
   * @param office
   *          the office to set
   */
  public void setOffice(String office) {

    this.office = office;
  }

}
