package Grafos;

import com.sun.jdi.IntegerValue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Metro {
    // HashMap metro cuya llave es el nomobre de la ruta y su valor la lista de ids de estaciones de la ruta
    private HashMap<String, ArrayList<Integer>> metro;
    // ArrayList rutas que contiene los nombres de las rutas y su posición en la lista funciona como id
    private ArrayList<String> rutas;
    // ArrayList estaciones que contiene los nombres de las estaciones y su posición en la lista funciona como id
    private ArrayList<String> estaciones;
    // ArrayList pesos que contiene el tiempo que toma llegar a cada estación desde otra estación próxima
    private ArrayList<Double> pesos;
    // Constructor de la clase Metro que requiere un HashMap, ArrayList de estaciones y rutas para inicializar
    private HashMap<String, Double> news;
    public Metro(HashMap<String, ArrayList<Integer>> metro, ArrayList<String> estaciones, ArrayList<String> rutas, HashMap<String, Double> news) {
        this.metro = metro;
        this.estaciones = estaciones;
        this.rutas = rutas;
        this.news = news;
        pesos = new ArrayList<>();
        // Establece el tiempo por defecto de cada estación a 2 minutos
        for (String estacion : estaciones){
            pesos.add(2.0);
        }
        // Mediante el método random, cada evento tiene una probabilidad de suceder y afectar los pesos
        news.forEach((evento, modificador) -> {
            if (Math.random() < 0.5){
                System.out.println(evento);
                for (Double peso : pesos){
                    pesos.set(pesos.indexOf(peso), peso * modificador);
                }
            }
        });
    }
    // Método metroString que imprime la ruta más rápida, el tiempo que tiene y su recorrido, a su vez identifica la ruta correcta de entre las posibles
    public void metroString(String inicio, String destino){
        ArrayList<Integer> recorrido = new ArrayList<>();
        String rutaElegida = "";
        int peso = 0;
        boolean first = true;
        for(String ruta1 : ruta(inicio, destino)){
            Grafo grafo = graphMaker(ruta1);
            try {
                recorrido = estacionesIdx(grafo.StringFW(estaciones.indexOf(inicio), estaciones.indexOf(destino)));
                rutaElegida = ruta1;
                peso = (int) grafo.getPesoFW(estaciones.indexOf(inicio), estaciones.indexOf(destino));
            } catch (StackOverflowError e){}
        }
        String[] arrayRutas = rutaElegida.split(",");
        String rutaString = "";
        for (int i = 0; i < arrayRutas.length; i++) {
            if(!first){
                rutaString += " luego el bus ";
            }
            rutaString += arrayRutas[i];
            first = false;
        }
        String recorridoString = "";
        for (int i = 0; i < recorrido.size()-1; i++) {
            recorridoString += estaciones.get(recorrido.get(i))+ ", ";
        }
        recorridoString += estaciones.get(recorrido.get(recorrido.size()-1));
        System.out.println("La ruta más corta desde " + inicio + " hasta " + destino + " es tomando el bus " + rutaString + " tarda " + peso +
                        " minutos y su recorrido es: \n" + inicio + " " + recorridoString);
    }
    // Método estacionesIdx que obtiene un String desde la clase Grafo con el recorrido de la ruta y devuelve la lista de ids de las estaciones del recorrido
    public ArrayList<Integer> estacionesIdx(String recorrido){
        String[] estacionesStr = recorrido.split(" ");
        ArrayList<Integer> estacionesInt = new ArrayList<>();
        for (int i = 0; i < estacionesStr.length; i++) {
            try {
                estacionesInt.add(Integer.parseInt(estacionesStr[i]));
            }catch (NumberFormatException e){
                System.out.println(estacionesStr[i]);
            }
        }
        return estacionesInt;
    }
    // Método graphMaker que obtiene una o varias rutas y devuelve un grafo con su información
    public Grafo graphMaker(String ruta){
        String[] ruta1 = ruta.split(",");
        int nodos = estaciones.size();
        Grafo grafo = new Grafo(nodos);
        // Para cada ruta se crea una copia del ArrayList que contiene los ids de las rutas, con el id y peso se construyen las aristas del grafo
        for (int i = 0; i < ruta1.length; i++) {
            ArrayList<Integer> estacionesRuta = new ArrayList<>(metro.get(ruta1[i]));
            for (int j = 0; j < estacionesRuta.size()-1; j++) {
                grafo.agregarArista(estacionesRuta.get(j), estacionesRuta.get(j+1), pesos.get(estacionesRuta.get(j)));
            }
        }
        return grafo;
    }
    // Método ruta que devuelve una o varias rutas que ofrecen una conexión entre dos puntos inicio y destino
    public ArrayList<String> ruta (String inicio, String destino) {
        ArrayList<String> posibles = new ArrayList<>();
        int inicioInt = estaciones.indexOf(inicio);
        int destinoInt = estaciones.indexOf(destino);
        metro.forEach((ruta, estaciones) -> {
            if (estaciones.contains(inicioInt) && estaciones.contains(destinoInt)) {
                posibles.add(ruta);
            }
        });
        if (!posibles.isEmpty()) {
            return posibles;
        }
        for (String ruta1 : rutas) {
            if (metro.get(ruta1).contains(inicioInt)) {
                for (String ruta2 : rutas) {
                    if (ruta1 != ruta2 && metro.get(ruta2).contains(destinoInt)) {
                        for (Integer estacion1 : metro.get(ruta1)) {
                            if (metro.get(ruta2).contains(estacion1)){
                                posibles.add(ruta1 + "," + ruta2);
                            }
                        }}}}
        }
        if (!posibles.isEmpty()) {
            return posibles;
        }
        for (String ruta1 : rutas) {
            if (metro.get(ruta1).contains(inicioInt)) {
                for (String ruta2 : rutas) {
                    if (ruta1 != ruta2 && metro.get(ruta2).contains(destinoInt)) {
                        for (Integer estacion1 : metro.get(ruta1)) {
                            for (String ruta3 : rutas) {
                                if (ruta3 != ruta2 && ruta3 != ruta1 && metro.get(ruta3).contains(estacion1)) {
                                    for (Integer estacion2 : metro.get(ruta2)) {
                                        if (metro.get(ruta3).contains(estacion2)) {
                                            posibles.add(ruta1 + "," + ruta3 + "," + ruta2);
                                        }
                                    }}}}}}}
        }
        if (!posibles.isEmpty()) {
            return posibles;
        }
        for (String ruta1 : rutas) {
            if (metro.get(ruta1).contains(inicioInt)) {
                for (String ruta2 : rutas) {
                    if (ruta1 != ruta2 && metro.get(ruta2).contains(destinoInt)) {
                        for (Integer estacion1 : metro.get(ruta1)) {
                            for (String ruta3 : rutas) {
                                if (ruta3 != ruta2 && ruta3 != ruta1 && metro.get(ruta3).contains(estacion1)) {
                                    for (String ruta4 : rutas) {
                                        for (Integer estacion2 : metro.get(ruta2)) {
                                            if (ruta4 != ruta3 && ruta4 != ruta2 && ruta4 != ruta1 && metro.get(ruta4).contains(estacion2)) {
                                                for (Integer estacion3 : metro.get(ruta3)) {
                                                    if (metro.get(ruta4).contains(estacion3)) {
                                                        posibles.add(ruta1 + "," + ruta3 + "," + ruta4 + "," + ruta2);
                                                    }
                                                }}}}}}}}}}
        }
        if (!posibles.isEmpty()) {
            return posibles;
        }
        return posibles;
    }
    private void newsMachine() {

    }
}
