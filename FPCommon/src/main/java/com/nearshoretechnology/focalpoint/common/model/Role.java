package com.nearshoretechnology.focalpoint.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Role implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(unique = true)
  @Size(min = 5, max = 30)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "role_has_grant", 
      joinColumns = { 
          @JoinColumn(name = "role_id", referencedColumnName = "id") 
      },
      inverseJoinColumns = { 
          @JoinColumn(name = "grant_id", referencedColumnName = "id") 
      })
  @Fetch(FetchMode.SELECT)
  private List<Grant> grants;

  @OneToMany(mappedBy = "role")
  private List<User> users;

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public List<Grant> getGrants() {

    return grants;
  }

  public void setGrants(List<Grant> grants) {

    this.grants = grants;
  }

  /**
   * @return the users
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * @param users
   *          the users to set
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  private static final long serialVersionUID = -7933173133051968022L;
}
