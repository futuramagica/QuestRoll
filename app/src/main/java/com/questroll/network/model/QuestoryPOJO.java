package com.questroll.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuestoryPOJO {


  @SerializedName("_id")
  @Expose
  private Id id;
  @SerializedName("questId")
  @Expose
  private String questId;
  @SerializedName("questeps")
  @Expose
  private List<QuestepPOJO> questeps = null;
  @SerializedName("_etag")
  @Expose
  private Etag etag;


  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }

  public String getQuestId() {
    return questId;
  }

  public void setQuestId(String questId) {
    this.questId = questId;
  }

  public List<QuestepPOJO> getQuesteps() {
    return questeps;
  }

  public void setQuesteps(List<QuestepPOJO> questeps) {
    this.questeps = questeps;
  }

  public Etag getEtag() {
    return etag;
  }

  public void setEtag(Etag etag) {
    this.etag = etag;
  }

}