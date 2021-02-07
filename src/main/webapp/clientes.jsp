<%-- 
    Document   : clientes
    Created on : 6 feb. 2021, 17:59:49
    Author     : onory

iteramos cada eleemnto de lista de lcientes
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de clientes</title>
    </head>
    <body>
        <h1>Control de clientes</h1>
        
    
        
        <ul>
            <c:forEach var="cliente" items="${clientes}">
                
                <li>
                    ${cliente.idCliente} ${cliente.nombre} ${cliente.apellido} ${cliente.saldo}
                </li>
                
            </c:forEach>
            
        </ul>
        
    </body>
</html>
