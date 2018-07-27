package com.questroll.ui.questories;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class QuestoryViewPager extends ViewPager {


  public QuestoryViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public QuestoryViewPager(Context context) {
    super(context);
  }

  @Override
  public boolean canScrollVertically(int direction) {
    return false;
  }

  @Override
  public boolean canScrollHorizontally(int direction) {
    return false;
  }

}
