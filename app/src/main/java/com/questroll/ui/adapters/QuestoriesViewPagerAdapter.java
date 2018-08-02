package com.questroll.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import com.questroll.model.Questory;
import com.questroll.ui.questories.QuestoryFragmentContent;
import java.util.List;

public class QuestoriesViewPagerAdapter extends FragmentStatePagerAdapter {

  private List<Questory> questories;
  private SparseArray<Fragment> questoriesFragments = new SparseArray<>();


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
    final Fragment fragment = questoriesFragments.get(position);

    if (fragment != null) {
      return fragment;
    } else {
      final QuestoryFragmentContent questoryFragmentContent = new QuestoryFragmentContent();
      questoryFragmentContent.setQuestory(questories.get(position));
      questoriesFragments.put(position, questoryFragmentContent);
      return questoryFragmentContent;
    }
  }
}
