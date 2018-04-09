/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.exception;


/**
 * @author earteaga
 *
 */
public class PreconditionFailedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 4935193485264203212L;

  /**
   * 
   */
  public PreconditionFailedException() {
    
    super();
  }

  /**
   * @param arg0
   */
  public PreconditionFailedException(String arg0) {

    super(arg0);
  }

  /**
   * @param arg0
   */
  public PreconditionFailedException(Throwable arg0) {

    super(arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public PreconditionFailedException(String arg0, Throwable arg1) {

    super(arg0, arg1);
  }

  /**
   * @param arg0
   * @param arg1
   * @param arg2
   * @param arg3
   */
  public PreconditionFailedException(String arg0, Throwable arg1, boolean arg2,
      boolean arg3) {

    super(arg0, arg1, arg2, arg3);
  }

}
