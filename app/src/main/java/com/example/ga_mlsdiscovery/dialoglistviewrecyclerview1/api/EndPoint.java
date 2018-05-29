package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.api;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {
    @GET("photos")
    Call<List<Photo>> getPhotosForUser();
}
