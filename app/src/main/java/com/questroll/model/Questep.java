package com.questroll.model;

public class Questep {

  private QuestepType questepType;
  private String description;
  private String fileEtag;
  private String fileId;
  private String fileExtension;


  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public QuestepType getQuestepType() {
    return questepType;
  }

  void setQuestepType(QuestepType questepType) {
    this.questepType = questepType;
  }

  public String getDescription() {
    return description;
  }

  public String getFileEtag() {
    return fileEtag;
  }

  public void setFileEtag(String fileEtag) {
    this.fileEtag = fileEtag;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }
}
