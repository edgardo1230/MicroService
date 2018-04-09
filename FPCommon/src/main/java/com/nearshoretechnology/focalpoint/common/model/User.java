/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nearshoretechnology.focalpoint.common.util.EncryptorUtil;

/**
 * @author earteaga
 *
 */
@Entity
public class User implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 7854391295707311278L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false, updatable = true, columnDefinition="VARCHAR(100)")
  private String username;

  @JsonIgnore
  @Column(nullable = false)
  private String password;
  
  @Column(nullable = false, columnDefinition="VARCHAR(50) default ''")
  private String fname;
  
  @Column(nullable = false, columnDefinition="VARCHAR(50) default ''")
  private String lname;
  
  private transient String fullname;
  
  private boolean newAccount;

  private boolean enabled;

  @OneToOne
  @JoinColumn(name = "role_id")
  private Role role;
  
  @OneToOne(mappedBy = "user")
  private Employee employee;
    
  /**
   * @return the id
   */
  public Long getId() {
  
    return id;
  }
  
  /**
   * @param id the id to set
   */
  public void setId(Long id) {
  
    this.id = id;
  }

  public void setUsername(String username) {

    this.username = username.trim();
  }
    
  /**
   * @return the fname
   */
  public String getFname() {
  
    return fname;
  }

  
  /**
   * @param fname the fname to set
   */
  public void setFname(String fname) {
  
    this.fname = fname.trim();
  }
  
  /**
   * @return the lname
   */
  public String getLname() {
  
    return lname;
  }
  
  /**
   * @param lname the lname to set
   */
  public void setLname(String lname) {
  
    this.lname = lname.trim();
  }
  
  /**
   * @return the fullname
   */
  public String getFullname() {
  
    if ( fullname == null ) {
      fullname = lname + ", " + fname;
    }
    
    return fullname;
    
  }
  
  /**
   * @return the newAccount
   */
  public boolean isNewAccount() {
  
    return newAccount;
  }

  /**
   * @param newAccount the newAccount to set
   */
  public void setNewAccount(boolean newAccount) {
  
    this.newAccount = newAccount;
  }

  public String getPassword() {

    return password;
  }

  public void setPassword(String password) {
    
    this.password = EncryptorUtil.getSharedInstance().encrypt(password);
  }
  
  /**
   * @return the enabled
   */
  @Override
  public boolean isEnabled() {
  
    return enabled;
  }

  /**
   * @param enable the enable to set
   */
  public void setEnabled(boolean enabled) {
  
    this.enabled = enabled;
  }

  @Override
  public String getUsername() {

    return username;
  }

  public Role getRole() {

    return role;
  }

  public void setRole(Role role) {

    this.role = role;
  }
  
  public String getRole_name() {
    
    return role.getName();
  }

  @Override
  public Collection<Grant> getAuthorities() {

    return role.getGrants();
  }

  @Override
  public boolean isAccountNonExpired() {

    return true;
  }

  @Override
  public boolean isAccountNonLocked() {

    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {

    return true;
  }
  
  /**
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /**
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public static final String DEFAULT_PASSWORD = "123456";
}
