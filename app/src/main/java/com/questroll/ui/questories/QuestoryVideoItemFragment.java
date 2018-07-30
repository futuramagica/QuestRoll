package com.questroll.ui.questories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;


public class QuestoryVideoItemFragment extends BaseQuestoryFragment {

  private VideoView questepVideo;
  private MediaController mediaController;
  private boolean isVideoLoaded = false;

  public QuestoryVideoItemFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    this.questep = getArguments().getParcelable("questep");
    this.questory = getArguments().getParcelable("questory");

    View view = inflater.inflate(R.layout.questoryitem_video, container, false);
    questepVideo = view.findViewById(R.id.questepVideo);
    mediaController = new MediaController(getContext());
    mediaController.setAnchorView(questepVideo);
    mediaController.setAnchorView(view);
    questepVideo.setMediaController(mediaController);

    if (isCachedFileExists()) {
      proccessMediaCached();
    } else {
      getDataFromServerAndPopulateView();
    }

    return view;
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVideoLoaded) {
      handleVideoPlaying(isVisibleToUser);
    }
  }

  private void handleVideoPlaying(boolean isVisibleToUser) {
    if (isVisibleToUser) {
      playVideo();
    } else {
      pauseVideo();
    }
  }

  private void pauseVideo() {
    if (questepVideo != null) {
      questepVideo.pause();
      mediaController.hide();
    }
  }

  private void playVideo() {
    if (questepVideo != null) {
      questepVideo.start();
    }
  }

  @Override
  public void proccessMediaCached() {
    questepVideo.setVideoPath(Helper.getPathToCachedDataForQuestep(getQuestep(), getQuestory()));
    isVideoLoaded = true;
  }
}
