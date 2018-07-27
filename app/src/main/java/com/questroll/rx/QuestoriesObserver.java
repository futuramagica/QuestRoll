package com.questroll.rx;

import android.support.v4.app.Fragment;
import com.questroll.app.Helper;
import com.questroll.model.Questep;
import com.questroll.model.QuestepAudio;
import com.questroll.model.QuestepImage;
import com.questroll.model.QuestepVideo;
import com.questroll.model.Questory;
import com.questroll.network.ApiService;
import com.questroll.network.model.QuestepPOJO;
import com.questroll.network.model.QuestoriesPOJO;
import com.questroll.network.model.QuestoryPOJO;
import com.questroll.ui.adapters.QuestoriesViewPagerAdapter;
import com.questroll.ui.questories.QuestoryFragmentContent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.List;

public class QuestoriesObserver extends DisposableSingleObserver<QuestoriesPOJO> {

  private List<Questory> questories;
  private QuestoriesViewPagerAdapter questoriesViewPagerAdapter;
  private ApiService apiService;
  private CompositeDisposable disposable;

  private QuestoriesObserver() {
  }

  public QuestoriesObserver(final List<Questory> questories,
      final QuestoriesViewPagerAdapter questoriesViewPagerAdapter,
      ApiService apiService) {
    this.questories = questories;
    this.questoriesViewPagerAdapter = questoriesViewPagerAdapter;
    this.apiService = apiService;
  }

  @Override
  public void onSuccess(QuestoriesPOJO questoriesPOJO) {
    // TODO all this can be putted to custom predicate to modify data
    processQuestories(questoriesPOJO);


  }

  private void processQuestories(QuestoriesPOJO questoriesPOJO) {

    List<Questory> questoriesFromServer = new ArrayList<>();
    for (QuestoryPOJO questoryPOJO : questoriesPOJO.getQuestories()) {
      final Questory questory = getQuestory(questoryPOJO);

      //QuestoryFragmentContent contentFragment = new QuestoryFragmentContent();
      //contentFragment.setQuestory(questory);
      questories.add(questory);
      questoriesViewPagerAdapter.notifyDataSetChanged();
    }
  }

  private Questory getQuestory(final QuestoryPOJO questoryPOJO) {

    Questory questory = new Questory();
    questory.setQuestoryId(questoryPOJO.getId().getId());
    questory.setQuestId(questoryPOJO.getQuestId());
    List<Questep> questeps = new ArrayList<>();
    for (QuestepPOJO questepPOJO : questoryPOJO.getQuesteps()) {
      switch (questepPOJO.getType()) {

        case "IMAGE":
          QuestepImage stepImage = new QuestepImage();
          stepImage.setFileEtag(questepPOJO.getFileEtag());
          stepImage.setDescription(questepPOJO.getDescription());
          stepImage.setFileId(questepPOJO.getFileId());
          stepImage.setFileExtension(questepPOJO.getFileExtension());
          questeps.add(stepImage);
          break;

        case "AUDIO":
          QuestepAudio questepAudio = new QuestepAudio();
          questepAudio.setFileEtag(questepPOJO.getFileEtag());
          questepAudio.setDescription(questepPOJO.getDescription());
          questepAudio.setFileId(questepPOJO.getFileId());
          questepAudio.setFileExtension(questepPOJO.getFileExtension());
          questeps.add(questepAudio);

          break;
        case "VIDEO":
          QuestepVideo stepVideo = new QuestepVideo();
          stepVideo.setFileEtag(questepPOJO.getFileEtag());
          stepVideo.setDescription(questepPOJO.getDescription());
          stepVideo.setFileId(questepPOJO.getFileId());
          stepVideo.setFileExtension(questepPOJO.getFileExtension());
          questeps.add(stepVideo);

          break;
        default:
          throw new IllegalStateException("No such questory type provided.");
      }
      questory.setQuesteps(questeps);
    }
    return questory;
  }

  @Override
  public void onError(final Throwable e) {
    Helper.logE("While query questories error happened + " + e);

  }

}

