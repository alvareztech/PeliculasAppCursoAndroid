package tech.alvarez.peliculasapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tech.alvarez.peliculasapp.MainActivity;
import tech.alvarez.peliculasapp.R;
import tech.alvarez.peliculasapp.models.Pelicula;

/**
 * Created on 11/26/16.
 *
 * @author Daniel Alvarez
 */

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.ViewHolder> {


    private ArrayList<Pelicula> dataset;
    private Context context;
    private OnPeliculaItemClickListener onPeliculaItemClickListener;

    public PeliculasAdapter(ArrayList<Pelicula> dataset, MainActivity context) {
        this.dataset = dataset;
        this.context = context;
        this.onPeliculaItemClickListener = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pelicula p = dataset.get(position);

        holder.tituloTextView.setText(p.getTitle());
        holder.descripcionTextView.setText(p.getOverview());

        String url = "http://image.tmdb.org/t/p/w185" + p.getImagen();

        Glide.with(context).load(url).into(holder.fotoImageView);

        holder.setOnPeliculaItemClick(p, onPeliculaItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTextView;
        TextView descripcionTextView;
        ImageView fotoImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            descripcionTextView = (TextView) itemView.findViewById(R.id.descripcionTextView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }

        public void setOnPeliculaItemClick(final Pelicula p, final OnPeliculaItemClickListener onPeliculaItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPeliculaItemClickListener.onPeliculaItemClick(p);
                }
            });
        }
    }

}
