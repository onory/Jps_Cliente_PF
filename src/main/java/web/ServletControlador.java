package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador") // volvemos la clase un Servlet
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recuperamos parametro de accion para editar
        String accion = request.getParameter("accion");
        
        //validamos si accion tiene valor
        if(accion != null){
            
            switch (accion){
                
                case "editar": //llama un metdo para editar cliente
                    this.editarCliente(request, response);
                    break;
                default:    
                   this.accionDefault(request, response); //redirije a  JSP clientes
            }
        }else{
             this.accionDefault(request, response); //redirije a  JSP clientes si la accion es null
                
        }
        
        
        
        //recupera el listado de clientes, compartirlo en request a clientes.jsp por el metodo  accionDefault
        this.accionDefault(request, response);
        
    }
    
    // anteriormente se haci en metodo doget, como paguina de iniciio, sin embargo se requerira en otro JSP por ello se separa en un metodo mas
    
     private void accionDefault (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //redirije a  JSP clientes
        
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        HttpSession sesion = request.getSession(); // compartimos la informacion en alcande sesion para que pueda ser accedida y no en request
        sesion.setAttribute("clientes", clientes); // se comparte en request
        sesion.setAttribute("totalClientes", clientes.size()); // envia a la tarjeta de lsitado clientes linea 81 el total de clientes
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes)); // suma los saldos de cada cliente y los distribuye en request
        //request.getRequestDispatcher("clientes.jsp").forward(request, response); //enviamos informacion aJSP de clientes foeward es un cambio interno del servidor no se avia a cliente, puede provocar renviar el formilario
        response.sendRedirect("clientes.jsp");//envia la informacion al JSP clientes pero evita el refresh para duplicar informacion del formulario , sendRedirect notifica al cliente
        
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
    
    //metodo para editar clientes
    
    private void editarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recupera el id cliente del listado para ser editado
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        // no coloca el fi para validar el dato como saldo, dado que es el mismoa uto incrementable de la DB
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar; //este JSP desplegara al info recuperada de la DB
        jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request,response);
        
    }
       
    //sobre-escribimos el metodo doPost para ser usado desde JSP agregar clientes
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recuperamos parametro de accion para insertar
        String accion = request.getParameter("accion");
        
        //validamos si accion tiene valor
        if(accion != null){
            
            switch (accion){
                
                case "insertar": //llama un metdo para insertar cliente
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);  
                    break;
                default:    
                   this.accionDefault(request, response); //redirije a  JSP clientes
            }
        }else{
             this.accionDefault(request, response); //redirije a  JSP clientes si accin es null
                
        }
        
   }
    //Metodo para insertar clientes con la informacion del formulario del JSP agregarcliente
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recupera informacion del formulario para poder insertar el lciente en la DB y redirigir a  accionDefault
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo =0;
        String saldoString = request.getParameter("saldo"); //recibimos saldo como string
        // si convertimos lo que coloquen y no es numero puede crear un error por ello validara antes de convertir
        if(saldoString != null && !"".equals(saldoString)){
            
            //realiza conversion del valor que se esta recibiendo y lo guarda en saldo
            saldo = Double.parseDouble(saldoString);
       }
        
        
        //Creamos el objeto de cliente(modelo) con los datos que estamos recibiendo del formulario
        
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        
        //insertamos en la DB
        
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registros modificados = " + registrosModificados);
        
        //redirigimos haciala accion por default a JSP cliente
        this.accionDefault(request, response);
    }
    
    //Metodo para insertar clientes con la informacion del formulario del JSP agregarcliente
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //recupera informacion del formulario del JSP editarCliente
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente")); // recuperamos el valor de idCliente
        String nombre = request.getParameter("nombre");//recuperamos el valor de nombre
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo =0;
        String saldoString = request.getParameter("saldo"); //recibimos saldo como string
        // si convertimos lo que coloquen y no es numero puede crear un error por ello validara antes de convertir
        if(saldoString != null && !"".equals(saldoString)){
            
            //realiza conversion del valor que se esta recibiendo y lo guarda en saldo
            saldo = Double.parseDouble(saldoString);
       }
        
        
        //Creamos el objeto de cliente(modelo) con los datos que estamos recibiendo del formulario
        
        Cliente cliente = new Cliente(idCliente,nombre, apellido, email, telefono, saldo);
        
        //modificamos en la DB
        
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registros modificados = " + registrosModificados);
        
        //redirigimos haciala accion por default a JSP cliente
        this.accionDefault(request, response);
    }
}
