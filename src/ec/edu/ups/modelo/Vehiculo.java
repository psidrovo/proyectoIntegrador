/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

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
public class Vehiculo {

    private int id;
    private String placa;
    private String marca;
    private String color;
    private int persona_id;

    public Vehiculo() {
    }

    public Vehiculo( String placa, String marca, String color, int persona_id) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.persona_id = persona_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }

    public List<Vehiculo> getPlacasCedula(int id) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sqlstm = "Select * from parqueadero.vehiculo where persona_id = '" + id + "'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Vehiculo vh = new Vehiculo();
                vh.setPlaca(rs.getString("placa"));
                vehiculos.add(vh);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return vehiculos;
    }

    public List<Vehiculo> getMarcas() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sqlstm = "Select marca from parqueadero.vehiculo order by vehiculo.marca";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Vehiculo vh = new Vehiculo();
                vh.setMarca(rs.getString("marca"));
                vehiculos.add(vh);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return vehiculos;
    }

    public List<Vehiculo> getColores() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sqlstm = "Select color from parqueadero.vehiculo order by vehiculo.color";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Vehiculo vh = new Vehiculo();
                vh.setColor(rs.getString("color"));
                vehiculos.add(vh);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return vehiculos;
    }

    public boolean crearVehiculo(Vehiculo vehiculo) {
        String sqlstm = "INSERT INTO  `parqueadero`.`vehiculo` (`placa`, `marca`,`color`,`persona_id`) "
                + "VALUES ('" + vehiculo.getPlaca()+ "', '" + vehiculo.getMarca()+ "','" + vehiculo.getColor()+ "', '" + vehiculo.getPersona_id()+ "')";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);
            return true;
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "Data truncation: Incorrect date value: 'null' for column 'fechaNacimiento' at row 1" -> {
                    JOptionPane.showMessageDialog(null, "FORMATO DE FECHA NO VALIDA", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                case "Data truncated for column 'genero' at row 1" -> {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN GENERO", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "ERROR AL CREAR VEHICULO", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
    }
    
    public int idVehiculo(String placa) {
        String sqlstm = "SELECT id FROM parqueadero.vehiculo where placa = '" + placa + "'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                return rs.getInt("id");
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR CEDULA", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return 0;

    }

    public Vehiculo getVehiculo(String placa) {
        String sqlstm = "SELECT * FROM parqueadero.vehiculo where placa = '" + placa + "'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Vehiculo vh = new Vehiculo();
                vh.setId(rs.getInt("id"));
                vh.setPlaca(rs.getString("placa"));
                vh.setMarca(rs.getString("marca"));
                vh.setColor(rs.getString("color"));
                return vh;
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR CEDULA", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return null;

    }
}
