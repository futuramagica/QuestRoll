package com.questroll.ui.questories;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;


public class QuestoryVideoItemFragment extends BaseQuestoryFragment {

  private VideoView questepVideo;
  //private boolean buttonHideFlag = false; //TODO this need to be properly resolved .
  private View videoView;

  public QuestoryVideoItemFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    this.questep = getArguments().getParcelable("questep");
    this.questory = getArguments().getParcelable("questory");

    videoView = inflater.inflate(R.layout.questoryitem_video, container, false);
    questepVideo = videoView.findViewById(R.id.questepVideo);

    if (isCachedFileExists()) {
      proccessMediaCached();
    } else {
      getDataFromServerAndPopulateView();
    }
    addListenerToViewView();
    addButoonImageClickListener();
    return videoView;
  }

  @SuppressLint("ClickableViewAccessibility")
  private void addListenerToViewView() {
    Helper.logD("Adding on click listener for  video ");
    triggerHideButtonThread();
  }

  @SuppressLint("ClickableViewAccessibility")
  private void triggerHideButtonThread() {
    questepVideo.setOnTouchListener((view, motionEvent) -> {
      Helper.logD("Clicked vide view ");

      final ImageButton imageBtn = videoView.findViewById(R.id.videoMediaButton);
      if (QuestoryVideoItemFragment.this.isVideoPlaying()) {
        imageBtn.setBackgroundResource(R.drawable.ic_button_pause);
      } else {
        imageBtn.setBackgroundResource(R.drawable.ic_button_play);
      }
      imageBtn.setVisibility(View.VISIBLE);
      triggerButtonHideThread(imageBtn);

      return false;
    });
  }

  private void triggerButtonHideThread(ImageButton imageBtn) {
    Handler handler = new Handler();
    handler.postDelayed(() -> {
      imageBtn.setVisibility(View.INVISIBLE);
    }, 3000);
  }


  private void addButoonImageClickListener() {
    final ImageButton imageButton = videoView.findViewById(R.id.videoMediaButton);

    imageButton.setOnClickListener(view -> {
      if (isMediaReady() && isVideoPlaying()) {
        pauseVideo();
      } else {
        playVideo();
      }
      view.setVisibility(View.INVISIBLE);
    });
  }

  private boolean isMediaReady() {
    return questepVideo != null;
  }

  private boolean isVideoPlaying() {
    return questepVideo.isPlaying();
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      onShow();
    } else {
      onHide();
    }

    handleVideoPlaying();
  }

  private void handleVideoPlaying() {
    if (isActiveMedia()) {
      playVideo();
    } else {
      pauseVideo();
    }
  }

  private void pauseVideo() {
    if (questepVideo != null) {
      questepVideo.pause();

    }
  }

  private void playVideo() {
    final Fragment parentFragment = getParentFragment();
    if (questepVideo != null && parentFragment.getUserVisibleHint()) {
      questepVideo.start();
    }
  }

  @Override
  public void proccessMediaCached() {
    questepVideo.setVideoPath(Helper.getPathToCachedDataForQuestep(getQuestep(), getQuestory()));
    if (getUserVisibleHint() && isActiveMedia()) {
      questepVideo.start();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    onHide();
    pauseVideo();
  }
  @Override
  public void pauseMediaDisplay() {
    pauseVideo();
  }

  @Override
  public void startMediaDisplay() {
    playVideo();
  }
}
