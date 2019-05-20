package com.Juan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String baseUrl = "https://jsonplaceholder.typicode.com/";

    RecyclerView rvUsuarios;
    List<Usuario> usuarioList = new ArrayList<>();

    Button getBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBtn = findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarControles();

               /* Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();*/

                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                rvUsuarios.setLayoutManager(llm);

                final UsuarioAdapter[] adapter = {new UsuarioAdapter(usuarioList)};
                rvUsuarios.setAdapter(adapter[0]);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(rvUsuarios.getContext(),llm.getOrientation());
                rvUsuarios.addItemDecoration(itemDecoration);

                //En la siguiente linea le pasamos el RetrofitCliente con la url base para el Singelton.
                UsuarioService usuarioService = RetrofitClient.getClient(baseUrl).create(UsuarioService.class);

                Call<List<Usuario>> listCall = usuarioService.getUsuarios();
                listCall.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if (response.isSuccessful()){
                            usuarioList = response.body();
                            adapter[0] = new UsuarioAdapter(usuarioList);
                            rvUsuarios.setAdapter(adapter[0]);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {

                    }
                });
            }
        });




        //iniciarControles();
    }

    private void iniciarControles(){
        rvUsuarios = findViewById(R.id.rvUsuarios);
    }
}
