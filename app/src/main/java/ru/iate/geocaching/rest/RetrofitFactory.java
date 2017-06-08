package ru.iate.geocaching.rest;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.iate.geocaching.obj.Answer;
import ru.iate.geocaching.obj.Secret;
import ru.iate.geocaching.obj.Succes;

/**
 * Created by Bitizen on 24.05.17.
 */

public class RetrofitFactory {

    private final static String BASE_URL = "https://us-central1-geocaching-832bc.cloudfunctions.net/";

    private static Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Observable<List<Secret>> getSecrets(){
        return retrofit.create(GeoCahingApi.class).getSecrets();
    }

    public static Observable<Succes> answer(int id, String answer) {
        return retrofit.create(GeoCahingApi.class).answer(new Answer(id, answer));
    }
}
