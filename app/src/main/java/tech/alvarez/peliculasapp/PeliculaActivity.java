package tech.alvarez.peliculasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculasapp.models.Pelicula;
import tech.alvarez.peliculasapp.models.PeliculasCineRespuesta;
import tech.alvarez.peliculasapp.services.TheMovieDatabaseService;

public class PeliculaActivity extends AppCompatActivity {

    private ImageView posterImageView;
    private TextView tituloTextView;

    private int idPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        posterImageView = (ImageView) findViewById(R.id.posterImageView);
        tituloTextView = (TextView) findViewById(R.id.tituloTextView);

         idPelicula = getIntent().getIntExtra("idPelicula", 0);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDatabaseService service = retrofit.create(TheMovieDatabaseService.class);

        Call<Pelicula> call = service.obtenerDetallePelicula(idPelicula, "589e10387e0ca4ece633f5836fb0383f");
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {

                Pelicula p = response.body();

                String url = "http://image.tmdb.org/t/p/w185" + p.getPoster();

                tituloTextView.setText(p.getTitle());
                Glide.with(PeliculaActivity.this).load(url).into(posterImageView);

            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {

            }
        });


    }

    public void mostrarActores(View view) {
        Intent intent = new Intent(this, ActoresActivity.class);
        intent.putExtra("idPelicula",  idPelicula);
        startActivity(intent);

    }
}
