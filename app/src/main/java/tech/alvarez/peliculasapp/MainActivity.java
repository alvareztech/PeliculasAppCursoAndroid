package tech.alvarez.peliculasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculasapp.adapters.OnPeliculaItemClickListener;
import tech.alvarez.peliculasapp.adapters.PeliculasAdapter;
import tech.alvarez.peliculasapp.models.Pelicula;
import tech.alvarez.peliculasapp.models.PeliculasCineRespuesta;
import tech.alvarez.peliculasapp.services.TheMovieDatabaseService;

public class MainActivity extends AppCompatActivity implements OnPeliculaItemClickListener {

    private RecyclerView peliculasRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        peliculasRecyclerView = (RecyclerView) findViewById(R.id.peliculasRecyclerView);
        peliculasRecyclerView.setHasFixedSize(true);
        peliculasRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDatabaseService service = retrofit.create(TheMovieDatabaseService.class);

        Call<PeliculasCineRespuesta> call = service.obtenerPeliculasEnCines("589e10387e0ca4ece633f5836fb0383f");

        call.enqueue(new Callback<PeliculasCineRespuesta>() {
            @Override
            public void onResponse(Call<PeliculasCineRespuesta> call, Response<PeliculasCineRespuesta> response) {

                PeliculasCineRespuesta respuesta = response.body();

                ArrayList<Pelicula> peliculas = respuesta.getResults();

                PeliculasAdapter adaptador = new PeliculasAdapter(peliculas, MainActivity.this);

                peliculasRecyclerView.setAdapter(adaptador);



            }

            @Override
            public void onFailure(Call<PeliculasCineRespuesta> call, Throwable t) {

            }
        });

    }

    @Override
    public void onPeliculaItemClick(Pelicula p) {
        Intent intent  = new Intent(this, PeliculaActivity.class);
        intent.putExtra("idPelicula", p.getId());
        startActivity(intent);
    }
}
