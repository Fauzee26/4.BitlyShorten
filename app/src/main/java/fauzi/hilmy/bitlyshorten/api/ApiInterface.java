package fauzi.hilmy.bitlyshorten.api;

import fauzi.hilmy.bitlyshorten.model.ResponseUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("shorten?login=o_6763834psq&apiKey=R_7ce47ae45dd24f03a25a43d23983d09d&format=json")
    Call<ResponseUrl> getUrl(
           @Query("longUrl") String url
    );
}
