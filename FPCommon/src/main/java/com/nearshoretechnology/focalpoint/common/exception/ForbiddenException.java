/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author earteaga
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -4325442167176452166L;

  /**
   * 
   */
  public ForbiddenException() {

    super();
  }

  /**
   * @param arg0
   */
  public ForbiddenException(String message) {

    super(message);
  }

  /**
   * @param arg0
   */
  public ForbiddenException(Throwable cause) {

    super(cause);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ForbiddenException(String message, Throwable cause) {

    super(message, cause);
  }
}
