package com.questroll.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Etag {

  @SerializedName("$oid")
  @Expose
  private String oid;

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }

}