<%-- 
    se usa concepto de modal de bootstrap
    para agregar una ventana modal al pregional el boton de agregar clientes ubicado en el JSP botonesNavegacion

L19 was-validated , valida si el campo esta vacio lo coloca como invalido, unicamente cambia cunado se tenga informacion en los input
type="email", telefono, numero, valida que sea deese tipo el campo
--%>

<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title"> Agregar Clientes</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <!--Creacion de formulario; todo lo que se agrega al formulario se envia a servlet controldor para insertar en DB -->
            
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST" class="was-validated">
            
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido" required>
                    </div>
                    <div class="form-group">E-mail</label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Telefono</label>
                        <input type="tel" class="form-control" name="telefono" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Saldo</label>
                        <input type="number" class="form-control" name="saldo" required step="any">
                    </div>
                </div>
                    
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
            
            </form>
                  
        </div>
    </div>
</div>
