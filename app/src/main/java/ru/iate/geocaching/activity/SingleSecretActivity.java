package ru.iate.geocaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.iate.geocaching.R;
import ru.iate.geocaching.obj.Answer;
import ru.iate.geocaching.obj.Secret;

/**
 * Created by Bitizen on 08.06.17.
 */

public class SingleSecretActivity extends ProtoActivity {

    private ImageView image;
    private TextView title;
    private TextView description;
    private FloatingActionButton answer;

    private Secret secret;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("secret")) {
            secret = intent.getParcelableExtra("secret");
        } else {
            finish();
        }
        setContentView(R.layout.activity_single_secret);

        image = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        answer = (FloatingActionButton) addBottom(R.layout.fab_answer);

        getSupportActionBar().setTitle(secret.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide
                .with(this)
                .load(secret.getImage())
                .centerCrop()
                .into(image);

        title.setText(secret.getName());
        description.setText(secret.getDescription());

        if (!secret.getPassed()) {
            answer.setOnClickListener(v -> {
                Intent answer = new Intent(this, AnswerActivity.class);
                answer.putExtra("id", secret.getId());
                answer.putExtra("name", secret.getName());
                answer.putExtra("question", secret.getQuestion());
                startActivity(answer);
            });
        } else {
            answer.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
