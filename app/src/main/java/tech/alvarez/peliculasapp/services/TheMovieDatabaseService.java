package tech.alvarez.peliculasapp.services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.alvarez.peliculasapp.models.Pelicula;
import tech.alvarez.peliculasapp.models.PeliculasCineRespuesta;

public interface TheMovieDatabaseService {


    @GET("movie/now_playing?language=es")
    Call<PeliculasCineRespuesta> obtenerPeliculasEnCines(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Pelicula> obtenerDetallePelicula(@Path("id") int id , @Query("api_key") String apiKey);


    @GET("movie/{id}/credits")
    Call<ActoresRespuesta> obtenerActoresDePelicula(@Path("id") int id , @Query("api_key") String apiKey);

}
