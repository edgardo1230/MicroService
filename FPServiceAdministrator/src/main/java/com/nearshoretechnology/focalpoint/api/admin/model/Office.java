/**
 * 
 */
package com.nearshoretechnology.focalpoint.api.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author earteaga
 *
 */
@Entity
@Table(name = "offices")
public class Office {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  
  /**
   * 
   */
  public Office() { }

  /**
   * @param name
   */
  public Office(String name) {

    super();
    this.name = name;
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

  /**
   * @return the name
   */
  public String getName() {

    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {

    this.name = name;
  }
}
