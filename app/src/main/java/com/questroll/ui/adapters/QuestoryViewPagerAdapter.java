package com.questroll.ui.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.questroll.model.Questep;
import com.questroll.model.Questory;
import com.questroll.ui.questories.QuestoryAudioItemFragment;
import com.questroll.ui.questories.QuestoryImageItemFragment;
import com.questroll.ui.questories.QuestoryVideoItemFragment;

public class QuestoryViewPagerAdapter extends FragmentStatePagerAdapter {

  private Questory questory;

  public QuestoryViewPagerAdapter(FragmentManager fm) {
    super(fm);

  }

  @Override
  public Fragment getItem(int position) {
    return processQuesteps(questory.getQuesteps().get(position), questory);
  }


  private Fragment processQuesteps(Questep questep, Questory questory) {
    switch (questep.getQuestepType()) {
      case VIDEO:
        return proccessVideoItem(questep, questory);
      case AUDIO:
        return proccessAudioItem(questep, questory);
      case IMAGE:
        return proccessImageItem(questep, questory);
      default:
        Log.e("QuestoryAdapter", "Wrong type of questory item.");
        throw new IllegalStateException("No such questory type");
    }
  }

  private Fragment proccessImageItem(Questep questep, Questory questory) {
    QuestoryImageItemFragment imageItemFragment = new QuestoryImageItemFragment();

    Bundle b = new Bundle();
    b.putParcelable("questory", questory);
    b.putParcelable("questep", questep);
    imageItemFragment.setArguments(b);
    imageItemFragment.setQuestep(questep);
    imageItemFragment.setQuestory(questory);

    return imageItemFragment;
  }

  private Fragment proccessAudioItem(Questep questep, Questory questory) {
    QuestoryAudioItemFragment audioItemFragment = new QuestoryAudioItemFragment();

    Bundle b = new Bundle();
    b.putParcelable("questory", questory);
    b.putParcelable("questep", questep);
    audioItemFragment.setArguments(b);
    audioItemFragment.setQuestep(questep);
    audioItemFragment.setQuestory(questory);

    return audioItemFragment;
  }

  private Fragment proccessVideoItem(Questep questep, Questory questory) {
    QuestoryVideoItemFragment videoItemFragment = new QuestoryVideoItemFragment();

    Bundle b = new Bundle();
    b.putParcelable("questory", questory);
    b.putParcelable("questep", questep);
    videoItemFragment.setArguments(b);
    videoItemFragment.setQuestep(questep);
    videoItemFragment.setQuestory(questory);

    return videoItemFragment;
  }

  public void setQuestory(Questory questory) {
    this.questory = questory;
  }

  @Override
  public int getCount() {
    return (questory != null) ? questory.getQuesteps().size() : 0;
  }
}
