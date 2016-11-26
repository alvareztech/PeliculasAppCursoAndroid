package tech.alvarez.peliculasapp;

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
import tech.alvarez.peliculasapp.adapters.ActoresAdapter;
import tech.alvarez.peliculasapp.models.Pelicula;
import tech.alvarez.peliculasapp.services.Actor;
import tech.alvarez.peliculasapp.services.ActoresRespuesta;
import tech.alvarez.peliculasapp.services.TheMovieDatabaseService;

public class ActoresActivity extends AppCompatActivity {

    private RecyclerView actoresRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actores);

        actoresRecyclerView = (RecyclerView) findViewById(R.id.actoresRecyclerView);
        actoresRecyclerView.setHasFixedSize(true);
        actoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        int idPelicula = getIntent().getIntExtra("idPelicula", 0);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDatabaseService service = retrofit.create(TheMovieDatabaseService.class);

        Call<ActoresRespuesta> call = service.obtenerActoresDePelicula(idPelicula, "589e10387e0ca4ece633f5836fb0383f");

        call.enqueue(new Callback<ActoresRespuesta>() {
            @Override
            public void onResponse(Call<ActoresRespuesta> call, Response<ActoresRespuesta> response) {

                ActoresRespuesta actoresRespuesta = response.body();
                ArrayList<Actor> actores = actoresRespuesta.getCast();

                ActoresAdapter adapter =  new ActoresAdapter(actores, ActoresActivity.this);
                actoresRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ActoresRespuesta> call, Throwable t) {

            }
        });
    }
}
