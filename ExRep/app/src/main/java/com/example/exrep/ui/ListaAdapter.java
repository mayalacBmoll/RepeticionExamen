package com.example.exrep.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exrep.R;
import com.example.exrep.model.Ejercicio;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Ejercicio ejercicio);
    }

    private List<Ejercicio> ejercicios;
    private OnItemClickListener listener;

    public ListaAdapter(List<Ejercicio> ejercicios, OnItemClickListener listener) {
        this.ejercicios = ejercicios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ejercicio ejercicio = ejercicios.get(position);
        holder.bind(ejercicio, listener);
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView icono;
        private TextView titulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icono = itemView.findViewById(R.id.itemIcon);
            titulo = itemView.findViewById(R.id.itemTitle);
        }

        public void bind(Ejercicio ejercicio, OnItemClickListener listener) {
            icono.setImageResource(ejercicio.getIconoResId());
            titulo.setText(ejercicio.getTitulo());

            itemView.setOnClickListener(v -> listener.onItemClick(ejercicio));
        }
    }
}
