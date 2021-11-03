<%@page import="java.util.ArrayList"%>
<%@page import="ComprasOnLine.modelosClases.User"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" name="redireccion" value="listarCliente.jsp">

        <%
            ArrayList<User> listaUsuarios = (ArrayList<User>) request.getAttribute("listaUsuarios");
        %>
        <div style="width: 1200px; margin-left: auto; margin-right: auto;">
            <h1>Lista de Usuarios</h1>
            <form action="ServletCliente" method="GET">
                <input type="hidden" name="redireccion" value="listarCliente.jsp">
                <input type="hidden" name="accion" value="agregar">
                <input type="submit" value="Agregar"/>
            </form>            <p>Total de Usuarios : ${longitud}</p>
                <table cellpadding="10" border='1'>
                    <tr>
                        <th>Nro</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Nick</th>
                        <th>Accion</th>
                    </tr>
                    <%
                        for (User usuario : listaUsuarios) {
                    %>
                    <tr>
                        <td><%=usuario.getNombre()%></td>
                        <td><%=usuario.getApellido()%></td>
                        <td><%=usuario.getEmail()%></td>
                        <td><%=usuario.getLoginName()%></td>
                        <td>
                            <form action="ServletCliente" method="GET">
                                <input type="hidden" name="redireccion" value="listarCliente.jsp">
                                <input type="hidden" name="id" value="<%=usuario.getId()%>">
                                <input type="hidden" name="accion" value="editar">
                                <input type="submit" value="Editar"/>
                            </form>
                            <form action="ServletCliente" method="GET">                                
                                <input type="hidden" name="redireccion" value="listarCliente.jsp">
                                <input type="hidden" name="id" value="<%=usuario.getId()%>">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="submit" value="Eliminar"/>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
        </div>
    </body>
</html>
