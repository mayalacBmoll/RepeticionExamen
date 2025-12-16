package com.example.exrep.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exrep.R;
import com.example.exrep.model.Ejercicio;

public class DetalleFragment extends Fragment {

    private static final String ARG_EJERCICIO = "arg_ejercicio";
    private Ejercicio ejercicio;

    public static DetalleFragment newInstance(Ejercicio ejercicio) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EJERCICIO, ejercicio);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            ejercicio = (Ejercicio) getArguments().getSerializable(ARG_EJERCICIO);
        }

        TextView title = view.findViewById(R.id.detailTitle);
        ImageView icon = view.findViewById(R.id.detailIcon);
        LinearLayout subtitulosContainer = view.findViewById(R.id.detailSubtitleContainer);

        title.setText(ejercicio.getTitulo());
        icon.setImageResource(ejercicio.getIconoResId());

        subtitulosContainer.removeAllViews();
        for (String sub : ejercicio.getSubtitulos()) {
            TextView t = new TextView(getContext());
            t.setText(sub);
            t.setTextSize(16);
            subtitulosContainer.addView(t);
        }
    }
}