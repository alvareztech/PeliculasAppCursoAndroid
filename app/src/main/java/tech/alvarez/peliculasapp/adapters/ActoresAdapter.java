package tech.alvarez.peliculasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tech.alvarez.peliculasapp.R;
import tech.alvarez.peliculasapp.services.Actor;

public class ActoresAdapter extends RecyclerView.Adapter<ActoresAdapter.ViewHolder> {

    private ArrayList<Actor> dataset;
    private Context context;

    public ActoresAdapter(ArrayList<Actor> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor p = dataset.get(position);

        holder.nombreTextView.setText(p.getName());
        holder.papelTextView.setText(p.getCharacter());

        String url = "http://image.tmdb.org/t/p/w185" + p.getProfile_path();

        Glide.with(context).load(url).into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView papelTextView;
        ImageView fotoImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            papelTextView = (TextView) itemView.findViewById(R.id.papelTextView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }

    }

}
