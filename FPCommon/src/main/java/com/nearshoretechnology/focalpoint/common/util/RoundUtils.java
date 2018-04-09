/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * @author earteaga
 *
 */
public class RoundUtils {

  public static double roundTwoDecimalHalfUp(double value) {

    return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();

  }

}
