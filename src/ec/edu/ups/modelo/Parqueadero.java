/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import com.itextpdf.text.Paragraph;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Idrovo
 */
public class Parqueadero {

    private int id;
    private String estado;
    private int numero;
    private int ticket_id;

    public Parqueadero() {
    }

    public Parqueadero(String estado, int numero, int ticket_id) {
        this.estado = estado;
        this.numero = numero;
        this.ticket_id = ticket_id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    
    
    public void crearParqueadero(Parqueadero parqueadero) {
        String sqlstm = "INSERT INTO  `parqueadero`.`parqueadero` (`estado`, `numero`, `ticket_id`) "
                + "VALUES ('" + parqueadero.getEstado()+ "', '" + parqueadero.getNumero()+ "', '" + parqueadero.getTicket_id() + "')";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR DE DATOS", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarDatosParqueadero() {
        String sqlstm = "TRUNCATE `parqueadero`.`parqueadero`";
        try {
           ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR DATOS", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public List<Parqueadero> getParqueaderosLibres() {
        String sqlstm = "SELECT * FROM parqueadero.parqueadero WHERE estado != 'DISPONIBLE' order by numero ASC";
        List<Parqueadero> listaParqueaderos = new ArrayList<>();
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                listaParqueaderos.add(new Parqueadero(rs.getString("estado"), rs.getInt("numero"), rs.getInt("ticket_id")));
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR CONTAR PARQUEADEROS", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return listaParqueaderos;
    }
    
    public List<Parqueadero> getParqueaderos() {
        String sqlstm = "SELECT * FROM parqueadero.parqueadero order by numero ASC";
        List<Parqueadero> listaParqueaderos = new ArrayList<>();
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                listaParqueaderos.add(new Parqueadero(rs.getString("estado"), rs.getInt("numero"), rs.getInt("ticket_id")));
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR CONTAR PARQUEADEROS", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return listaParqueaderos;
    }

    public int getMaximoOcupado() {
        String sqlstm = "SELECT * FROM parqueadero.parqueadero WHERE estado != 'DISPONIBLE' order by numero DESC";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                return rs.getInt("numero");
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR CONTAR PARQUEADEROS", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return 0;
    }

    public void ActualizarParqieadero(int parqueadero_id, String estado) {
        String sqlstm = "UPDATE  `parqueadero`.`parqueadero` SET `estado` = '"+estado+"' WHERE `id` = " + parqueadero_id ;
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);

        } catch (Exception e) {

        }
    }

}
