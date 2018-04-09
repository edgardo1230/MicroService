package com.nearshoretechnology.focalpoint.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mcanto on 11/5/15.
 */
public class ShortDateFormatterUtils {

  /**
   * @param date the date to format
   * @param message is a string that will be used if date is null
   * @return
   */
  public static String formatWithDefaultMessage(Date date, String message) {
    return ( date != null ) ? formatter.format(date) : message;
  }

  /**
   * If the date is null, the return value will be a empty String
   * @param date the date to format
   * @return
   */
  public static String format(Date date) {
    return formatWithDefaultMessage(date, "");
  }

  /**
   *The method expect to receive a String with a format specifier in the placeholder parameter
   * @param date date to format
   * @param placeholder a String with a format specifier, E.g. "My birthday is %s"
   * @return
   */
  public static String format(Date date, String placeholder) {
    return format(date, placeholder, "");
  }

  /**
   *
   * @param date date to format
   * @param placeholder a String with a format specifier, E.g. "My birthday is %s"
   * @param message is a string that will be used if date is null
   * @return
   */
  public static String format(Date date, String placeholder, String message) {
    if ( date != null ) {
      return String.format(placeholder, format(date));
    } else {
      return message;
    }
  }

  private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");;
}
