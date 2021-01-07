/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.Ticket;

/**
 *
 * @author Paul Idrovo
 */
public class ControladorTicket {
    public void crearTicket(Ticket ticket){
        Ticket tk = ticket;
        tk.crearTicket(tk);
        Parqueadero prq = new Parqueadero();
        prq.ActualizarParqieadero(tk.getParqueadero_id(),"RESERVADO");
    }

    public void setPagarParqueadero(Ticket ticket) {
        Ticket tk = ticket;
        tk.setPago(tk);
        Parqueadero prq = new Parqueadero();
        prq.ActualizarParqieadero(tk.getParqueadero_id(), "DISPONIBLE");
    }
}
