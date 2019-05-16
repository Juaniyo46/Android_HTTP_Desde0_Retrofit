package com.Juan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioService {
    @GET("posts")
    Call<List<Usuario>> getUsuarios();
}
