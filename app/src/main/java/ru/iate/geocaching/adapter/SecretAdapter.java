package ru.iate.geocaching.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ru.iate.geocaching.R;
import ru.iate.geocaching.activity.SingleSecretActivity;
import ru.iate.geocaching.obj.Secret;

/**
 * Created by Bitizen on 08.06.17.
 */

public class SecretAdapter extends RecyclerView.Adapter<SecretAdapter.SecretViewHolder> {

    private Context context;
    private List<Secret> secrets;

    public SecretAdapter(Context context, List<Secret> secrets) {
        this.context = context;
        this.secrets = secrets;
    }

    private static int[] colors = {
            Color.WHITE,
            Color.parseColor("#eeeeee")
    };

    @Override
    public SecretViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        return new SecretViewHolder(lf.inflate(R.layout.item_secret, parent, false));
    }

    @Override
    public void onBindViewHolder(SecretViewHolder holder, int position) {
        Secret secret = secrets.get(position);
        holder.wrapper.setBackgroundColor(colors[position % colors.length]);
        holder.wrapper.setOnClickListener(v -> {
            Intent intent = new Intent(context, SingleSecretActivity.class);
            intent.putExtra("secret", secret);
            context.startActivity(intent);
        });
        holder.title.setText(secret.getName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(secret.getTimestamp());
        holder.timestamp.setText(formatter.format(calendar.getTime()));
        holder.region.setText(secret.getRegion());

        if (secret.getPassed())
            holder.passed.setVisibility(View.VISIBLE);
        else
            holder.passed.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return secrets.size();
    }

    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }

    class SecretViewHolder extends RecyclerView.ViewHolder {

        ViewGroup wrapper;
        TextView title;
        TextView timestamp;
        TextView region;
        ImageView passed;

        public SecretViewHolder(View itemView) {
            super(itemView);
            wrapper = (ViewGroup) itemView.findViewById(R.id.wrapper);
            title = (TextView) itemView.findViewById(R.id.title);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            region = (TextView) itemView.findViewById(R.id.region);
            passed = (ImageView) itemView.findViewById(R.id.passed);
        }
    }
}
