package com.questroll;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.example.oleg.questroll.R;
import com.questroll.app.Helper;
import com.questroll.model.Questory;
import com.questroll.network.ApiClient;
import com.questroll.network.ApiService;
import com.questroll.rx.QuestoriesObserver;
import com.questroll.ui.adapters.QuestoriesViewPagerAdapter;
import com.questroll.ui.listeners.CircularViewPagerHandler;
import com.questroll.ui.questories.QuestoriesViewPager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class Questories extends AppCompatActivity {

  public QuestoriesViewPagerAdapter questoriesViewPagerAdapter;
  public QuestoriesViewPager questoriesViewPager;
  private List<Questory> questories = new ArrayList<>();
  private ApiService apiService;
  private CompositeDisposable disposable = new CompositeDisposable();

  private final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_questories);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    initQuestoriesViewPager();
  }

  private void initQuestoriesViewPager() {

    getAudioRecordPermission();// TODO this has to be changed

    Helper.setApplicationContext(getApplicationContext());
    final FragmentManager supportFragmentManager = getSupportFragmentManager();
    questoriesViewPager = findViewById(R.id.questoriesViewPager);
    questoriesViewPager.addOnPageChangeListener(new CircularViewPagerHandler(questoriesViewPager));
    apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
    questoriesViewPagerAdapter = new QuestoriesViewPagerAdapter(supportFragmentManager,
        questories);
    questoriesViewPager.setAdapter(questoriesViewPagerAdapter);
    questoriesViewPager.setOffscreenPageLimit(2);
    getQuestories();
  }

  private void getAudioRecordPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat
          .requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
              MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }

  private void getQuestories() {
    disposable.add(apiService.fetchQuestories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new QuestoriesObserver(questories, questoriesViewPagerAdapter, apiService)));
  }
}
