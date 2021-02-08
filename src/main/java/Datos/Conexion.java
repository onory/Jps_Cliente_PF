/*
 * Capa de Datos
 * En esta calse se define la URL de la cponecion con la BD
 * los datos de ingresos ocmo usuario y pasword
 * asi como el metodo para crear la conexion con DB y para cerrarla.
 */
package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

//Metodo para recuperar coneccion a DB

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    private static BasicDataSource dataSource; //este objeto sera el que se utilizara para efectuar las aconexiones, asi se evita crear barios inecesario
    
    public static DataSource getDataSource(){ //La libreria de esta clase se agrego en el POM
        if(dataSource == null){
        dataSource = new BasicDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USER);
        dataSource.setPassword(JDBC_PASSWORD);
        dataSource.setInitialSize(50); //determina las conexiones a DB pool de conexiones
        }
        return dataSource;
    }
    
    //Metodo para obtener conexion
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
     //Metodo para cerrar los objetos de ResultSet
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
   //Metodo para cerrar los objetos de PreparedStatement
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
    //Metodo para cerrar los objetos de connectio
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
