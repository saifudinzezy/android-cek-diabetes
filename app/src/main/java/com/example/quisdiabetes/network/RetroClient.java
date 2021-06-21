package com.example.quisdiabetes.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static Retrofit retrofit = null;
    public static String BASE_URL = "https://resikodm.000webhostapp.com/";
//    public static String BASE_URL = "http://resikodm.saepstudio.site/";
    //public static String BASE_URL = "http://192.168.0.104/resikodm/";

    private static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService(){
        return getClient().create(ApiService.class);
    }
}
