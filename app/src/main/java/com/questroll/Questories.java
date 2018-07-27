package com.questroll;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;
import com.questroll.model.Questory;
import com.questroll.network.ApiClient;
import com.questroll.network.ApiService;
import com.questroll.rx.QuestoriesObserver;
import com.questroll.ui.listeners.CircularViewPagerHandler;
import com.questroll.ui.questories.QuestoriesViewPager;
import com.questroll.ui.adapters.QuestoriesViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Questories extends AppCompatActivity {

  public QuestoriesViewPagerAdapter questoriesViewPagerAdapter;
  public QuestoriesViewPager questoriesViewPager;
  private List<Questory> questories = new ArrayList<>();


  private ApiService apiService;
  private CompositeDisposable disposable = new CompositeDisposable();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_questories);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    initQuestoriesViewPager();

  }

  private void initQuestoriesViewPager() {
    Helper.setApplicationContext(getApplicationContext());
    questoriesViewPager = findViewById(R.id.questoriesViewPager);
    questoriesViewPager.addOnPageChangeListener(new CircularViewPagerHandler(questoriesViewPager));

    apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

    questoriesViewPagerAdapter = new QuestoriesViewPagerAdapter(getSupportFragmentManager(),
        questories);

    questoriesViewPager.setAdapter(questoriesViewPagerAdapter);

    getQuestories();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.clear(); //TODO: need to check if that is correct
  }

  private void getQuestories() {
    disposable.add(apiService.fetchQuestories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new QuestoriesObserver(questories, questoriesViewPagerAdapter, apiService)));

  }


}
