<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="ServletCliente">
            Nombre:
            <input type="text" name="nombre_cliente"/><br>
            Apellido:
            <input type="text" name="apellido_cliente"/><br>
            Correo:
            <input type="text" name="correo_cliente"/><br>
            Nickname:
            <input type="text" name="nick_cliente"/><br>
            Password:
            <input type="password" name="password_cliente"/><br>
            Tipo Cliente:
            <input type="text" name="tipo_cliente"/><br>

            <input type="hidden" name="redireccion" value="listarCliente.jsp">
            <input type="hidden" name="accion" value="grabar"/><br>
            <button type="submit" >Agregar</button>

        </form>

</html>
