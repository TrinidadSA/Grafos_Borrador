
/**
 * Write a description of class GraphList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Queue;


/**
 *  Graph represents an undirected graph, where vertices store
 *  values of type Comparable.
 *  Formally, a graph G=<V,E> consists of a set of vertices V,
 *  and a relation E in VxV that describes the edges of the graph.
 */
public class GraphList<T extends Comparable<? super T>> implements Graph<T> {
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private List<Integer>[] adj;
    //private Map<T, Integer> vertiMaps;

    public GraphList(){
        adj = new LinkedList[0];
        V = 0;
        E = 0;
        map = new TreeMap<>();
        keys = (T[]) new Comparable[0];
        //vertiMaps = new HashMap<>();
    }

    /**
     * @post Returns the number of vertices in this graph.
     */
    public int V(){
        return V;
    }

    /**
     * @post Returns the number of edges in this graph.
     */
    public int E(){
        return E;
    }

    /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph.
     */
    public void addVertex(T v){
        if (containsVertex(v)) 
            throw new IllegalArgumentException("El vértice ya existe");
        //vertiMaps.put(v, V);
        map.put(v, V);
        if (V >= adj.length){
            resize((V+1)*2);
        }
        adj[V] = new LinkedList<>();
        keys[V] = v;
        V++;        
    }


    private void resize(int capacity){
        List<Integer>[] remplazo = (List<Integer>[]) new LinkedList[capacity];
        T[] remplazo2 = (T[]) new Comparable[capacity];
        for(int i=0; i<V; i++){
            remplazo[i] = adj[i];
            remplazo2[i] = keys[i];
        } 
        adj = remplazo;
        keys = remplazo2;
    }
    /**
     * @post Returns true iff there is a vertex with label v
     * in this graph.
     */
    public boolean containsVertex(T v){
        //return vertiMaps.containsKey(v);
        return map.containsKey(v);
    }

    /**
     * @pre v and w are vertices of the graph
     * @post Adds the undirected edge v-w to this graph.
     */
    public void addEdge(T v, T w){
        if (!containsVertex(v)) return;//excepcion
        if (!containsVertex(w)) return;//excepcion

        //int i= vertiMaps.get(v);

        //adj[i].add(w);
        E++;
        int vid = map.get(v); //indexOf?
        int wid = map.get(w); //indexOf??
        adj[vid].add(wid);
        adj[wid].add(vid);
    }

    /**
     * @pre v is a vertex of the graph
     * @post Returns the list of vertices adjacent to vertex v.
     */
    public List<T> adj(T v){
        List<T> elems = new LinkedList<>();
        if (containsVertex(v)){
            //Integer i = vertiMaps.get(v);
            Integer i = map.get(v);
            for(Integer elem : adj[i]){
                elems.add(keys[elem]);
            }
            
            //for(T elem : adj[i]){
            //    elems.add(elem);
            //}
        }
        return elems;
    }
    
    //rastrear nodo
       public List<T> rutaEntreVertices(T s, T n){
        //comprobar que nodo s existe (sino, IllegalArgumentException)
        //comprobar que nodo n existe (sino, IllegalArgumentException)
        //Llamo a bfs(s,n); esto me devuelve la lista de numeros que son los de la ruta de s a n
        //Funcion que elimina las peliculas de la lista, y reemplaza los numeros por Strings
        //Deberia retornar la lista de tipo T (en nuestro caso van a ser Strings)
        return null;
    }
    
    
    
    //bfs deberia recorrer el grafo desde el nodo s hasta encontrar el nodo n
    //y devolver la ruta
    public List<Integer> bfs(T s, T n){
        int ss = map.get(s);  //obtengo indice del vertice s
        int nn = map.get(n);  //obtengo indice del vertice s
        int[] edgeTo = new int[V()];  //guarda desde donde viene el recorrido
        boolean[] marked = new boolean[V()]; //gurda elementos marcados
        
        Queue<Integer> q = new LinkedList<Integer>();  //cola de los vertices que se van recorriendo
        marked[ss] = true;  // marca verdadera la posición del índice desde donde parto
        q.add(ss);  //agrego indice inicial a la cola
        
        
        while (!q.isEmpty()) {
            int v = q.poll();
            for(int w : adj[v]) {
                if (!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    q.add(w);
                    
                    if(w==nn){
                        //esto retornaria la ruta en numeros
                        return descubrirRuta(edgeTo, ss, nn); 
                    }
                }
            }
            
        }
        return null;
    }
    
    
    private List<Integer> descubrirRuta(int[] path, int ini, int fin){
        //Creo un linkedlist
        //creo variable pos que me guarda la posicion que busco
        //a pos le asigno fin
        //agregar el numero fin a la lista
        //deberia haber un ciclo, que mientras lo que tenga path en la posición pos 
        //sea distinto a ini, 
            //agrego a la lista lo que tiene path en la posición pos
            //actualizo el pos, con lo que tiene path en la posicion pos
        //cuando llego a posicion que teine guardado ini finalizo el ciclo
        //agrego ini a la lista
        //Todas las veces que agrego cosas deberian ser al principio de la lista
        //creo que addFirst(elemento)  hace eso
        
        //retornar la lista creada
        return null;
    }
    
    
    private List<T> obtenerRutaAcotada(List<Integer> path){
        //En teoria el primer elemeto seria un actor, despues pelicula, despues actor
        //crear una nueva lista del tipo T
        //recorrer path
            //deberia tratar un elemento si, otro no, otro si, otro no...otro si
            //para cada indice de la lista deberia buscar su valor en keys[]
            //cada elemento lo agrego a la nueva lista que cree
        //retorno la lista creada -- en teoria deberia tener solo los nombres de los actores
        return null;
    }
      

}
