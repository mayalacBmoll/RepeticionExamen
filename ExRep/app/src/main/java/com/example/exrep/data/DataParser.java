package com.example.exrep.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.example.exrep.R;
import com.example.exrep.model.Ejercicio;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public static List<Ejercicio> cargarEjercicios(Context context) {
        List<Ejercicio> lista = new ArrayList<>();

        Resources res = context.getResources();
        XmlResourceParser parser = res.getXml(R.xml.data_elements);

        String title = null;
        int iconRes = 0;
        List<String> subtitulos = null;

        try {
            int evento = parser.getEventType();

            while (evento != XmlPullParser.END_DOCUMENT) {
                String nombreTag = parser.getName();

                switch (evento) {

                    case XmlPullParser.START_TAG:
                        if ("item".equals(nombreTag)) {
                            // Empezamos un nuevo ejercicio
                            subtitulos = new ArrayList<>();
                        } else if ("title".equals(nombreTag)) {
                            title = parser.nextText();
                        } else if ("icon".equals(nombreTag)) {
                            String iconName = parser.nextText().replace("@drawable/", "");
                            iconRes = res.getIdentifier(iconName, "drawable", context.getPackageName());
                        } else if ("subtitle".equals(nombreTag)) {
                            subtitulos.add(parser.nextText());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("item".equals(nombreTag)) {
                            // Creamos el objeto Ejercicio y lo agregamos
                            lista.add(new Ejercicio(title, subtitulos, iconRes));
                        }
                        break;
                }

                evento = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
