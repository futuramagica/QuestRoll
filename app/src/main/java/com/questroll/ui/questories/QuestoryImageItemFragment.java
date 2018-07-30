package com.questroll.ui.questories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;

public class QuestoryImageItemFragment extends BaseQuestoryFragment {

  private ImageView questepImage;

  public QuestoryImageItemFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {

    this.questep = getArguments().getParcelable("questep");
    this.questory = getArguments().getParcelable("questory");
    View view = inflater.inflate(R.layout.questoryitem_image, container, false);
    questepImage = view.findViewById(R.id.questepImage);
    if (isCachedFileExists()) {
      proccessMediaCached();
    } else {
      getDataFromServerAndPopulateView();
    }
    return view;
  }

  @Override
  public void proccessMediaCached() {
    Glide.with(this).load(Helper.getPathToCachedDataForQuestep(getQuestep(), getQuestory()))
        .into(questepImage);
  }
}