package com.questroll.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Questory implements Parcelable {

  private List<Questep> questeps;
  private String name;
  private String questoryId;
  private String questId;


  public Questory() {
  }

  public Questory(Parcel in) {
    questeps = in.createTypedArrayList(Questep.CREATOR);
    name = in.readString();
    questoryId = in.readString();
    questId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(questeps);
    dest.writeString(name);
    dest.writeString(questoryId);
    dest.writeString(questId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Questory> CREATOR = new Creator<Questory>() {
    @Override
    public Questory createFromParcel(Parcel in) {
      return new Questory(in);
    }

    @Override
    public Questory[] newArray(int size) {
      return new Questory[size];
    }
  };

  public String getQuestId() {
    return questId;
  }

  public void setQuestId(String questId) {
    this.questId = questId;
  }

  public String getQuestoryId() {
    return questoryId;
  }

  public void setQuestoryId(String questoryId) {
    this.questoryId = questoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Questep> getQuesteps() {
    return questeps;
  }

  public void setQuesteps(List<Questep> questeps) {
    this.questeps = questeps;
  }
}
