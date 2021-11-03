<%@page import="ComprasOnLine.modelosClases.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            int totalCompra = 0;
            HttpSession sesion  = request.getSession();
            User usuarioLoggado = null;
            usuarioLoggado = (User)sesion.getAttribute("usuarioLogin");
            totalCompra = (int)request.getAttribute("totalCompra");
            
        %>
       <form method="POST" action="ServletCarrito">
            Fecha:
            <input id="datePicker" type="date" name="fecha" readonly/><br>
            Cliente
            <input type="text" name="id_cliente" value="<%=usuarioLoggado.getId()%>" readonly/><br>
            Total:
            <input type="text" name="total" value="<%=totalCompra%>" readonly/><br>
            direccion envio:
            <input type="text" name="direccion_envio" required/><br>
            Medio Pago:
            <select name="id_medio_pago">
                <option value="1">Tarjeta</option>
                <option value="2">Paypal</option>
                <option value="3">Bitcoin</option>
            </select>
            <br>
            Nro Tarjeta:
            <input type="text" name="nro_tarjeta"/><br>
            <input type="hidden" name="estado" value="p"/><br>
            <input type="hidden" name="redireccion" value="home.jsp">
            <input type="hidden" name="accion" value="comprar"/><br>
            <button type="submit" >Comprar</button>
        </form>
    </body>
    <script>
        document.getElementById('datePicker').valueAsDate = new Date();
    </script>
</html>
