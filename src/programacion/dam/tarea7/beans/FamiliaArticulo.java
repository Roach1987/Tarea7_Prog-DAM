package programacion.dam.tarea7.beans;

import java.io.Serializable;

/**
 *
 * @author Roach_Mimi
 */
public class FamiliaArticulo implements Serializable{
    
    // Atributos
    private String nombre;
    
    // Constructores
    public FamiliaArticulo(){
    }
    
    public FamiliaArticulo(String nombre){
        this.nombre = nombre;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
