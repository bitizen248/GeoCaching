package ru.iate.geocaching.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.iate.geocaching.R;
import ru.iate.geocaching.rest.RetrofitFactory;

/**
 * Created by Bitizen on 08.06.17.
 */

public class AnswerActivity extends ProtoActivity {

    private TextView question;
    private EditText answer;
    private Button send;

    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        send = (Button) findViewById(R.id.send);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);

        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        question.setText(intent.getStringExtra("question"));

        send.setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Проверка ответа...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            RetrofitFactory
                    .answer(id, answer.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(s -> {
                        progressDialog.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                        if (s.getSucces()) {
                            alert
                                    .setMessage("Поздравляем! Вы успешло прошли квест!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                                        Intent intent1 = new Intent(this, MainActivity.class);
                                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent1);
                                    });
                        } else {
                            alert
                                    .setMessage("К сожалению, это не верный ответ. Попробуйте еще раз.")
                                    .setPositiveButton("Ok", null);
                        }
                        alert.create().show();
                    })
                    .subscribe();


        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
