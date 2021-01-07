
package ec.edu.ups.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paul Idrovo
 */
public class ConexionSql {
    
    private static Connection connex;
    private static final String driver = "com.mysql.cj.jdbc.Driver";    
    private static  String user = "ejemplos";
    private static final String password = "ejemplos"; 
    
    private static final String url = "jdbc:mysql://localhost:3306/parqueadero?serverTimezone=UTC";

    public static Connection getConn() {
        return connex;
    }

    public static void setConn(Connection conn) {
        ConexionSql.connex = conn;
    }    
   
    public static Connection getClose() throws SQLException{
        connex.close();
        return connex;
    }
    
    public static Connection getConnection() throws Exception{

        if(connex == null || connex.isClosed()){
            try {
                Class.forName(driver);
                connex = DriverManager.getConnection(url,user,password);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return connex;
    }   
}
