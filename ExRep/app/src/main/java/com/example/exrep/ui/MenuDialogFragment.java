package com.example.exrep.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.exrep.R;

public class MenuDialogFragment extends DialogFragment {

    public interface OnMenuDialogResult {
        void onMenuAccepted(String titulo, String primerSubtitulo);
    }

    private OnMenuDialogResult callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMenuDialogResult) {
            callback = (OnMenuDialogResult) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMenuDialogResult");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_menu_form, null);

        EditText fieldTitulo = view.findViewById(R.id.inputField1);
        EditText fieldSubtitulo = view.findViewById(R.id.inputField2);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnAccept = view.findViewById(R.id.btnAccept);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnAccept.setOnClickListener(v -> {
            String titulo = fieldTitulo.getText().toString().trim();
            String subtitulo = fieldSubtitulo.getText().toString().trim();

            if (!titulo.isEmpty()) {
                callback.onMenuAccepted(titulo, subtitulo);
            }

            dialog.dismiss();
        });

        return dialog;
    }
}