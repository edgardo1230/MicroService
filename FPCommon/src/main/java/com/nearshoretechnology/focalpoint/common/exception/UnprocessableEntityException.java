/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.exception;

/**
 * @author earteaga
 *
 */
public class UnprocessableEntityException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -4374870782364805790L;

  /**
   * 
   */
  public UnprocessableEntityException() {

    super();
  }

  /**
   * @param message
   */
  public UnprocessableEntityException(String message) {

    super(message);
  }

  /**
   * @param cause
   */
  public UnprocessableEntityException(Throwable cause) {

    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public UnprocessableEntityException(String message, Throwable cause) {

    super(message, cause);
  }

  /**
   * @param message
   * @param cause
   * @param enableSuppression
   * @param writableStackTrace
   */
  public UnprocessableEntityException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {

    super(message, cause, enableSuppression, writableStackTrace);
  }

}
