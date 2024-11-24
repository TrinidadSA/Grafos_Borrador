package bacon;

import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;

/**
 * La clase SixDegrees implenenta diferentes funcionalidades para resolver el juego de los seis grados de Bacon.
 */

public class SixDegrees {

    //Grafo que contiene a las peliculas y actores
    private GraphList grafo;

    /**
     * @post Construye una instancia de SixDegrees con la información contenida en la base de datos fileName.
     * @param fileName el nombre de un archivo que contine nombres de películas y sus respectivos actores y actrices.
     * @param delimiter el caracter especial que permite identificar la separación entre los elementos del archivo fileName.
     */
    public SixDegrees(String fileName, String delimiter) {
        
        grafo = new GraphList<>();
        lectorArchivo(fileName, delimiter);
    }
    
    /**
     * @post lee un archivo linea a linea, con la información dada se crea un grafo
     */
    private void lectorArchivo(String fileName, String delimiter)
    {
        try (BufferedReader ready = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = ready.readLine()) != null) {
                String[] palabras = line.split(delimiter);
                agregarVertices(palabras);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error");
        }
    }
    
    /**
     * @post Dado un arreglo de palabras, si no existen, las agrega al grado. 
     * Genera también aristas entre la primera palabra y el resto de las palabras
     */
    private void agregarVertices(String[] palabras){
        String pelicula = palabras[0];
        if (!grafo.containsVertex(pelicula)){
            grafo.addVertex(pelicula);            
        }
        
        for(int i=1; i < palabras.length; i++){
            if (!grafo.containsVertex(palabras[i])){
                grafo.addVertex(palabras[i]);
            }
            grafo.addEdge(pelicula, palabras[i]);
        }
    }

    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la película en la que trabajaron juntos act1 y act2, o null si no fueron co-protagonistas.
     */
    public String coStars(String act1, String act2) {
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("El actor no existe");
        
        List<String> peliculas_act1 = grafo.adj(act1);
        List<String> peliculas_act2 = grafo.adj(act2);
        
        for(String pelicula : peliculas_act1){
            if(peliculas_act2.contains(pelicula)){
                return pelicula;
            }
        }
     
        return null;
    }
    


    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la distancia de act1 a act2, donde distancia esta dada por:
     *       1, si trabajaron en la misma película
     *       (i+1), si act2 esta relacionado/a con algun actor o actriz que tiene distancia i con act1.
     */
    public int degreesOfSeparation(String act1, String act2){
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("El actor no existe");
        
        //grafo.buscardistanci = act1
        return 0; 
        //devuelve -1 si no hay camino
        //devuelve 0 si es el mismo actor
    }

    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la lista de actores y actrices involucrados en el camino para obtener la menor distancia de act1 a act2.
     */
    public List<String> pathOfSeparation(String act1, String act2) {
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("El actor no existe");
        //
        return null;
    }

    /**
     * @pre movie debe existir en el archivo pasado como parametro al constructor.
     * @post Retorna los actores y actrices que protagonizan movie.
     */
    public Set<String> actors(String movie) {
        //hashset new HashSet<>(grafo.adj(movie));
        return null;
    }

    /**
     * @pre act debe existir en el archivo pasado como parametro al constructor.
     * @post Retorna todas las peliculas en las que actua act.
     */
    public Set<String> movies(String act) {
        //hashset new HashSet<>(grafo.adj(act));
        return null;
    }
}

