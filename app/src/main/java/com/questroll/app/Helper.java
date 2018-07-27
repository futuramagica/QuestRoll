package com.questroll.app;


import android.content.Context;
import android.util.Log;
import com.questroll.model.Questep;
import com.questroll.model.Questory;
import java.io.File;


public class Helper {

  private static Context applicationContext;

  public static void logD(final String msg) {

    Log.d("DEBUG", msg);
  }

  public static void logI(final String msg) {

    Log.i("INFO", msg);
  }

  public static void logE(final String msg) {
    Log.e("ERROR", msg);
  }

  public static void logW(final String msg) {
    Log.w("WARN", msg);
  }

  public static File getCacheDir() {
    if (applicationContext == null) {
      throw new IllegalStateException("Application context null");
    }
    return applicationContext.getCacheDir();
  }

  public static void setApplicationContext(Context applicationContext) {
    Helper.applicationContext = applicationContext;
  }

  public static Context getApplicationContext() {
    if (applicationContext == null) {
      throw new IllegalStateException("Application context null");
    }
    return applicationContext;

  }

  public static String getPathToCachedDataForQuestep(final Questep questep,
      final Questory questory) {
    return getCacheDir() + "/" + questory.getQuestoryId() + "/" + questep.getFileId() + "."
        + questep.getFileExtension();
  }
}
