package com.questroll.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Questep implements Parcelable {

  private QuestepType questepType;
  private String description;
  private String fileEtag;
  private String fileId;
  private String fileExtension;

  public Questep() {
  }

  protected Questep(Parcel in) {
    description = in.readString();
    fileEtag = in.readString();
    fileId = in.readString();
    fileExtension = in.readString();
  }

  public static final Creator<Questep> CREATOR = new Creator<Questep>() {
    @Override
    public Questep createFromParcel(Parcel in) {
      return new Questep(in);
    }

    @Override
    public Questep[] newArray(int size) {
      return new Questep[size];
    }
  };

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(description);
    parcel.writeString(fileEtag);
    parcel.writeString(fileId);
    parcel.writeString(fileExtension);
  }
}
