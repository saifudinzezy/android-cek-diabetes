package com.example.quisdiabetes.network;


import com.example.quisdiabetes.model.ResponseCRUD;
import com.example.quisdiabetes.model.history.HistoryItem;
import com.example.quisdiabetes.model.history.ResponseHistory;
import com.example.quisdiabetes.model.pasien.ResponsePasien;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("read/pasien.php")
    Call<ResponsePasien> getByUUIDHistory(@Field("uuid") String uuid);

    @FormUrlEncoded
    @POST("create/pasien.php")
    Call<ResponseCRUD> savePasien(@Field("uuid") String uuid, @Field("uuip") String uuip, @Field("umur") String umur,
                                  @Field("jenkel") String jenkel, @Field("rasio_dm") String rasioDm,
                                  @Field("pekerjaan") String pekerjaan, @Field("provinsi") String provinsi,
                                  @Field("no_hp") String noHp);

    @FormUrlEncoded
    @POST("create/question.php")
    Call<ResponseCRUD> saveHistory(@Field("uuid") String uuid, @Field("uuip") String uuip, @Field("question") String question,
                                   @Field("answer") String answer, @Field("skor") double skor);

    @FormUrlEncoded
    @POST("create/rating.php")
    Call<ResponseCRUD> saveRating(@Field("star") String star, @Field("ulasan") String ulasan);
}