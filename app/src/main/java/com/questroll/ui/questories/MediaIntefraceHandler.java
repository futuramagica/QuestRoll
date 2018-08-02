package com.questroll.ui.questories;

public interface MediaIntefraceHandler {


  void onHide();

  void onShow();

  public boolean isActiveMedia();

  public void pauseMediaDisplay();

  public void startMediaDisplay();
}
