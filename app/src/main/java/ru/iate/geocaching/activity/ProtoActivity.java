package ru.iate.geocaching.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import ru.iate.geocaching.R;

/**
 * Created by Bitizen on 08.06.17.
 */

public abstract class ProtoActivity extends AppCompatActivity {

    private CoordinatorLayout root;
    private Toolbar toolbar;
    private FrameLayout frame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_proto);

        root = (CoordinatorLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        frame = (FrameLayout) findViewById(R.id.frame);

        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, frame);
    }

    protected View addBottom(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, root, false);
        root.addView(view);
        return view;
    }
}
