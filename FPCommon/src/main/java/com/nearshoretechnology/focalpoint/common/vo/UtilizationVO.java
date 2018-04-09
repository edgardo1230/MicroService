/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.vo;

import static com.nearshoretechnology.focalpoint.common.util.RoundUtils.roundTwoDecimalHalfUp;

/**
 * Utilization(hours)
 * 
 * @author earteaga
 *
 */
public class UtilizationVO {

  private double billable;

  private double nonBillable;

  private double billableRate;

  private double nonBillableRate;

  private double total;

  /**
   * 
   * @param billable hours
   * @param nonBillable hours
   */
  public UtilizationVO(double billable, double nonBillable) {

    this.billable = billable;
    this.nonBillable = nonBillable;
    this.total = billable + nonBillable;

    if (total > 0) {

      billableRate = roundTwoDecimalHalfUp((billable / total) * 100);
      nonBillableRate = roundTwoDecimalHalfUp((nonBillable / total) * 100);

    } else {

      billableRate = 0.00;
      nonBillableRate = 0.00;

    }

  }

  /**
   * @return the billable
   */
  public double getBillable() {

    return billable;
  }

  /**
   * @return the nonBillable
   */
  public double getNonBillable() {

    return nonBillable;
  }

  /**
   * @return the billableRate
   */
  public double getBillableRate() {

    return billableRate;
  }

  /**
   * @return the nonBillableRate
   */
  public double getNonBillableRate() {

    return nonBillableRate;
  }

  /**
   * @return the total
   */
  public double getTotal() {

    return total;
  }

}
