package com.Juan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    List<Usuario> list;

    public UsuarioAdapter(List<Usuario> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, int i) {
        usuarioViewHolder.bindUsuario(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder{

        TextView tvId;
        TextView tvTitle;
        TextView tvBody;

        public UsuarioViewHolder(View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.id);
            tvTitle = itemView.findViewById(R.id.title);
            tvBody = itemView.findViewById(R.id.body);
        }

        public void bindUsuario (Usuario usuario){
            String title = usuario.getTitle();
            tvTitle.setText(title);
            String body = usuario.getBody();
            tvBody.setText(body);
            tvId.setText(String.valueOf(usuario.getId()));
        }
    }
}
