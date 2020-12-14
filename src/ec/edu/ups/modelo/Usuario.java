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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Idrovo
 */
public class Usuario extends Persona {

    private int id;
    private String tipo;
    private String correo;
    private String password;
    private int persona_id;

    public Usuario() {
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id, String tipo, String correo, String password, int persona_id) {
        this.id = id;
        this.tipo = tipo;
        this.correo = correo;
        this.password = password;
        this.persona_id = persona_id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }



    public void crearUsuario(Usuario usuario) {
        String sqlstm = "INSERT INTO  `unidadeducativa`.`usuario` (tipo,`correo`, `password`,) "
                + "VALUES ('" + usuario.getTipo()+ "', '" + usuario.getCorreo() + "', '" + usuario.getPassword() + "')";
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

    public List<Usuario> getListaUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sqlstm = "Select * from parqueadero.usuario where tipo = 'user'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));                
                us.setCorreo(rs.getString("correo"));
                us.setPassword(rs.getString("password"));
                usuarios.add(us);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return usuarios;
    }

    public Usuario validarSesion(Usuario usuario) {
        String sqlstm = "SELECT * FROM  `parqueadero`.usuario where (correo = '" + usuario.getCorreo() + "') and (password = '" + usuario.getPassword() + "')";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Usuario usDatos = new Usuario();
                usDatos.setId(rs.getInt("id"));
                usDatos.setTipo(rs.getString("tipo"));
                return usDatos;
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR INICIO SESION", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuario) {
        String sqlstm = "UPDATE  `parqueadero`.`usuario` SET `correo` = '" + usuario.getCorreo()+ "', password = '"+ usuario.getPassword()+"' WHERE `id` = " + usuario.getId();
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);

        } catch (Exception e) {

        }
    }
    
    public Usuario extraerDatos (int id) {
        String sqlstm = "SELECT * FROM  `parqueadero`.usuario where (id = '" + id+ "')";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Usuario usDatos = new Usuario();
                usDatos.setCorreo(rs.getString("correo"));
                usDatos.setPassword(rs.getString("password"));
                return usDatos;
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR INICIO SESION", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
}
