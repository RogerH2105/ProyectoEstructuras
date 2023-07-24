package Grafos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Roger Hernández
 */
public class Main {
    public static void main(String[] args) {
        // demoMetro();
    }
    public static void demoMetro(){
        ArrayList<String> rutas = new ArrayList<>();
        ArrayList<String> estaciones = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> metro = new HashMap<>();
        HashMap<String, Double> news = new HashMap<>();
        // 0
        estaciones.add("Estación Lagos");
        // 1
        estaciones.add("Estación de Transferencia Cañaveral");
        // 2
        estaciones.add("Estación Payador");
        // 3
        estaciones.add("Estación de Transferencia Provenza");
        // 4
        estaciones.add("Estación La Isla");
        // 5
        estaciones.add("Estación La Rosita");
        // 6
        estaciones.add("Estación Chorreras");
        // 7
        estaciones.add("Estación San Mateo");
        // 8
        estaciones.add("Estación Quebradaseca");
        // 9
        estaciones.add("Portal Piedecuesta");
        // 10 S/N
        estaciones.add("Puente Españolina");
        // 11 S/N
        estaciones.add("Puente Campoalegre");
        // 12
        estaciones.add("Estación Palmichal");
        // 13
        estaciones.add("Estación Mensulí");
        // 14
        estaciones.add("Estación Estancia");
        // 15 S/N
        estaciones.add("Mac Pollo");
        // 16
        estaciones.add("Eds Aranzoque");
        // 17 S/N
        estaciones.add("Papi Quiero Piña");
        // 18
        estaciones.add("Universidad Santo Tomás");
        // 19
        estaciones.add("Lagos II Carrera 8 Calle 39");
        // 20
        estaciones.add("Calle 19 Carrera 5 Plaza Colmena");
        // 21
        estaciones.add("Calle 41 Carrera 4");
        // 22
        estaciones.add("Carrera 3 Calle 48");
        // 23
        estaciones.add("Colegio Vicente Azuero");
        // 24
        estaciones.add("Sector 3-2");
        // 25
        estaciones.add("Sector 6-5 Bucarica");
        // 26
        estaciones.add("Sector 13-3");
        // 27
        estaciones.add("Salud Total Eps");
        // 28
        estaciones.add("Sector 18-19");
        // 29
        estaciones.add("Carrera 6 Calle 5");
        // 30
        estaciones.add("Calle 7a Carrera 5 Caracolí");
        // 31
        estaciones.add("Despacho Bucarica");
        // 32
        estaciones.add("Calle 8 Carrera 6");
        // 33
        estaciones.add("Carrera 7 Calle 5");
        // 34
        estaciones.add("Sector 18-19");
        // 35
        estaciones.add("Bucarica Sector 19-9");
        // 36
        estaciones.add("Mercomfenalco");
        // 37
        estaciones.add("Sector 10-10");
        // 38
        estaciones.add("Cancha Bucarica");
        // 39
        estaciones.add("Sector 3-3");
        // 40
        estaciones.add("Iglesia Santa María De Los Lagos");
        // 41
        estaciones.add("Calle 29 Carrera 9 Lagos");
        // 42
        estaciones.add("Supermercado La Canasta");
        // 43
        estaciones.add("El Remanso Carrera 12 Calle 27");
        // 44
	estaciones.add("Estación Diamante");
	// 45 S/N
	estaciones.add("Centro Recreacional Comfenalco");
	// 46 S/N
	estaciones.add("Estadio Álvaro Gómez Hurtado");
        ArrayList<Integer> T2_S_N = new ArrayList<>();
        T2_S_N.add(0);
        T2_S_N.add(1);
        T2_S_N.add(2);
        T2_S_N.add(3);
        T2_S_N.add(4);
        T2_S_N.add(5);
        T2_S_N.add(6);
        T2_S_N.add(7);
        T2_S_N.add(8);
        T2_S_N.add(44);
        rutas.add("T2_S_N");
        metro.put("T2_S_N", T2_S_N);
        ArrayList<Integer> T2_N_S = new ArrayList<>();
        T2_N_S.add(44);
        T2_N_S.add(8);
        T2_N_S.add(7);
        T2_N_S.add(6);
        T2_N_S.add(5);
        T2_N_S.add(4);
        T2_N_S.add(3);
        T2_N_S.add(2);
        T2_N_S.add(1);
        T2_N_S.add(0);
        rutas.add("T2_N_S");
        metro.put("T2_N_S", T2_N_S);
        ArrayList<Integer> T4 = new ArrayList<>();
        T4.add(9);
        T4.add(10);
        T4.add(11);
        T4.add(12);
        T4.add(13);
        T4.add(14);
        T4.add(15);
        T4.add(16);
        T4.add(17);
        T4.add(18);
        T4.add(0);
        T4.add(1);
        T4.add(2);
        T4.add(3);
        T4.add(45);
        T4.add(46);
        // metro.put("T4", temp);
        ArrayList<Integer> P5_N_S = new ArrayList<>();
        P5_N_S.add(1);
        P5_N_S.add(19);
        P5_N_S.add(20);
        P5_N_S.add(21);
        P5_N_S.add(22);
        P5_N_S.add(23);
        P5_N_S.add(24);
        P5_N_S.add(25);
        P5_N_S.add(26);
        P5_N_S.add(27);
        P5_N_S.add(28);
        P5_N_S.add(29);
        P5_N_S.add(30);
        P5_N_S.add(31);
        rutas.add("P5_N_S");
        metro.put("P5_N_S", P5_N_S);
        ArrayList<Integer> P5_S_N = new ArrayList<>();
        P5_S_N.add(31);
        P5_S_N.add(32);
        P5_S_N.add(33);
        P5_S_N.add(34);
        P5_S_N.add(35);
        P5_S_N.add(36);
        P5_S_N.add(37);
        P5_S_N.add(38);
        P5_S_N.add(39);
        P5_S_N.add(23);
        P5_S_N.add(21);
        P5_S_N.add(20);
        P5_S_N.add(40);
        P5_S_N.add(41);
        P5_S_N.add(42);
        P5_S_N.add(43);
        P5_S_N.add(1);
        rutas.add("P5_S_N");
        metro.put("P5_S_N", P5_S_N);

        news.put("Lluvia", 1.2);
        news.put("Día sin carro", 0.9);

        Metro metrolinea = new Metro(metro, estaciones, rutas, news);
        metrolinea.metroString("Estación de Transferencia Cañaveral", "Estación La Isla");
        System.out.println();
        metrolinea.metroString("Estación La Rosita", "Colegio Vicente Azuero");
    }
    public static void demo(){
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0,1, 1);
        grafo.agregarArista(1,2, 1);
        grafo.agregarArista(2,3, 1);
        grafo.agregarArista(3,4, 1);
        try{
            grafo.caminoMinFW(4,0);
        } catch (StackOverflowError e){
            System.out.println("Camino imposible");
        }
    }
    public static void demoGrafo(){
        Grafo grafo = new Grafo(9);
        grafo.agregarArista(4, 3, 1);
        grafo.agregarArista(3, 2, 1);
        grafo.agregarArista(2, 1, 1);
        grafo.agregarArista(1, 0, 1);
        grafo.agregarArista(1, 5, 1);
        grafo.agregarArista(5, 6, 1);
        grafo.agregarArista(6, 7, 1);
        grafo.agregarArista(7, 8, 1);
        grafo.agregarArista(8, 9, 1);
        grafo.caminoMinFW(1,4);
        grafo.caminoMinFW(4,0);
        grafo.caminoMinFW(0,3);
        grafo.caminoMinFW(3,0);
        grafo.caminoMinDijkstra(0,3);
        grafo.caminoMinDijkstra(3,0);
    }
}
