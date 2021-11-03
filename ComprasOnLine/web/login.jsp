<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="ServletCliente">
            Usuario
            <input type="text" name="loginName"/><br>
            Password
            <input type="password" name="password"/><br>

            <input type="hidden" name="accion" value="login"/><br>
            <input type="hidden" name="redireccion" value="home.jsp"/><br>
            <button type="submit" >Iniciar Secion</button>
            <button type="reset" >Cancelar</button>
        </form>
</html>
