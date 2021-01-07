/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Idrovo
 */
public class Ticket extends Usuario{

    private int id;
    private String tipo;
    private String fechaEntrada;
    private String fechaSalida;
    private double valor;
    private int vehiculo_id;
    private int parqueadero_id;
    private int usuario_id_ingreso;
    private int usuario_id_cobro;

    public Ticket() {
    }

    public Ticket(String tipo, String fechaEntrada, String fechaSalida, double valor, int vehiculo_id, int parqueadero_id, int usuario_id_ingreso, int usuario_id_cobro) {
        this.tipo = tipo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.vehiculo_id = vehiculo_id;
        this.parqueadero_id = parqueadero_id;
        this.usuario_id_ingreso = usuario_id_ingreso;
        this.usuario_id_cobro = usuario_id_cobro;
    }

    public int getUsuario_id_ingreso() {
        return usuario_id_ingreso;
    }

    public void setUsuario_id_ingreso(int usuario_id_ingreso) {
        this.usuario_id_ingreso = usuario_id_ingreso;
    }

    public int getUsuario_id_cobro() {
        return usuario_id_cobro;
    }

    public void setUsuario_id_cobro(int usuario_id_cobro) {
        this.usuario_id_cobro = usuario_id_cobro;
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

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(int vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public int getParqueadero_id() {
        return parqueadero_id;
    }

    public void setParqueadero_id(int parqueadero_id) {
        this.parqueadero_id = parqueadero_id;
    }

    public boolean crearTicket(Ticket ticket) {
        String sqlstm = "INSERT INTO  `parqueadero`.`ticket` (`tipo`, `fechaEntrada`,`fechaSalida`,`valor`,vehiculo_id, usuario_id_ingreso, usuario_id_cobro,parqueaderoNumero) "
                + "VALUES ('" + ticket.getTipo() + "', " + ticket.getFechaEntrada() + "," + ticket.getFechaSalida() + ", '" + ticket.getValor() + "','" + ticket.getVehiculo_id() + "','" + ticket.getUsuario_id_ingreso() + "','" + ticket.getUsuario_id_cobro() + "','" + ticket.getParqueadero_id() + "')";
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
                    JOptionPane.showMessageDialog(null, "ERROR AL CREAR TICKET", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
    }

    public Ticket recuperarDatos(int id) {
        String sqlstm = "SELECT * FROM  `parqueadero`.ticket where id = " + id;
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Ticket tk = new Ticket();
                tk.setFechaEntrada(rs.getString("fechaEntrada"));
                tk.setFechaSalida(rs.getString("fechaSalida"));
                tk.setTipo(rs.getString("tipo"));
                tk.setValor(rs.getDouble("valor"));
                tk.setParqueadero_id(rs.getInt("parqueaderoNumero"));
                tk.setVehiculo_id(rs.getInt("vehiculo_id"));
                return tk;
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR INICIO SESION", "ERROR DE DATOS", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public List<Ticket> getTicketFecha(String fechaInicio, String fechaFinal) {
        List<Ticket> tickets = new ArrayList<>();
        String sqlstm = "select pt.id, pt.tipo, pt.fechaEntrada, pt.fechaSalida, pt.valor, vh.placa, pr.cedula, us.correo from parqueadero.ticket as pt \n"
                + "inner join parqueadero.vehiculo as vh on vh.id=pt.vehiculo_id \n"
                + "inner join parqueadero.persona as pr on pr.id=vh.persona_id \n"
                + "inner join parqueadero.usuario as us on us.id=pt.usuario_id_cobro\n"
                + " where (fechaEntrada between '" + fechaInicio + "' and '" + fechaFinal + "') and valor != 0.0";
        try {
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();//Direcion
            Statement stmt = conn.createStatement();//Puerta
            ResultSet rs; //Resultados
            rs = stmt.executeQuery(sqlstm);
            while (rs.next()) {
                Ticket tk = new Ticket();
                tk.setId(rs.getInt("id"));
                tk.setTipo(rs.getString("tipo"));
                tk.setFechaEntrada(rs.getString("fechaEntrada"));
                tk.setFechaSalida(rs.getString("fechaSalida"));
                tk.setValor(rs.getDouble("valor"));
                tk.setCorreo(rs.getString("correo"));
                tk.setCedula(rs.getString("cedula"));
                tk.setDireccion(rs.getString("placa"));
                tickets.add(tk);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {

        }
        return tickets;
    }

    public void setPago(Ticket tk) {
        String sqlstm = "UPDATE  `parqueadero`.ticket SET valor  = '" + tk.getValor() + "', fechaSalida = '"+tk.getFechaSalida()+"' WHERE id = " + tk.getId();
        try {     
            ConexionSql.getConnection();
            Connection conn = ConexionSql.getConn();                        
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlstm);                
            
        } catch (Exception e) {
        }
    }

}
