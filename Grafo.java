/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;
import java.util.*;

/**
 *
 * @author CENTIC
 */
public class Grafo {
    // V = Vertices del grafo
    // A = aristas del grafo
    // matrizAdyacencia = Matriz que indica las conexiones entre dos vertices mediante '1' (conectado) y '0' (desconectado)
    // matrizPesos = Matriz que indica los peso de las aristas que conectan dos vertices
    // matrizRecorrido = Matriz que indica los vertices a seguir para lograr el camino mínimo entre dos de estos
    // * aristas = Lista enlazada que representa las uniones entre los vertices
    private int V;
    private int A;
    private boolean FW;
    private boolean firstI;
    private int[][] matrizAdyacencia;
    private double[][] matrizPesos;
    private double[][] matrizPesosFW;
    private int[][] matrizRecorrido;
    private LinkedList<Integer>[] aristas;
    // Constructor de la clase grafo, establece el valor de V como el número de nodos que se ingresa, el número de
    // aristas en cero e inicializa la matriz de adyacencia, pesos y recorrido
    public Grafo(int nodos){
        this.V = nodos;
        this.A = 0;
        this.matrizAdyacencia = new int[nodos][nodos];
        this.aristas = new LinkedList[nodos];
        for (int i = 0; i < V; i++) {
            aristas[i] = new LinkedList<>();
        }
        this.matrizPesos = new double[nodos][nodos];
        this.matrizPesosFW = new double[nodos][nodos];
        this.matrizRecorrido = new int[nodos][nodos];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j){
                    matrizRecorrido[i][j] = j;
                }
            }
        }
        FW = false;
        firstI = true;
    }
    //Método que imprime la matriz de adyacencia del grafo
    public void imprimirGrafo(){
        for (int v = 0; v < V; v++) {
            System.out.print("Fila " + v + ": ");
            for (int w = 0; w < V; w++) {
                System.out.print(matrizAdyacencia[v][w]+ " ");
            }
            System.out.println();
        }
    }
    // Método que imprime la matriz de pesos del grafo
    public void imprimirPesos() {
        for (int v = 0; v < V; v++) {
            System.out.print("Fila " + v + ": ");
            for (int w = 0; w < V; w++) {
                if(matrizPesos[v][w]==0 && v != w){
                    System.out.print("∞ ");
                }else {
                    System.out.print(matrizPesos[v][w] + " ");
                }
            }
            System.out.println();
        }
    }
    // Método que imprime la matriz de recorrido del algoritmo de Floyd-Warshall
    public void imprimirRecorrido() {
        for (int v = 0; v < V; v++) {
            System.out.print("Fila " + v + ": ");
            for (int w = 0; w < V; w++) {
                if(v == w){
                    System.out.print("- ");
                }else {
                    System.out.print(matrizRecorrido[v][w] + " ");
                }
            }
            System.out.println();
        }
    }
    // Método que agrega una arista que conecta a dos vértices, con su respectivo peso
    public void agregarArista(int u, int v, double p){
        matrizAdyacencia[u][v] = 1;
        //matrizAdyacencia[v][u] = 1;
        matrizPesos[u][v] = p;
        //matrizPesos[v][u] = p;
        aristas[u].add(v);
        //aristas[v].add(u);
        A++;
        FW = false;
    }
    // Método que imprime las listas enlazadas que representan las conexiones del grafo
    public void imprimirLista(){
        for (int i = 0; i < aristas.length; i++) {
            System.out.print("INICIO");
            System.out.print(" -> " + i);
            for (int j = 0; j < aristas[i].size(); j++) {
                System.out.print(" -> " + aristas[i].get(j));
            }
            System.out.print(" -> NULL");
            System.out.println();
        }
    }
    // Método que imprime las listas enlazadas que representan las aristas del grafo
    public void recorridoAnchura(int raiz){
        Queue<Integer> cola = new LinkedList<>();
        cola.add(raiz);
        boolean[] banderas = new boolean[aristas.length];
        for (int i = 0; i < aristas.length; i++) {
            banderas[i] = false;
        }
        System.out.print("INICIO");
        banderas[raiz] = true;
        while(!cola.isEmpty()){  
            int temp = cola.poll();
            System.out.print(" -> " + temp);
            for (int i = 0; i < aristas[temp].size(); i++) {
                if(!banderas[aristas[temp].get(i)]){
                    cola.add(aristas[temp].get(i));
                    banderas[aristas[temp].get(i)] = true;
                }
            }
        }
        System.out.print(" -> NULL");
        System.out.println();
    }
    // Método que imprime el recorrido en profundidad del grafo, indicada una raiz
    public void recorridoProfundidad(int raiz){
        Stack<Integer> stack = new Stack<>();
        stack.push(raiz);
        boolean[] banderas = new boolean[V];
        System.out.print("INICIO");
        while(!stack.isEmpty()){  
            int temp = stack.pop();
            if(!banderas[temp]){
                banderas[temp] = true;
                System.out.print(" -> " + temp);    
            }
            for (int v : aristas[temp]) {
                if(!banderas[v]){
                    stack.push(v);
                }
            }
        }
        System.out.print(" -> NULL");
        System.out.println();
    }
    public void actualizarFW(){
        matrizPesosFW = matrizPesos;
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++){
                double piv = matrizPesosFW[v][w];
                if(v != w && piv != 0){
                    for (int i = 0; i < V; i++) {
                        if(w != i && i != v && matrizPesosFW[i][v]!= 0){
                            double suma = piv + matrizPesosFW[i][v];
                            double valor = matrizPesosFW[i][w];
                            if(valor == 0 || valor > suma) {
                                matrizPesosFW[i][w] = suma;
                                // matrizPesosFW[i][w] = suma;
                                matrizRecorrido[i][w] = v;
                                // matrizRecorrido[i][w] = v;
                            }}}}}}
    }
    // Método que devuelve el camino minimo entre dos vertices y el peso de este camino
    public void caminoMinFW(int inicio, int destino){
        // Si está desactualizado, aplica el algoritmo y crea ambas tablas
        if(!FW){
            actualizarFW();
        }
        firstI = true;
        double peso = matrizPesos[inicio][destino];
        System.out.println("El camino más corto desde " + inicio
                + " a " + destino + " es " + StringFW(inicio, destino) + " y pesa " + peso);
    }
    public String StringFW(int inicio, int destino){
        if(!FW){
            actualizarFW();
        }
        String recorrido = "";
        // Si es la primera iteración, añade al recorrido el primer vértica
        if(firstI){
            recorrido += "" + inicio;
            firstI = false;
        }
        // Comprueba si ya se llegó al destino
        if(inicio != destino){
            // Comprueba si hay conexión con entre el vértice inicial y el que indica a seguir el algoritmo
            if(matrizAdyacencia[inicio][matrizRecorrido[inicio][destino]] == 0){
                // Si no la hay, cambia el destino al vértice indicado y después de la iteración imprime el destino
                recorrido += StringFW(inicio, matrizRecorrido[inicio][destino]);
                recorrido +=  " " + destino;
            }else{
                // Si la hay imprime el vértice indicado y este pasa a ser el nuevo inicio
                recorrido += " " + matrizRecorrido[inicio][destino];
                recorrido += StringFW(matrizRecorrido[inicio][destino], destino);
            }
        }
        return recorrido;
    }
    public void caminoMinDijkstra(int inicio, int destino){
        boolean[] checked = new boolean[V];
        int temp = inicio;
        double[] pesos = new double[V];
        double peso = 0;
        ArrayList<String> recorridos = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            recorridos.add(""+inicio);
        }
        String recorrido = "";
        for (int v = 0; v < V-1; v++) {
            checked[temp] = true;
            for (int w = 0; w < V; w++) {
                if (!checked[w] && matrizAdyacencia[temp][w] == 1){
                    double suma = peso + matrizPesos[temp][w];
                    if(suma < pesos[w] || pesos[w] == 0) {
                        pesos[w] = suma;
                        recorridos.set(w, recorridos.get(temp) + w);
                    }
                }
            }
            boolean first = true;
            for (int w = 0; w < V; w++) {
                double pesoW = pesos[w];
                if (!checked[w] && pesoW != 0 && (first || pesoW < peso)){
                    temp = w;
                    peso = pesoW;
                    first = false;
                }
            }
        }
        for (int v = 0; v < V; v++) {
            if(!checked[v]){
                temp = v;
                recorrido = recorridos.get(v);
                peso = pesos[v];
            }
        }
        System.out.println("El camino más corto desde " + inicio + " hasta " + destino + " es "
                + recorrido + " y tiene un peso de " + peso);
    }
    // Método que mediante el algoritmo de Prim devuelve una matriz de pesos que representa
    // el árbol recubridor mínmo, así como el peso de este árbol
    public void arbolPrim(){
        System.out.println("La matriz de pesos del grafo que representa el árbol recubridor mínimo es: ");
        boolean[] checked = new boolean[V];
        int raiz = (int)Math.floor(Math.random() * (V)),
        peso = 0;
        Grafo arbol = new Grafo(V);
        ArrayList<Integer> vertices = new ArrayList<>();
        vertices.add(raiz);
        boolean first = true;
        checked[raiz] = true;
        while (arbol.A < V) {
            int inicio= 0, destino = 0;
            double menor = 0;
            for (Integer vertice : vertices)
            for (int w = 0; w < V; w++) {
                if(matrizAdyacencia[vertice][w] == 1 && !checked[w] && (menor > matrizPesos[vertice][w] || first)){
                    menor = matrizPesos[vertice][w];
                    inicio = vertice;
                    destino = w;
                    first = false;
                }
            }
            peso += menor;
            arbol.agregarArista(inicio, destino, menor);
            checked[destino] = true;
            vertices.add(destino);
            first = true;
        }
        arbol.imprimirPesos();
        System.out.println("El peso del árbol es: " + peso);
    }
    // Método que mediante el algoritmo de Kruskal devuelve una matriz de pesos que representa
    // el árbol recubridor mínmo, así como el peso de este árbol
    public void arbolKruskal(){
        System.out.println("La matriz de pesos del grafo que representa el árbol recubridor mínimo es: ");
        ArrayList<Integer> grupos = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < V; i++) {
            grupos.add(0);
        }
        double peso = 0;
        Grafo arbol = new Grafo(V);
        boolean first = true;
        while (arbol.A < V) {
            int inicio= 0, destino = 0;
            double menor = 0;
            for (int v = 0; v < V; v++) {
                for (Integer w : aristas[v]) {
                    if (matrizAdyacencia[v][w] == 1 && (matrizPesos[v][w] < menor || first)){
                        if(grupos.get(v) != grupos.get(w) || grupos.get(v) == 0 || grupos.get(w) == 0){
                            menor = matrizPesos[v][w];
                            inicio = v;
                            destino = w;
                            first = false;
                        }
                    }
                }
            }
            peso += menor;
            arbol.agregarArista(inicio, destino, menor);
            if (grupos.get(inicio) == 0 && grupos.get(destino) == 0){
                id++;
                grupos.set(inicio, id);
                grupos.set(destino, id);
            } else if ( grupos.get(inicio) == 0 ){
                grupos.set(inicio, grupos.get(destino));
            } else if ( grupos.get(destino) == 0){
                grupos.set(destino, grupos.get(inicio));
            } else {
                int temp = grupos.get(destino);
                for (int v = 0; v < V; v++){
                    if(grupos.get(v) == temp){
                        grupos.set(v, grupos.get(inicio));
                    }
                }
            }
            first = true;
        }
        arbol.imprimirPesos();
        System.out.println("El peso del árbol es: " + peso);
    }
    public double getPesoFW(int inicio, int destino){
        return matrizPesosFW[inicio][destino];
    }
}

