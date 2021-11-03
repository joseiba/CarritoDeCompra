<%@page import="java.util.ArrayList"%>
<%@page import="ComprasOnLine.modelosClases.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias"); %>        
        <form method="POST" action="ServletProducto">
            Descripcion:
            <input type="text" name="producto_descripcion"/><br>
            Precio Unitario:
            <input type="text" name="producto_precio"/><br>
            Categoria:
            <select name="producto_categoria">
                <% for (Categoria categoria : listaCategorias) {%>
                    <option value="<%=categoria.getId_categoria()%>"><%=categoria.getDescripcion()%></option>>  
                <%}%>                
            </select><br>
            Stock:
            <input type="text" name="producto_cantidad"/><br>
            <input type="hidden" name="redireccion" value="listarProducto.jsp">
            <input type="hidden" name="accion" value="grabar"/><br>
            <button type="submit" >Agregar</button>

        </form>

</html>
