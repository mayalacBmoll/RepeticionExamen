package com.example.exrep.model;

import java.io.Serializable;
import java.util.List;

public class Ejercicio implements Serializable {

    private String titulo;
    private List<String> subtitulos;
    private int iconoResId;

    public Ejercicio(String titulo, List<String> subtitulos, int iconoResId) {
        this.titulo = titulo;
        this.subtitulos = subtitulos;
        this.iconoResId = iconoResId;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public List<String> getSubtitulos() {
        return subtitulos;
    }

    public int getIconoResId() {
        return iconoResId;
    }

    // Setters opcionales
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulos(List<String> subtitulos) {
        this.subtitulos = subtitulos;
    }

    public void setIconoResId(int iconoResId) {
        this.iconoResId = iconoResId;
    }
}