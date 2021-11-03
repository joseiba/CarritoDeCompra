<%@page import="ComprasOnLine.modelosClases.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%User usuario = (User)request.getAttribute("usuario");%>
 
        <form method="POST" action="ServletCliente">
            id:
            <input type="hidden" value ="<%=usuario.getId()%>" name="id_cliente"/><br>
            Nombre:
            <input type="text" value ="<%=usuario.getNombre()%>" name="nombre_cliente"/><br>
            Apellido:
            <input type="text" value ="<%=usuario.getApellido()%>" name="apellido_cliente"/><br>
            Correo:
            <input type="text" value ="<%=usuario.getEmail()%>" name="correo_cliente"/><br>
            Nickname:
            <input type="text" value ="<%=usuario.getLoginName()%>" name="nick_cliente"/><br>
            Password:
            <input type="password" value ="<%=usuario.getPasswd()%>" name="password_cliente"/><br>
            Tipo Cliente:
            <input type="text" value ="<%=usuario.getTipoCliente()%>" name="tipo_cliente"/><br>

            <input type="hidden" name="redireccion" value="listarCliente.jsp">
            <input type="hidden" value ="actualizar" name="accion"/><br>
            <button type="submit" >Guardar Cambios</button>
            
        </form>
    </body>
</html>
