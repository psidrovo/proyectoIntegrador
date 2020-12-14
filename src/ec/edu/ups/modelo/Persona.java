/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Idrovo
 */
public class Persona {

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Persona() {
    }

    public Persona( String cedula, String nombre, String apellido, String direccion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public boolean crearPersona(Persona persona) {
        String sqlstm = "INSERT INTO  `parqueadero`.`persona` (`cedula`, `nombre`,`apellido`,`direccion`,telefono) "
                + "VALUES ('" + persona.getCedula() + "', '" + persona.getNombre() + "', '" + persona.getApellido() + "', '" + persona.getDireccion() + "', '" + persona.getTelefono()+ "')";
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
                    JOptionPane.showMessageDialog(null, "YA EXISTE UNA CEDULA REGISTRADA CON ESE NUMERO", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
    }

    public int idPersona(String cedula) {
        String sqlstm = "SELECT * FROM parqueadero.persona where cedula = '" + cedula + "'";
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
    
    //UTILIZANDO
     public List<Persona> getPersonas() {
        List<Persona> personas = new ArrayList<Persona>();
        String sqlstm = "SELECT cedula, nombre, id FROM  `parqueadero`.persona";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Persona p = new Persona();
                p.setNombre(rs.getString("nombre"));
                p.setCedula(rs.getString("cedula"));
                p.setId(rs.getInt("id"));
                personas.add(p);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

        }
        return personas;
    }
     
     
     public Persona getPersona(String cedula) {
        String sqlstm = "SELECT * FROM parqueadero.persona where cedula = '" + cedula + "'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Persona pr = new Persona();
                pr.setId(rs.getInt("id"));
                pr.setCedula(rs.getString("cedula"));
                pr.setNombre(rs.getString("nombre"));
                pr.setApellido(rs.getString("apellido"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setTelefono(rs.getString("telefono"));
                return pr;
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR CEDULA", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return null;

    }
    

}
