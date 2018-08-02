package com.questroll.ui.questories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.oleg.questroll.R;
import com.questroll.model.Questory;
import com.questroll.ui.adapters.QuestoryViewPagerAdapter;
import java.util.List;

public class QuestoryFragmentContent extends Fragment {

  private QuestoryViewPager questoryViewPager;
  private QuestoryViewPagerAdapter questoryViewPagerAdapter;

  private Questory questory;

  private int HASHCODE_OF_ACTIVE_FRAGMENT;

  public QuestoryFragmentContent() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.questory_content, container, false);
    questoryViewPager = view.findViewById(R.id.questoryViewPager);

    final FragmentManager childFragmentManager = getChildFragmentManager();
    questoryViewPagerAdapter = new QuestoryViewPagerAdapter(childFragmentManager);
    questoryViewPagerAdapter.setQuestory(this.questory);
    questoryViewPager.setAdapter(questoryViewPagerAdapter);
    //questoryViewPager.setCurrentItem(0);

    return view;
  }

  public void setQuestory(Questory questory) {
    this.questory = questory;
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (questoryViewPagerAdapter != null) {

      final List<Fragment> fragments = questoryViewPagerAdapter.getFragmentManager().getFragments();
      for (Fragment fragment : fragments) {

        final MediaIntefraceHandler mediaIntefraceHandlerFragment = (MediaIntefraceHandler) fragment;
        if (isVisibleToUser) {
          if (mediaIntefraceHandlerFragment.isActiveMedia()) {
            mediaIntefraceHandlerFragment.startMediaDisplay();
          }
        } else {
          mediaIntefraceHandlerFragment.pauseMediaDisplay();
        }
      }
    }
  }


  public Questory getQuestory() {
    return questory;
  }
}
