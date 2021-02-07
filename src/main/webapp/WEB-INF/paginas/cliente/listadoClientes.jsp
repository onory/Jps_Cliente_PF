<%-- 
taglib se usa en esta paguia para acer uso del core desde este jsp

ejemplo de despliegue de informacion
<ul>
    <c:forEach var="cliente" items="${clientes}">
    <li>${cliente.idCliente} ${cliente.nombre} ${cliente.apellido} ${cliente.saldo}</li>
    </c:forEach>
</ul>

--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ejerce un formato internacional para la apresiacion de los datos -->
<fmt:setLocale value="es_MX"/> 

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>saldo</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- iteramos cada elemento de la lista de clientes-->
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <!-- ejerce un formato internacional MX sobre la consulta en EL de saldo -->
                                    <td><fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>
                                    <td>
                                        <!-- boton que manda la accion de editar y el valor? de idCliente que se modificara -->
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary">
                                           <i class="fas fa-angle-double-right"></i> Editar
                                        </a>   
                                    </td>
                                </tr>
                                
                                
                            </c:forEach>  
                        </tbody>
                        
                        
                    </table>
                </div>
            </div>
    <!-- tarjetas para msotrar total de lcientes y total de saldo -->
    
    <div class="col-md-3">
        
        <!-- tarjetas saldo total -->
        
        <div class="card text-center bg-danger text-white mb-3">
            <div class="card-body">
                <h3>Saldo total</h3>
                <h4 class="dusplay-4">
                    <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                </h4>
            </div>
        </div>
        
        <!-- tarjetas total clientes -->
        
        <div class="card text-center bg-success text-white mb-3">
            <div class="card-body">
                <h3>Total Clientes</h3>
                <h4 class="dusplay-4">
                    <i class="fas fa-users"></i>${totalClientes}
                </h4>
            </div>
        </div>  
                
    </div>
    
    
        </div>
    </div>
</section>