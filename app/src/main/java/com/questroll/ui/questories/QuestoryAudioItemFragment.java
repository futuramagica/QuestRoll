package com.questroll.ui.questories;

import ak.sh.ay.musicwave.MusicWave;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class QuestoryAudioItemFragment extends BaseQuestoryFragment implements
    MediaIntefraceHandler {

  private MusicWave musicWave;
  private MediaPlayer mMediaPlayer;
  private View audioView;
  private Visualizer mVisualizer;
  private int currentPositionOfTrack = 0;

  public QuestoryAudioItemFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    this.questep = getArguments().getParcelable("questep");
    this.questory = getArguments().getParcelable("questory");

    audioView = inflater.inflate(R.layout.questoryitem_audio, container, false);
    if (isCachedFileExists()) {
      proccessMediaCached();
    } else {
      getDataFromServerAndPopulateView();
    }
    return audioView;
  }

  private void prepareMediaPlayerAndVisualizer() throws IOException {

    musicWave = audioView.findViewById(R.id.musicWave);
    final File mediaFile = new File(Helper.getPathToCachedDataForQuestep(questep, questory));
    mMediaPlayer = new MediaPlayer();
    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    mMediaPlayer.setVolume(20, 20);
    FileInputStream fileInputStream = new FileInputStream(mediaFile);
    mMediaPlayer.setDataSource(fileInputStream.getFD());
    mMediaPlayer.prepareAsync();
    mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mediaPlayer) {
        mMediaPlayer.seekTo(0);
        prepareVisualizer();
        mMediaPlayer.setOnCompletionListener(player -> mVisualizer.setEnabled(true));

        addButoonImageClickListener();
        addMusicVaveListener();
      }
    });


  }

  private void addMusicVaveListener() {
    musicWave.setOnClickListener(view -> {
      final ImageButton imageBtn = audioView.findViewById(R.id.audioMediaButton);
      if (isAudioPlaying()) {
        imageBtn.setBackgroundResource(R.drawable.ic_button_pause);
      } else {
        imageBtn.setBackgroundResource(R.drawable.ic_button_play);
      }
      imageBtn.setVisibility(View.VISIBLE);

      Handler handler = new Handler();
      handler.postDelayed(() -> imageBtn.setVisibility(View.INVISIBLE), 3000);
    });
  }

  private void addButoonImageClickListener() {
    final ImageButton imageButton = audioView.findViewById(R.id.audioMediaButton);

    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (isMediaReady() && isAudioPlaying()) {
          pauseAudio();
        } else {
          playAudio();
        }
        view.setVisibility(View.INVISIBLE);
      }
    });
  }

  private void prepareVisualizer() {

    final int audioSessionId = mMediaPlayer.getAudioSessionId();

    mVisualizer = new Visualizer(audioSessionId);
    mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
    mVisualizer.setDataCaptureListener(
        new Visualizer.OnDataCaptureListener() {
          public void onWaveFormDataCapture(Visualizer visualizer,
              byte[] bytes, int samplingRate) {
            musicWave.updateVisualizer(bytes);
          }

          public void onFftDataCapture(Visualizer visualizer,
              byte[] bytes, int samplingRate) {
          }
        }, Visualizer.getMaxCaptureRate() / 2, true, false);
    mVisualizer.setEnabled(true);
  }


  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      onShow();
      playAudio();
    } else {
      onHide();
      pauseAudio();
    }
  }

  private void pauseAudio() {
    if (isMediaReady() && isAudioPlaying()) {
      currentPositionOfTrack = mMediaPlayer.getCurrentPosition();
      mMediaPlayer.pause();
      mVisualizer.setEnabled(false);
    }

  }

  private boolean isAudioPlaying() {
    return mMediaPlayer.isPlaying();
  }

  private boolean isMediaReady() {
    return mMediaPlayer != null && mVisualizer != null;
  }

  private void playAudio() {
    if (isMediaReady()) {
      final Fragment parentFragment = getParentFragment();
      if (parentFragment.getUserVisibleHint()) {
        mMediaPlayer.seekTo(currentPositionOfTrack);
        mMediaPlayer.start();
        mVisualizer.setEnabled(true);
      }
    }
  }

  @Override
  public void proccessMediaCached() {
    try {
      prepareMediaPlayerAndVisualizer();
    } catch (IOException e) {
      Helper.logD("Error happened while process audio data " + e.getMessage());
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    onHide();
    pauseAudio();
  }

  @Override
  public void pauseMediaDisplay() {
    pauseAudio();
  }

  @Override
  public void startMediaDisplay() {
    playAudio();
  }
}