package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceGenerator {

    //Singleton
    private static ApiServiceGenerator apiServiceGenerator = null;

    //BaseURL
    private String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //Retrofit Builder
    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    //Retrofit instance
    private Retrofit retrofit = builder.build();

    //adding a logging interceptor
    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public <S> S createService(Class<S> serviceClass){
        if(!httpClient.interceptors().contains(logging)){
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

    //constructor for a singleton pattern
    private ApiServiceGenerator() {
    }

    public static ApiServiceGenerator getApiServiceGeneratorInstance() {
        if(apiServiceGenerator == null){
            apiServiceGenerator = new ApiServiceGenerator();
        }
        return apiServiceGenerator;
    }
}
