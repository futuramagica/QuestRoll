package com.questroll.ui.questories;

import android.support.v4.app.Fragment;
import com.questroll.app.Helper;
import com.questroll.model.Questep;
import com.questroll.model.Questory;
import com.questroll.network.ApiClient;
import com.questroll.network.ApiService;
import com.questroll.rx.QuestepObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;

public abstract class BaseQuestoryFragment extends Fragment {

  Questep questep;
  Questory questory;
  private CompositeDisposable disposable;

  public abstract void proccessMediaCached();

  public BaseQuestoryFragment() {
  }

  void getDataFromServerAndPopulateView() {
    disposable = new CompositeDisposable();
    final ApiService apiService = ApiClient.getClient(getContext()).create(ApiService.class);
    disposable.add(apiService.getMediaData(getQuestepFileId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new QuestepObserver(getQuestep(), getQuestory(), this)));
  }

  boolean isCachedFileExists() {
    return new File(Helper.getPathToCachedDataForQuestep(questep, questory)).exists();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (disposable != null) {
      disposable.clear();
    }
  }


  String getQuestepFileId() {
    return this.questep.getFileId();
  }


  public Questory getQuestory() {
    return questory;
  }

  public void setQuestory(Questory questory) {
    this.questory = questory;
  }

  public Questep getQuestep() {
    return questep;
  }

  public void setQuestep(Questep questep) {
    this.questep = questep;
  }
}
