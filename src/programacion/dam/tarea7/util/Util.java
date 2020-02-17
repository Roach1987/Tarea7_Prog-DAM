package programacion.dam.tarea7.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import programacion.dam.tarea7.beans.Articulo;


/**
 *
 * @author Roach
 */
public class Util implements Serializable{
    // Pasar a clase principal del menu
    public static final String ARCHIVO_ARTICULOS = "articulos.dat";
    
    // Lista auxiliar tratamiento de objetos del fichero.
    private static ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
    
    // Lista auxiliar tratamiento articulos temporales.
    private static ArrayList<Articulo> listaArticulosTemporal = new ArrayList<Articulo>();
    
// *******************************************************************************************************    
// *********************************** CRUD Lista Articulos **********************************************
    
    /**
     * Método que crea un articulo en la lista, si este no existe previamente.
     * @param articulo 
     */
    public static void crearArticuloEnLista(Articulo articulo){
        // CREATE
        if(null != articulo && (null != listaArticulosTemporal && !listaArticulosTemporal.isEmpty())){
            if(null != articulo.getCodArticulo()){
                String codigoArticuloBuscar = articulo.getCodArticulo(); 
                Articulo articuloEncontrado = buscarArticuloPorCodigo(codigoArticuloBuscar, listaArticulosTemporal);
                
                if(null == articuloEncontrado){
                    listaArticulosTemporal.add(articulo);
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un articulo con el codigo ".concat(codigoArticuloBuscar),
                    "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Método que elimina un articulo existente en la lista.
     * @param codigoArticulo 
     */
    public static void borrarArticuloDeLista(String codigoArticulo){
        // DELETE
        if(null != codigoArticulo && (null != listaArticulosTemporal && !listaArticulosTemporal.isEmpty())){
            Articulo articuloEncontrado = buscarArticuloPorCodigo(codigoArticulo, listaArticulosTemporal);
            
            // Si existe un articulo con el mismo codigo que el articulo llegado por parametro
            // se elimina de la lista.
            if(null != articuloEncontrado){
                listaArticulosTemporal.remove(articuloEncontrado);
            }else{
                JOptionPane.showMessageDialog(null, "No existe un articulo con codigo ".concat(codigoArticulo),
                "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Método que actualiza un articulo existente en la lista de articulos.
     * Si no existe, se notificara con un panel con el mensaje correspondiente.
     * @param articulo
     */
    public static void actualizarArticuloEnLista(Articulo articulo){
        // UPDATE
        if(null != articulo && (null != listaArticulosTemporal && !listaArticulosTemporal.isEmpty())){
            if(null != articulo.getCodArticulo()){
                String codigoArticuloBuscar = articulo.getCodArticulo();
                Articulo articuloEncontrado = buscarArticuloPorCodigo(codigoArticuloBuscar, listaArticulosTemporal);
            
                if(null != articuloEncontrado){
                    // Si existe un articulo con el mismo codigo que el articulo llegado por parametro
                    // se borra y se guarda el nuevo, seteando los atributos del objeto con los llegados por parametro.
                    if(null != articulo.getDescripcion()){
                        articuloEncontrado.setCodArticulo(articulo.getDescripcion());
                    }

                    if(null != articulo.getPrecio()){
                        articuloEncontrado.setPrecio(articulo.getPrecio());
                    }

                    if(null != articulo.getDescuento()){
                        articuloEncontrado.setDescuento(articulo.getDescuento());
                    }

                    if(null != articulo.getIva()){
                        articuloEncontrado.setIva(articulo.getIva());
                    }

                    if(null != articulo.getListaFamiliaArticulo() && !articulo.getListaFamiliaArticulo().isEmpty()){
                        articuloEncontrado.setListaFamiliaArticulo(articulo.getListaFamiliaArticulo());
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No existe un articulo con codigo ".concat(codigoArticuloBuscar),
                    "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "El articulo no tiene codigo.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            if(null == articulo){
                JOptionPane.showMessageDialog(null, "El articulo no existe.",
                    "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
            
            if(null == listaArticulosTemporal || listaArticulosTemporal.isEmpty()){
                JOptionPane.showMessageDialog(null, "La lista esta vacia, no se puede actualizar.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
    /**
     * Método que devuelve la lista de articulos temporales.
     * @return listaArticulos
     */
    public static ArrayList<Articulo> listarArticulosTemporal(){
        // READ
        return listaArticulosTemporal;
    }
     
// *******************************************************************************************************
// *******************************************************************************************************
    
// *******************************************************************************************************    
// ********************************** CRUD fichero Articulos *********************************************
// *******************************************************************************************************
    
    /**
     * Método que añade los articulos que se encuentran el la lista temporal.
     */
    public static void addArticuloFichero(){
        // UPDATE
        try {
            // Intentamos crear el fichero y su objeto dentro de un try pasando por parametro 
            // estos dos Streams sin catch, si falla por estar vacio no lanzara ninguna excepcion y añadira
            // los articulos con el objeto ObjectOutputStream
            // "try con autocierre de Streams"
            try (
                // lee la informacion del archivo.
                FileInputStream ficheroEntrada = new FileInputStream(ARCHIVO_ARTICULOS);
                    
                // traduce la infromacion del archivo en datos
                ObjectInputStream objetoEntrada = new ObjectInputStream(ficheroEntrada) 
            ) {
                // lee todos los objetos que esten en el array
                listaArticulos = (ArrayList<Articulo>) objetoEntrada.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("El fichero esta vacío");
        }

        // Introduce en el array los datos de los nuevos articulos
        listaArticulos.addAll(listaArticulosTemporal);

        ObjectOutputStream objetoSalida;
        // Se crea el flujo para poder escribir en "articulos.dat"
        try (FileOutputStream ficheroSalida = new FileOutputStream(ARCHIVO_ARTICULOS)) {
            
            // prepara la forma de escritura para "articulos.dat" que en este caso sera escribir un objeto
            objetoSalida = new ObjectOutputStream(ficheroSalida);
            
            // Escribe en el archivo el Array de objetos "listaArticulos"
            objetoSalida.writeObject(listaArticulos);
            
            // Cerramos el objeto de salida.
            objetoSalida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en el fichero ".concat(ex.getMessage()),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        // Limpiamos la lista temporal.
        listaArticulosTemporal.clear();
    }
    
    /**
     * Método que elimina un articulo del fichero, si este existe.
     * @param codigoArticulo 
     */
    public static void borrarArticuloFichero(String codigoArticulo){
        // DELETE
        listaArticulos = listarArticulosFichero();
        Articulo articuloBorrar = buscarArticuloPorCodigo(codigoArticulo, listaArticulos);
       
        // Borramos el articulo de la lista si este existe.
        if(null != articuloBorrar){
            listaArticulos.remove(articuloBorrar);
        }
        
        try{
            ObjectOutputStream objetoSalida;
            try (FileOutputStream ficheroSalida = new FileOutputStream(ARCHIVO_ARTICULOS);) {
                objetoSalida = new ObjectOutputStream(ficheroSalida);
                objetoSalida.writeObject(listaArticulos);
            }
            objetoSalida.close();
        }catch(IOException ex){
            System.out.println("*** Error en fichero " + ex.getMessage() + " ***");
        }
    }
    
    /**
     * Método que lista los articulos que posee el fichero.
     * @return listaArticulos
     */
    public static ArrayList<Articulo> listarArticulosFichero(){
        // READ
        try {
            // lee la informacion del archivo
            FileInputStream ficheroEntrada = new FileInputStream(ARCHIVO_ARTICULOS);
            
            // traduce la informacion del archivo en datos
            ObjectInputStream objetoEntrada = new ObjectInputStream(ficheroEntrada);    
            
            // lee todos los objetos que esten en el array
            listaArticulos = (ArrayList<Articulo>) objetoEntrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El fichero esta vacío.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return listaArticulos;
    }

// *******************************************************************************************************
// *******************************************************************************************************    
    

// *******************************************************************************************************    
// ****************************************** Utilidades *************************************************
// *******************************************************************************************************
    
    /**
     * Método utilitario que busca en la lista llegada por parametro, y devuelve el coincidente con el codigo
     * llegado por parametro.
     * @param codigoArticulo
     * @param lista
     * @return 
     */
    public static Articulo buscarArticuloPorCodigo(String codigoArticulo, ArrayList<Articulo> lista){
        // SEARCH
        Articulo respuesta = null;
        for(Articulo articulo : lista){
            if(articulo.getCodArticulo().equals(codigoArticulo)){
                respuesta = articulo;
                break;
            }
        }
        return respuesta;
    }
    
// *******************************************************************************************************
// *******************************************************************************************************    
}
