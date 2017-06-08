package ru.iate.geocaching.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.iate.geocaching.R;
import ru.iate.geocaching.adapter.SecretAdapter;
import ru.iate.geocaching.obj.Secret;
import ru.iate.geocaching.rest.RetrofitFactory;

/**
 * Created by Bitizen on 08.06.17.
 */

public class MainActivity extends ProtoActivity {

    private RecyclerView secretList;
    private SecretAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secretList = (RecyclerView) findViewById(R.id.list);
        adapter = new SecretAdapter(this, new ArrayList<>());
        secretList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        secretList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitFactory
                .getSecrets()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(l -> {
                    adapter.setSecrets(l);
                    adapter.notifyDataSetChanged();
                })
                .subscribe();
    }

}
