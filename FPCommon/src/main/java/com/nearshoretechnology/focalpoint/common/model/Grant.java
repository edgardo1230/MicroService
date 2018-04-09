/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author earteaga
 *
 */
@Entity
@Table(name = "app_grant")
public class Grant implements GrantedAuthority {

  /**
   * 
   */
  private static final long serialVersionUID = 8589914872782183488L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(unique = true, nullable = false, columnDefinition="VARCHAR(100)")
  @Size(min = 5, max = 100)
  private String authority;

  @NotNull
  @Size(min = 5, max = 100)
  @Column(nullable = false, columnDefinition="VARCHAR(100)")
  private String description;

  private transient boolean selected;

  @ManyToMany(mappedBy = "grants")
  private List<Role> roles;

  public Grant() {

    super();
  }

  public Grant(String authority) {

    super();
    this.authority = authority;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((authority == null) ? 0 : authority.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Grant other = (Grant) obj;
    if (authority == null) {
      if (other.authority != null)
        return false;
    } else if (!authority.equals(other.authority))
      return false;
    return true;
  }

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

  @Override
  public String getAuthority() {

    return authority;
  }

  /**
   * @param authority
   *          the authority to set
   */
  public void setAuthority(String authority) {

    this.authority = authority;
  }

  /**
   * @return the description
   */
  public String getDescription() {

    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return the selected
   */
  public Boolean getSelected() {

    return selected;
  }

  /**
   * @param selected
   *          the selected to set
   */
  public void setSelected(Boolean selected) {

    this.selected = selected;
  }

  /**
   * @return the roles
   */
  public List<Role> getRoles() {

    return roles;
  }

  /**
   * @param roles
   *          the roles to set
   */
  public void setRoles(List<Role> roles) {

    this.roles = roles;
  }

}
