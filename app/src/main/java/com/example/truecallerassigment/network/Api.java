package com.example.truecallerassigment.network;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    @GET()
     Observable<String> getTrueCallerUrlContent(@Url String url);
}
