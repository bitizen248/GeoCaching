package ru.iate.geocaching.rest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.iate.geocaching.obj.Answer;
import ru.iate.geocaching.obj.Secret;
import ru.iate.geocaching.obj.Succes;

/**
 * Created by Bitizen on 08.06.17.
 */

public interface GeoCahingApi {

    @GET("getSecrets")
    Observable<List<Secret>> getSecrets();

    @Headers("Content-Type: application/json")
    @POST("answer")
    Observable<Succes> answer(@Body Answer answer);

}
