<%@page import="ComprasOnLine.modelosClases.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ComprasOnLine.modelosClases.Producto"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" name="redireccion" value="listarProducto.jsp">
        <%
            ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getAttribute("listaProductos");
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
            int longitud = (Integer) request.getAttribute("longitud");
        %>
        <div style="width: 1200px; margin-left: auto; margin-right: auto;">
            <h1>Lista de Productos</h1>
            <form action="ServletProducto" method="GET">
                <input type="hidden" name="redireccion" value="listarProducto.jsp">
                <input type="hidden" name="accion" value="agregar">
                <input type="submit" value="Agregar"/>
            </form>
            
            <form action="ServletProducto" method="GET">
                <input type="hidden" name="redireccion" value="listarProducto.jsp">            
                <input type="hidden" name="accion" value="buscar">
                <input type="text" name="producto_descripcion_buscar" id="producto_descripcion_buscar" required>
                <button type="submit" id="btnBuscar">Buscar</button>
            </form>

            <form method="GET" action="ServletProducto" id="filtro">       
                <input type="hidden" name="redireccion" value="listarProducto.jsp">
                <input type="hidden" name="accion" value="filtrar">
                <select name="producto_categoria_fil" id="producto_categoria">
                    <% for (Categoria categoria : listaCategorias) {%>
                        <option value="<%=categoria.getId_categoria()%>"><%=categoria.getDescripcion()%></option>>  
                    <%}%>                
                </select><br>
                 <button type="submit">Filtrar</button>
            </form>
            
            <table cellpadding="10" border='1'>
                <tr>
                    <th>Descripcion</th>
                    <th>Precio Unitario</th>
                    <th>Cantidad</th>
                    <th>Categoria</th>
                    <th>Accion</th>
                </tr>
                <%
                    for (Producto producto : listaProductos) {
                %>
                <tr>
                    <td><%=producto.getDescripcion()%></td>
                    <td><%=producto.getPrecio_unit()%></td>
                    <td><%=producto.getCantidad()%></td>
                    <td><%=producto.getDescripcion_categoria()%></td>
                    <td>
                        <form action="ServletProducto" method="GET">
                            <input type="hidden" name="redireccion" value="listarProducto.jsp">
                            <input type="hidden" name="id" value="<%=producto.getId()%>">
                            <input type="hidden" name="accion" value="editar">
                            <input type="submit" value="Editar"/>
                        </form>
                        <form action="ServletProducto" method="GET">
                            <input type="hidden" name="redireccion" value="listarProducto.jsp">
                            <input type="hidden" name="id" value="<%=producto.getId()%>">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="submit" value="Eliminar"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
            <p>Total de Productos : <%=longitud%> </p>
        </div>
        <script>
            
            document.getElementById("btnBuscar").addEventListener("click", function(){                
                var descripcion = document.getElementById("producto_descripcion_buscar").value;
                
                if (descripcion === " ") {
                    console.log("LA DESCRIPCION NO PUEDE ESTAR VACIA");
                }
                
            });

            function filtrar(){
                document.getElementById("filtro").submit();                                              
            }
            
            
        </script>
        
    </body>
</html>
