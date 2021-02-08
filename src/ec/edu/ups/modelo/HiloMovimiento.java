/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.vista.VistaParqueaderos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Paul Idrovo
 */
public class HiloMovimiento extends Thread {

    private VistaParqueaderos vistaParqueaderos;
    private int numero;

    public HiloMovimiento(VistaParqueaderos vistaParqueaderos, int numero) {
        this.vistaParqueaderos = vistaParqueaderos;
        this.numero = numero;
    }

    @Override
    public void run() {
        int posicionLabelY = vistaParqueaderos.lblParqueaderos.get(numero).getBounds().y;
        posicionLabelY += 220;
        int posicionLabelX = 0;
        int numeroImagen = this.vistaParqueaderos.getValorDado();
        JLabel coche = new JLabel("");
        coche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/iconos/l" + numeroImagen + ".png")));
        while ((posicionLabelX+90) <= vistaParqueaderos.lblParqueaderos.get(numero).getBounds().x) {
            coche.setBounds(posicionLabelX, posicionLabelY, 250, 100);
            vistaParqueaderos.pnlParqueaderos.add(coche);
            vistaParqueaderos.pnlParqueaderos.updateUI();
            posicionLabelX += 3;
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMovimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        vistaParqueaderos.pnlParqueaderos.remove(coche);
        vistaParqueaderos.lblParqueaderos.get(numero).setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/iconos/v" + numeroImagen + ".png")));
        vistaParqueaderos.pnlParqueaderos.updateUI();

    }

}
