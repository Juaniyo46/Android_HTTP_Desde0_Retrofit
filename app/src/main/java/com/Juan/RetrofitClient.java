package com.Juan;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Esto es el Singelton ya que comprueba si esxiste y en caso de existir te da el que ya tenias creado.

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
