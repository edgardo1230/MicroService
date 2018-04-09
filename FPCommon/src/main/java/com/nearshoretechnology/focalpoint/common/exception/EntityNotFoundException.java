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
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -8751016299347758740L;

  /**
   * 
   */
  public EntityNotFoundException() {

    super();
  }

  /**
   * @param message
   * @param cause
   */
  public EntityNotFoundException(String message, Throwable cause) {

    super(message, cause);
  }

  /**
   * @param message
   */
  public EntityNotFoundException(String message) {

    super(message);
  }

  /**
   * @param cause
   */
  public EntityNotFoundException(Throwable cause) {

    super(cause);
  }
}
