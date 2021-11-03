<%@page import="ComprasOnLine.modelosClases.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ComprasOnLine.modelosClases.Producto"%>
<%@page import="ComprasOnLine.modelosClases.TransaccionDet"%>
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
            int longitudCarrito = 0;
            int indice = 0;
            int indiceProducto = 0;
            int totalCompra = 0;
            ArrayList<Producto> listaCarrito = new ArrayList<Producto>();         
            ArrayList<TransaccionDet> listaTransaccionDet = new ArrayList<TransaccionDet>();
            
            totalCompra = (int)request.getAttribute("totalCompra");
            listaCarrito = (ArrayList<Producto>) request.getAttribute("listaCarrito");
            listaTransaccionDet = (ArrayList<TransaccionDet>) request.getAttribute("listaTransaccionDet");
            longitudCarrito = (int)request.getAttribute("longitudCarrito");
            System.out.println(listaTransaccionDet);
                        
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
            int longitud = (Integer) request.getAttribute("longitud");
            
        %>
        
        <div style="width: 1200px; margin-left: auto; margin-right: auto;">
            <h1>Carrito de Compras</h1>
                     
            <table cellpadding="10" border='1'>
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Precio Unitario</th>
                    <th>Sub Total</th>
                    <th>Cantidad</th>
                    <th>Accion</th>
                </tr>
                <%
                    for (TransaccionDet transaccion : listaTransaccionDet) {
                %>
                <tr>
                    <td><%=transaccion.getId_producto()%></td>
                    <td><%=listaCarrito.get(indiceProducto++).getDescripcion()%></td>
                    <td><%=transaccion.getPrecio()%></td>
                    <td><%=transaccion.getSub_total()%></td>
                    <td><input value="<%=transaccion.getCantidad()%>" type="number" min="1" max="<%=transaccion.getCantidad()%>"></td>
                    <td>
                        <form action="ServletCarrito" method="GET">
                            <input type="hidden" name="indice" value="<%=indice++%>">
                            <input type="hidden" name="accion" value="sacarDelCarrito">
                            <input type="submit" value="Eliminar"/>
                        </form>
                    </td>

                </tr>
                <%}%>
            </table>
            <p>Productos Carrito: <%=longitudCarrito%> </p>
            <p>Total: Gs. <%=totalCompra%> </p>
            <form action="ServletCarrito" method="GET">
                  <input type="hidden" name="accion" value="confirmarCompra">
                  <input type="submit" value="Confirmar Compra"/>
              </form>            
        </div>
            
       
    </body>
</html>
