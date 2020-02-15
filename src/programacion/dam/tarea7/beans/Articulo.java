package programacion.dam.tarea7.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Roach
 */
public class Articulo implements Serializable{
    
    // Atributos
    private String codArticulo;
    private String descripcion;
    private double precio;
    private double descuento;
    private int iva;
    private ArrayList<FamiliaArticulo> listaFamiliaArticulo;
    
    // Constructores
    public Articulo(){
        listaFamiliaArticulo = new ArrayList<>();
    }
    
    public Articulo(String codArticulo, String descripcion, double precio, double descuento, int iva,
            ArrayList<FamiliaArticulo> listaFamiliaArticulo){
        this.codArticulo = codArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.iva = iva;
        this.listaFamiliaArticulo = listaFamiliaArticulo;        
    }
    
    // Getters y Setters
    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public ArrayList<FamiliaArticulo> getListaFamiliaArticulo() {
        return listaFamiliaArticulo;
    }

    public void setListaFamiliaArticulo(ArrayList<FamiliaArticulo> listaFamiliaArticulo) {
        this.listaFamiliaArticulo = listaFamiliaArticulo;
    }
}


