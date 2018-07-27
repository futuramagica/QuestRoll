package com.questroll.model;

import java.util.List;

public class Questory {

  private List<Questep> questeps;
  private String name;
  private String questoryId;
  private String questId;


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
