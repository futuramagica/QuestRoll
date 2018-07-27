package com.questroll.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.questroll.app.Helper;
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
    Questep questep = questory.getQuesteps().get(position);



    return processQuesteps(questep, questory);
  }



  private Fragment processQuesteps(Questep questep, Questory questory) {
    switch (questep.getQuestepType()) {
      case VIDEO:
        return proccessVideoItem(questep,questory);
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
    imageItemFragment.setQuestep(questep);
    imageItemFragment.setQuestory(questory);
    return imageItemFragment;
  }

  private Fragment proccessAudioItem(Questep questep, Questory questory) {
    QuestoryAudioItemFragment audioItemFragment = new QuestoryAudioItemFragment();

    audioItemFragment.setQuestep(questep);
    audioItemFragment.setQuestory(questory);

    return audioItemFragment;
  }

  private Fragment proccessVideoItem(Questep questep, Questory questory) {
    QuestoryVideoItemFragment videoItemFragment = new QuestoryVideoItemFragment();

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
