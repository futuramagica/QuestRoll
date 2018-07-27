package com.questroll.rx;

import android.util.Log;
import android.widget.Toast;
import com.questroll.app.Helper;
import com.questroll.model.Questep;
import com.questroll.model.Questory;
import com.questroll.ui.questories.BaseQuestoryFragment;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import retrofit2.Response;

public class QuestepObserver extends DisposableSingleObserver<Response<ResponseBody>> {

  private Questep questep;
  private Questory questory;
  private BaseQuestoryFragment baseQuestoryFragment;

  private QuestepObserver(){}

  public QuestepObserver(Questep questep, Questory questory,
      BaseQuestoryFragment baseQuestoryFragment) {
    this.questep = questep;
    this.questory = questory;
    this.baseQuestoryFragment = baseQuestoryFragment;
  }

  @Override
  public void onSuccess(Response<ResponseBody> responseBodyResponse) {
    try {
      cacheMediaData(questep, questory, responseBodyResponse);
    } catch (IOException e) {
      Log.e("I/O",
          "Something went wrong when processed questep = " + questep.getFileId() + "error is = "
              + e);
      Toast.makeText(Helper.getApplicationContext(),
          "Something went wrong when processed questep = " + questep.getFileId(),
          Toast.LENGTH_SHORT);
    }
    baseQuestoryFragment.proccessMediaCached();
  }

  @Override
  public void onError(Throwable e) {
    Log.e("NETWORK_MEDIA", "Something happened with request for qustesp = "+questep.getDescription()+"error is "+e);//TODO better logging
  }

  private void cacheMediaData(final Questep questep, final Questory questory,
      final Response<ResponseBody> responseBodyResponse)
      throws IOException {
    final File cacheDir = Helper.getCacheDir();
    if (cacheDir == null) {
      Log.d("NET_MEDIA", "Cache directory is null");
//TODO:      throw new exception once this die is null and display toast
    }
    File questoryCacheDir = new File(cacheDir, questory.getQuestoryId());
    if (!questoryCacheDir.exists()) {
      questoryCacheDir.mkdir();
    }
    InputStream responseBodyInputStream = responseBodyResponse.body().byteStream();
    final File questepCachedMediaFile = new File(questoryCacheDir,
        questep.getFileId() + "." + questep.getFileExtension());
    FileUtils.copyInputStreamToFile(responseBodyInputStream,
        questepCachedMediaFile);

    Log.d("MEDIA", "Completed file write " + questepCachedMediaFile.exists());
  }
}
