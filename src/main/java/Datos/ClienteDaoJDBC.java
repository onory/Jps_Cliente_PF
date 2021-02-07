package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + " FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + " FROM cliente WHERE id_cliente = ?";

    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) "
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE cliente "
            + " SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    
    //metodo qie lista la consulta de  SQL_SELECT
    public List<Cliente> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            //iniciamos conecxion
            conn = Conexion.getConnection(); // abre la conexion
            stmt = conn.prepareStatement(SQL_SELECT); // seleccion la sentencia pre diseñada
            rs = stmt.executeQuery(); //ejecuta el querty 
                      //next se mueve en el puntero de la lista
            while (rs.next()) { //itera los campos de la consulta select que se esta usando
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente); // se agrega al listado de clientes
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally { // cerramos la conecxion
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //retornamos el listado de clientes
        return clientes;
    }

    //metodo para encontar un objeto tipo cliente
    public Cliente encontrar(Cliente cliente) { //resive un objeto tipo cliente
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; //recupera informacion
        
        try {
            //iniciamos conecxion
            conn = Conexion.getConnection(); // abre la conexion
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID); // seleccion la sentencia pre diseñada
            stmt.setInt(1, cliente.getIdCliente()); // se establece el parametro de idcliente que requiere la sentencia
            rs = stmt.executeQuery(); //ejecuta el querty
            rs.absolute(1);// nos posisciona en el primer registro que se requiere ene ste caos 1

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally { // cerramos la conecxion
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    // metodo par ainsertar un objeto cliente
    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //variable que controla los registros modificados
        
        try {
            //iniciamos conecxion
            conn = Conexion.getConnection(); // abre la conexion
            stmt = conn.prepareStatement(SQL_INSERT); // seleccion la sentencia pre diseñada
              //el numero representa el atributo que de declaro en la sentencia en orden
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally { // cerramos la conecxion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //variable que controla los registros modificados
        try {
            conn = Conexion.getConnection(); // abre la conexion
            stmt = conn.prepareStatement(SQL_UPDATE); // seleccion la sentencia pre diseñada
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally { // cerramos la conecxion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //variable que controla los registros modificados
        
        try {
            //iniciamos conecxion
            conn = Conexion.getConnection(); // abre la conexion
            stmt = conn.prepareStatement(SQL_DELETE); // seleccion la sentencia pre diseñada
            stmt.setInt(1, cliente.getIdCliente());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally { // cerramos la conecxion
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}
