/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Datos.ClienteDaoJDBC;
import Dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador") // volvemos la calse un Servlet
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException{
        
        //recupera el listado de clientes, compartirlo en request a clientes.jsp
        
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        request.setAttribute("clientes",clientes); // se comparte en request
        request.getRequestDispatcher("clientes.jsp").forward(request, response); //enviamos informacion aJSP de clientes
        
    }
    
    
}
