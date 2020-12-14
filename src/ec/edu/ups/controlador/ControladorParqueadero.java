/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Parqueadero;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Idrovo
 */
public class ControladorParqueadero {

    public void actualizarCantidadParqueadero(int num) {
        Parqueadero parqueadero = new Parqueadero();
        int maximoParqueadero = parqueadero.getMaximoOcupado();
        if (maximoParqueadero < num) {
            List<Parqueadero> numeroParqueaderosOcupados = parqueadero.getParqueaderosLibres();
            parqueadero.eliminarDatosParqueadero();
            int cont = 0;
            for (int i = 1; i <= num; i++) {
                if (numeroParqueaderosOcupados.size()>0) {
                    if (numeroParqueaderosOcupados.get(cont).getNumero() == i) {
                        parqueadero.crearParqueadero(numeroParqueaderosOcupados.get(cont));
                        cont++;
                    } else {
                        parqueadero.crearParqueadero(new Parqueadero("DISPONIBLE", i, 0));
                    }
                }else{
                    parqueadero.crearParqueadero(new Parqueadero("DISPONIBLE", i, 0));
                }
            }
            JOptionPane.showMessageDialog(null, "ACTUALIZACION COMPLETA", "ACTUALIZADO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "EL NUMERO DE PARQUEADEROS A ACTUALIZAR ES MENOR AL ULTIMO NUMERO OCUPADO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
