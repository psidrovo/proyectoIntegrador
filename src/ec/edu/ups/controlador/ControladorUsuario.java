/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Usuario;

/**
 *
 * @author Paul Idrovo
 */
public class ControladorUsuario {

    public Usuario inicioSesion(Usuario usuario) {
        usuario = usuario.validarSesion(usuario);
        return usuario;
    }
    
}
