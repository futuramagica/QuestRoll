package com.questroll.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestepPOJO {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("description")
  @Expose
  private String description;

  @SerializedName("fileEtag")
  @Expose
  private String fileEtag;

  @SerializedName("fileId")
  @Expose
  private String fileId;

  @SerializedName("fileExtension")
  @Expose
  private String fileExtension;

  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFileEtag() {
    return fileEtag;
  }

  public void setFileEtag(String fileEtag) {
    this.fileEtag = fileEtag;
  }

}