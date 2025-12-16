package com.example.exrep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.exrep.model.Ejercicio;
import com.example.exrep.ui.DetalleFragment;
import com.example.exrep.ui.ListaFragment;
import com.example.exrep.ui.MenuDialogFragment;
import com.example.exrep.viewmodel.EjerciciosViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListaFragment.OnEjercicioClickListener, MenuDialogFragment.OnMenuDialogResult {

    private boolean esLandscape;

    private EjerciciosViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(EjerciciosViewModel.class);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_form) {
                MenuDialogFragment dialog = new MenuDialogFragment();
                dialog.show(getSupportFragmentManager(), "menuDialog");
                return true;
            }
            return false;
        });

        esLandscape = findViewById(R.id.fragmentContainerLista) != null;

        if (esLandscape) {
            // Landscape → cargar lista y detalle vacío
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerLista, new ListaFragment())
                    .commit();
        } else {
            // Portrait → todo en un solo contenedor
            showFragment(new ListaFragment());
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onEjercicioSelected(Ejercicio ejercicio) {

        if (esLandscape) {

            // Landscape → usar contenedor derecho
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerDetalle, DetalleFragment.newInstance(ejercicio))
                    .commit();

        } else {

            // Portrait → usar contenedor único
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, DetalleFragment.newInstance(ejercicio))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onMenuAccepted(String titulo, String primerSubtitulo) {

        List<String> subtitulos = new ArrayList<>();
        if (!primerSubtitulo.isEmpty()) subtitulos.add(primerSubtitulo);

        int icono = R.drawable.ic_brazos;

        Ejercicio nuevo = new Ejercicio(titulo, subtitulos, icono);

        // Guardar en el ViewModel
        viewModel.agregarEjercicio(nuevo);

        // Notificar al fragmento que se recargue
        ListaFragment listaFragment =
                (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (listaFragment != null) {
            listaFragment.refrescarLista();
        }

    }
}