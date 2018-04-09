/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ernesto.arteaga
 *
 */
@Entity
@Table(name = "external_reports")
public class ExternalReport {

  @Id
  private Long id;

  private String title;

  private String url;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
