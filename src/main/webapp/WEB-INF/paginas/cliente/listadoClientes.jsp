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

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-striped">
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
                                                                      <!-- Se puede agregar la etiqueta varStatus="status"-->
                                                                      <!-- Para mostrar un valir en idcliente auto incrementable que no sea de la DB-->
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <!-- si usamos varStatus cambiamos l asentencia <td>$-{-status.count-}-</td> sin de lo contrario lo obtiene de la DB--->
                                    <td>${status.count}</td> 
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <!-- ejerce un formato internacional MX sobre la consulta en EL de saldo -->
                                    <td><fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>
                                    <td>
                                        <!-- boton que manda la accion de editar y el valor? de idCliente que se modificara por peticion GET-->
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
                <h3>Saldo Total</h3>
                <h4 class="dusplay-4">
                    <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                </h4>
            </div>
        </div>
        
        <!-- tarjetas total clientes -->
        
        <div class="card text-center bg-success text-white mb-3">
            <div class="card-body">
                <h3>Total Clientes</h3>
                <h4 class="display-4">
                    <i class="fas fa-users"></i>${totalClientes}
                </h4>
            </div>
        </div>  
                
    </div>
    
    <!--Fin Tarjetas para los totales-->
        </div>
    </div>
</section>
                
<!-- incluir el JSP agregar cliente modal -->

<jsp:include page="/WEB-INF/paginas/cliente/agregarCliente.jsp"/>