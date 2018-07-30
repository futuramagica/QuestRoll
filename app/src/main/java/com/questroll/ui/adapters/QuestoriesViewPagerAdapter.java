package com.questroll.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.questroll.model.Questory;
import com.questroll.ui.questories.QuestoryFragmentContent;
import java.util.List;

public class QuestoriesViewPagerAdapter extends FragmentStatePagerAdapter {

  private List<Questory> questories;

  public QuestoriesViewPagerAdapter(FragmentManager fm, final List<Questory> questories) {
    super(fm);
    this.questories = questories;
  }

  @Override
  public int getCount() {
    return questories.size();
  }

  @Override
  public Fragment getItem(int position) {
    final QuestoryFragmentContent questoryFragmentContent = new QuestoryFragmentContent();
    questoryFragmentContent.setQuestory(questories.get(position));
    return questoryFragmentContent;
  }
}
