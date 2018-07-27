package com.questroll.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuestoriesPOJO {

  @SerializedName("_embedded")
  @Expose
  private List<QuestoryPOJO> questory = null;
  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("_returned")
  @Expose
  private Integer returned;

  public List<QuestoryPOJO> getQuestories() {
    return questory;
  }

  public void setQuestory(List<QuestoryPOJO> questory) {
    this.questory = questory;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getReturned() {
    return returned;
  }

  public void setReturned(Integer returned) {
    this.returned = returned;
  }

}