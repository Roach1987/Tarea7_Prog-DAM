package programacion.dam.tarea7.util;

import java.io.Serializable;
import java.util.ArrayList;
import programacion.dam.tarea7.beans.Articulo;

/**
 *
 * @author Roach
 */
public class Util implements Serializable{
//    // Pasar a clase principal del menu
//    public static final String ARCHIVO_ARTICULOS = "articulos.dat";
//    private static ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
    
// *******************************************************************************************************    
// *********************************** CRUD Lista Articulos **********************************************
    public static void crearArticuloEnLista(Articulo articulo, ArrayList<Articulo> listaArticulos){
        // CREATE
    }
    
    public static void borrarArticuloDeLista(String codigoArticulo, ArrayList<Articulo> listaArticulos){
        // DELETE
    }
    
    public static void actualizarArticuloEnLista(Articulo articulo, ArrayList<Articulo> listaArticulos){
        // UPDATE
    }
            
    /**
     * Método que devuelve la lista de articulos.
     * @param listaArticulos
     * @return 
     */
    public static ArrayList<Articulo> listarArticulos(ArrayList<Articulo> listaArticulos){
        return listaArticulos;
    }         
            
    public static Articulo buscarArticuloPorCodigo(String codigoArticulo, ArrayList<Articulo> listaArticulos){
        Articulo respuesta = null;
        // SEARCH
        return respuesta;
    }
    
// *******************************************************************************************************
    
// *******************************************************************************************************    
// ********************************** CRUD fichero Articulos *********************************************
    
    
// *******************************************************************************************************
}
