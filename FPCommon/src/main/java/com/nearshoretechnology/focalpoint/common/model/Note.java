/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author earteaga
 *
 */
@Entity
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String content;
  
  @Column(nullable = false)
  private Date date;
  
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  /**
   * 
   */
  public Note() {
  }

  /**
   * @param content
   * @param user
   */
  public Note(String content, User user) {

    super();
    this.content = content;
    this.user = user;
    this.date = new Date();
    
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /* (non-Javadoc)
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
    Note other = (Note) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
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
   * @param id the id to set
   */
  public void setId(Long id) {
  
    this.id = id;
  }

  
  /**
   * @return the content
   */
  public String getContent() {
  
    return content;
  }

  
  /**
   * @param content the content to set
   */
  public void setContent(String content) {
  
    this.content = content;
  }

  
  /**
   * @return the date
   */
  public Date getDate() {
  
    return date;
  }

  
  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
  
    this.date = date;
  }

  
  /**
   * @return the user
   */
  public User getUser() {
  
    return user;
  }

  
  /**
   * @param user the user to set
   */
  public void setUser(User user) {
  
    this.user = user;
  }
  

}
