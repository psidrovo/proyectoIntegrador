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

/**
 *
 * @author Paul Idrovo
 */
public class Tarifa {

    private int id;
    private String tipo;
    private double valorTarifa;

    public Tarifa() {
    }

    public Tarifa(String tipo, double valorTarifa) {
        this.tipo = tipo;
        this.valorTarifa = valorTarifa;
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

    public double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public void actualizarTarifas(Tarifa tarifa) {
        String sqlstm = "UPDATE  `parqueadero`.`tarifa` SET `valorTarifa` = " + tarifa.getValorTarifa() + " WHERE `tipo` = '" + tarifa.getTipo() + "'";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);

        } catch (Exception e) {

        }
    }

    public List<Tarifa> TarifasValores() {
        List<Tarifa> tarifas = new ArrayList<>();
        String sqlstm = "Select * from parqueadero.tarifa";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Tarifa tf = new Tarifa();
                tf.setTipo(rs.getString("tipo"));
                tf.setValorTarifa(rs.getDouble("valorTarifa"));
                tarifas.add(tf);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return tarifas;
    }
    
    public List<Tarifa> TarifasMembresia() {
        List<Tarifa> tarifas = new ArrayList<>();
        String sqlstm = "Select * from parqueadero.tarifa where id > 3";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Tarifa tf = new Tarifa();
                tf.setTipo(rs.getString("tipo"));
                tf.setValorTarifa(rs.getDouble("valorTarifa"));
                tarifas.add(tf);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return tarifas;
    }

    public List<Tarifa> TarifasRegular() {
        List<Tarifa> tarifas = new ArrayList<>();
        String sqlstm = "Select * from parqueadero.tarifa where id < 4";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Tarifa tf = new Tarifa();
                tf.setTipo(rs.getString("tipo"));
                tf.setValorTarifa(rs.getDouble("valorTarifa"));
                tarifas.add(tf);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR LISTA DE DOCENTES");
        }
        return tarifas;
    }
}
