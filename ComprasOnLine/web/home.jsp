<%@page import="ComprasOnLine.modelosClases.User"%>
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

        <%            
            HttpSession sesion  = request.getSession();
            int longitudCarrito = 0;
            User usuarioLoggado = null;
            Producto productoCarrito = new Producto();
            ArrayList<Producto> listaCarrito = new ArrayList<Producto>();         
            ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getAttribute("listaProductos");
                 
            if(request.getAttribute("productoCarrito")!=null){
                listaCarrito = (ArrayList<Producto>) request.getAttribute("listaCarrito");
                productoCarrito = (Producto) request.getAttribute("productoCarrito");
                longitudCarrito = (int)request.getAttribute("longitudCarrito");
            }
                        
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
            int longitud = (Integer) request.getAttribute("longitud");
            
        %>
        
        <%
            usuarioLoggado = (User)sesion.getAttribute("usuarioLogin");
            
        %>
        <%if(usuarioLoggado != null){%>
        
            <p><%=usuarioLoggado.getNombre()%></p>
            <div>
               <form action="ServletCliente" method="GET">
                    <input type="hidden" name="accion" value="logout">
                    <button type="submit" name="redireccion" value="home.jsp">Cerrar Sesion</button>
                </form>            
            <%if(usuarioLoggado.getTipoCliente()== 0){%>

                <div>
                   <form action="ServletHome" method="GET">
                        <input type="hidden">
                        <button type="submit" name="redireccion" value="listarProducto.jsp" id="btnBuscar">ABM Productos</button>
                        <button type="submit" name="redireccion" value="listarCliente.jsp" id="btnBuscar">ABM Clientes</button>
                    </form>            
                </div>
            <%}%>
            </div>
        <%}else{%>
            <div>
               <form action="ServletHome" method="GET">
                    <button type="submit" name="redireccion" value="login.jsp">Iniciar Sesion</button>
                </form>            
            </div>
        <%}%>
        
        
        <div style="width: 1200px; margin-left: auto; margin-right: auto;">
            <h1>Catalogo de Productos</h1>
            
            <form action="ServletHome" method="GET">
                <input type="hidden" name="redireccion" value="home.jsp">
                <input type="hidden" name="accion" value="buscar">
                <input type="text" name="producto_descripcion_buscar" id="producto_descripcion_buscar" required>
                <button type="submit" id="btnBuscar">Buscar</button>
            </form>

            <form method="GET" action="ServletHome" id="filtro">                
                <input type="hidden" name="redireccion" value="home.jsp">
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
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Precio Unitario</th>
                    <th>Stock</th>
                    <th>Categoria</th>
                    <th>AgregarCarrito</th>
                </tr>
                <%
                    for (Producto producto : listaProductos) {
                %>
                <tr>
                    <td><%=producto.getId()%></td>
                    <td><%=producto.getDescripcion()%></td>
                    <td><%=producto.getPrecio_unit()%></td>
                    <td><%=producto.getCantidad()%></td>
                    <td><%=producto.getDescripcion_categoria()%></td>
                    <td>
                        <form action="ServletCarrito" method="GET">
                            <input type="number" name="cantidadCompra" min="1" max="<%=producto.getCantidad()%>">
                            <input type="hidden" name="redireccion" value="home.jsp">
                            <input type="hidden" name="id" value="<%=producto.getId()%>">
                            <input type="hidden" name="accion" value="agregarCarrito">
                            <input type="submit" value="AgregarCarrito"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
            <p>Total de Productos : <%=longitud%> </p>
            <p>Productos Carrito: <%=longitudCarrito%> </p>
            <form action="ServletCarrito" method="GET">
                <input type="hidden" name="accion" value="verCarrito">
                <input type="submit" value="Ver Carrito"/>
            </form>
        </div>
       
    </body>
</html>
