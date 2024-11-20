
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
    
    public int[] bfs(T s){
        int ss = map.get(s);
        int[] edgeTo = new int[V()];
        boolean[] marked = new boolean[V()];
        
        Queue<Integer> q = new LinkedList<Integer>();
        marked[ss] = true;
        q.add(ss);
        
        
        while (!q.isEmpty()) {
            int v = q.poll();
            for(T w : adj(keys[v])) {
                int z = map.get(w);
                if (!marked[z]){
                    marked[z] = true;
                    edgeTo[z] = v;
                    q.add(z);
                }
            }
            
        }
        return edgeTo;
    }
    
    //arme arreglo entre act1 y act2 pasando arreglo de caminos    

    
}
