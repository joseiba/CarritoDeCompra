<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
 <body>
        <form action="ServletHome" action="GET" name="formHome">
            <input type="hidden" name="redireccion" value="home.jsp">

        </form>
     
        <script>
            window.onload = function () {
                // Una vez cargada la página, el formulario se enviara automáticamente.
                document.forms["formHome"].submit();
            }
        </script>

    </body>
</html>
