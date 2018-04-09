/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Represents a view table for retrieve employee information
 * 
 * @author earteaga
 *
 */
@Entity
@Table(name = "employee_information_view")
public class EmployeeInfo {

  @Id
  private Long id;

  private String fullname;

  private String avatarUrl;

  private String username;

  private String manager;

  private String office;

  private String permission;

  private Boolean enabled;

  private Boolean canDisable;

  private Boolean newAccount;

  /**
   * @return the id
   */
  public Long getId() {

    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return the fullname
   */
  public String getFullname() {

    return fullname;
  }

  /**
   * @param fullname
   *          the fullname to set
   */
  public void setFullname(String fullname) {

    this.fullname = fullname;
  }

  /**
   * @return the avatarUrl
   */
  public String getAvatarUrl() {

    return avatarUrl;
  }

  /**
   * @param avatarUrl
   *          the avatarUrl to set
   */
  public void setAvatarUrl(String avatarUrl) {

    this.avatarUrl = avatarUrl;
  }

  /**
   * @return the username
   */
  public String getUsername() {

    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return the manager
   */
  public String getManager() {

    return manager;
  }

  /**
   * @param manager
   *          the manager to set
   */
  public void setManager(String manager) {

    this.manager = manager;
  }

  /**
   * @return the office
   */
  public String getOffice() {

    return office;
  }

  /**
   * @param office
   *          the office to set
   */
  public void setOffice(String office) {

    this.office = office;
  }

  /**
   * @return the permission
   */
  public String getPermission() {

    return permission;
  }

  /**
   * @param permission
   *          the permission to set
   */
  public void setPermission(String permission) {

    this.permission = permission;
  }

  /**
   * @return the enabled
   */
  public Boolean getEnabled() {

    return enabled;
  }

  /**
   * @param enabled
   *          the enabled to set
   */
  public void setEnabled(Boolean enabled) {

    this.enabled = enabled;
  }

  /**
   * @return the canDisable
   */
  public Boolean getCanDisable() {

    return canDisable;
  }

  /**
   * @param canDisable
   *          the canDisable to set
   */
  public void setCanDisable(Boolean canDisable) {

    this.canDisable = canDisable;
  }

  /**
   * @return the newAccount
   */
  public Boolean getNewAccount() {

    return newAccount;
  }

  /**
   * @param newAccount
   *          the newAccount to set
   */
  public void setNewAccount(Boolean newAccount) {

    this.newAccount = newAccount;
  }

}
