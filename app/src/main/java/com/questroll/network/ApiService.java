package com.questroll.network;

import com.questroll.network.model.QuestoriesPOJO;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


  // Fetch all notes
  @GET("questroll/questories")
  Single<QuestoriesPOJO> fetchQuestories();

  @GET("questroll/questories.files/{fileId}/binary")
  Single<Response<ResponseBody>> getMediaData(@Path("fileId") String fileId);

}
