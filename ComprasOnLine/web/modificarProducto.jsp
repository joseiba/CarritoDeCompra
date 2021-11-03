<%@page import="java.util.ArrayList"%>
<%@page import="ComprasOnLine.modelosClases.Categoria"%>
<%@page import="ComprasOnLine.modelosClases.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%  Producto producto = (Producto) request.getAttribute("producto");
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
        %>
        <form method="POST" action="ServletProducto">
            id:
            <input type="hidden" value ="<%=producto.getId()%>" name="id_producto"/><br>
            Descripcion:
            <input type="text" value ="<%=producto.getDescripcion()%>" name="producto_descripcion"/><br>
            Precio Unitario:
            <input type="text" value ="<%=producto.getPrecio_unit()%>" name="producto_precio"/><br>
            Cantidad:
            <input type="text" value ="<%=producto.getCantidad()%>" name="producto_cantidad"/><br>
            Categoria:
            <select name="producto_categoria">
                <% for (Categoria categoria : listaCategorias) { %>
                    <% if (categoria.getId_categoria() == producto.getId_categoria()) {%>
                        <option value="<%=categoria.getId_categoria()%>" selected><%=categoria.getDescripcion()%></option>>  
                    <%} else {%>
                        <option value="<%=categoria.getId_categoria()%>"><%=categoria.getDescripcion()%></option>>  
                    <%}%>                
                <%}%>                
            </select><br>            
            <input type="hidden" name="redireccion" value="listarProducto.jsp">
            <input type="hidden" value ="actualizar" name="accion"/><br>
            <button type="submit" >Guardar Cambios</button>

        </form>
    </body>
</html>
