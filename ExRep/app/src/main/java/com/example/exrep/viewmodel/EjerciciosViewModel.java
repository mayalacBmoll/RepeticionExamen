package com.example.exrep.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.exrep.model.Ejercicio;
import java.util.ArrayList;
import java.util.List;

public class EjerciciosViewModel extends ViewModel {

    private final List<Ejercicio> ejercicios = new ArrayList<>();

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        ejercicios.add(ejercicio);
    }
}
