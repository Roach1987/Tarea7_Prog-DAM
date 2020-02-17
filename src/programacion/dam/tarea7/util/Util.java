package programacion.dam.tarea7.util;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import programacion.dam.tarea7.beans.Articulo;
import programacion.dam.tarea7.beans.FamiliaArticulo;

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
     * M�todo que crea un articulo en la lista, si este no existe previamente.
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
     * M�todo que elimina un articulo existente en la lista.
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
     * M�todo que actualiza un articulo existente en la lista de articulos.
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
                        ArrayList<FamiliaArticulo> listaFamilia = new ArrayList<>();
                        // A�adimos las familias que ya estaban en el articulo
                        articuloEncontrado.getListaFamiliaArticulo().forEach((familia) -> {
                            listaFamilia.add(familia);
                        });
                        // Eliminamos las familias que ya estaban disponibles en el articulo.
                        articuloEncontrado.getListaFamiliaArticulo().forEach((familia) -> {
                            articuloEncontrado.getListaFamiliaArticulo().remove(familia);
                        });
                        // A�adimos las familias que lleguen en el nuevo articulo.
                        articulo.getListaFamiliaArticulo().forEach((familia) -> {
                            listaFamilia.add(familia);
                        });
                        // A�adimos la lista resultante al articulo que se va a modificar
                        articuloEncontrado.setListaFamiliaArticulo(listaFamilia);
                    }
                    borrarArticuloDeLista(codigoArticuloBuscar);
                    crearArticuloEnLista(articuloEncontrado);
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
     * M�todo que devuelve la lista de articulos.
     * @return listaArticulos
     */
    public static ArrayList<Articulo> listarArticulosTemporal(){
        // READ
        return listaArticulosTemporal;
    }
    
    /**
     * M�todo utilitario que busca en la lista llegada por parametro, y devuelve el coincidente con el codigo
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
    
// *******************************************************************************************************    
// ********************************** CRUD fichero Articulos *********************************************
    
    
// *******************************************************************************************************
}
