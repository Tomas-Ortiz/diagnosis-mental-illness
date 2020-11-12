package Negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UtilidadGeneral {

    private static final List<String> paises = new ArrayList<>();

    public static boolean soloLetras(String cadena) {
        String soloLetras = "[a-zA-Z]+";
        return cadena.matches(soloLetras);
    }

    public static String getFechaActual() {
        Date date = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(date);
    }

    public static String getHoraActual() {
        Date date = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(date);
    }

    // Lista de paises que se mostrarán en la GUI
    public static List<String> getPaises() {
        if (!paises.isEmpty()) {
            return paises;
        }
        String[] countryCodes = java.util.Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String pais = locale.getDisplayCountry();
            paises.add(pais);
        }
        return paises;
    }
}
