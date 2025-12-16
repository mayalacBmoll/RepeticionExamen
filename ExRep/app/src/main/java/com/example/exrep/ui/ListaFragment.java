package com.example.exrep.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exrep.R;
import com.example.exrep.data.DataParser;
import com.example.exrep.model.Ejercicio;
import com.example.exrep.viewmodel.EjerciciosViewModel;

import java.util.List;

public class ListaFragment extends Fragment {

    private List<Ejercicio> ejercicios;
    private ListaAdapter adapter;
    private EjerciciosViewModel viewModel;

    public interface OnEjercicioClickListener {
        void onEjercicioSelected(Ejercicio ejercicio);
    }

    private OnEjercicioClickListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnEjercicioClickListener) {
            listener = (OnEjercicioClickListener) context;
        } else {
            throw new RuntimeException("La actividad debe implementar OnEjercicioClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(EjerciciosViewModel.class);

        if (viewModel.getEjercicios().isEmpty()) {
            viewModel.getEjercicios().addAll(DataParser.cargarEjercicios(getContext()));
        }

        ejercicios = viewModel.getEjercicios();
        adapter = new ListaAdapter(ejercicios, ejercicio -> listener.onEjercicioSelected(ejercicio));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        ejercicios.add(ejercicio);
        adapter.notifyItemInserted(ejercicios.size() - 1);
    }

    public void refrescarLista() {
        adapter.notifyDataSetChanged();
    }


}