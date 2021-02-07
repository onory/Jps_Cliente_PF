package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador") // volvemos la calse un Servlet
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recupera el listado de clientes, compartirlo en request a clientes.jsp
        
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        request.setAttribute("clientes", clientes); // se comparte en request
        request.setAttribute("totalClientes", clientes.size()); // envia a la tarjeta de lsitado clientes linea 81 el total de clientes
        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes)); // suma los saldos de cada cliente y los distribuye en request
        request.getRequestDispatcher("clientes.jsp").forward(request, response); //enviamos informacion aJSP de clientes
    }
    
    // regresa el total del saldo
    
    private double calcularSaldoTotal(List<Cliente> clientes){
        double saldoTotal =0;
        
        //itere los clientes y va sumndo el saldo ontenido por getSaldo
        
        for(Cliente cliente: clientes){
            saldoTotal += cliente.getSaldo();
        }
        
        return saldoTotal;
    }
}
