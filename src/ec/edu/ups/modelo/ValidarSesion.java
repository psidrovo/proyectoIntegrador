/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

/**
 *
 * @author Paul Idrovo
 */
public class ValidarSesion {

    private int id;
    private String tipo;
    private static ValidarSesion miconfigurador;

    public static synchronized ValidarSesion getValidarSesion(int id,String tipo) {

        if (miconfigurador == null) {

            miconfigurador = new ValidarSesion(id,tipo);
        }
        return miconfigurador;
    }

    public ValidarSesion(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
