package com.nearshoretechnology.focalpoint.common.model;

import javax.persistence.*;

/**
 * Created by romel on 10/7/15.
 */
@Entity
public class File {

  @EmbeddedId
  private FileId id;

  @Lob
  @Column
  private byte[] content;

  public FileId getId() {
    return id;
  }

  public void setId(FileId id) {
    this.id = id;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

}
