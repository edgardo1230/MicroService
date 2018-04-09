package com.nearshoretechnology.focalpoint.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by romel on 10/7/15.
 */
@Embeddable
public class FileId implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8582701811095164866L;

  @Column(nullable = false, columnDefinition="VARCHAR(500)")
  private String fileName;

  @Column(nullable = false, columnDefinition="VARCHAR(100)")
  private String entityName;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FileId fileId = (FileId) o;

    if (!fileName.equals(fileId.fileName)) return false;
    return entityName.equals(fileId.entityName);

  }

  @Override
  public int hashCode() {
    int result = fileName.hashCode();
    result = 31 * result + entityName.hashCode();
    return result;
  }

}
