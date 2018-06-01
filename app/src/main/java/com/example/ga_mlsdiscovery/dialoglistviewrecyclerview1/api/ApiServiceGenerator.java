package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.api;

import android.content.Context;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceGenerator {
    Cache cache;
    int cacheSize = 10 * 1024 * 1024; // 10MB

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

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    okhttp3.Response response = chain.proceed(request);

                    //overwriting an existing header. use caution
                    okhttp3.Response hackedResponse = response
                            .newBuilder()
                            .header("Cache-Control", "public, max-age=1")
                            .build();
                    return hackedResponse;
                }
            });

    public <S> S createService(Class<S> serviceClass, Context context){
        cache = new Cache(context.getCacheDir(), cacheSize);
        if(!httpClient.interceptors().contains(logging)){
            httpClient.addInterceptor(logging);
            httpClient.cache(cache);
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
