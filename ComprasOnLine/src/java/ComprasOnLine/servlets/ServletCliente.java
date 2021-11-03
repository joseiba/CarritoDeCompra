/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprasOnLine.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ComprasOnLine.modelosServicios.ModeloCliente;
import ComprasOnLine.modelosClases.User;
import org.json.JSONException;

/**
 *
 * @author Joseiba
 */
@WebServlet(name = "ServletCliente", urlPatterns = {"/ServletCliente"})
public class ServletCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        String accion = request.getParameter("accion");
        String redireccion = request.getParameter("redireccion");
        User usuarioLogin = new User();
        HttpSession sesion  = request.getSession(true);
        ModeloCliente cliente = new ModeloCliente();
        System.out.println("accion:" + accion);

        if (accion == null) {
            System.out.println("muestra el listado");
            try {
                ArrayList<User> listaUsuarios = cliente.listar();
                System.out.println(listaUsuarios.toString());
                request.setAttribute("listaUsuarios", listaUsuarios);
                RequestDispatcher rd = request.getRequestDispatcher(redireccion);
                rd.forward(request, response);

            } catch (JSONException ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
        else if (accion.equals("agregar")) {
            try {

                RequestDispatcher rd = request.getRequestDispatcher("agregarCliente.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (accion.equals("editar")) {
            try {
                User usuario = cliente.listar(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("usuario", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("modificarCliente.jsp");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else if (accion.equals("login")) {
            System.out.println("se envia el logiName y password");
            try {
                usuarioLogin = cliente.login(request.getParameter("loginName"),request.getParameter("password"));
                System.out.println("Usuario loggeado: " + usuarioLogin.getNombre() + usuarioLogin.getApellido());
                System.out.println("la sesion esta: " + sesion);
                sesion.setAttribute("usuarioLogin", usuarioLogin);
                request.setAttribute("usuarioLogin", usuarioLogin);
                RequestDispatcher rd = request.getRequestDispatcher("/ServletProducto");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (accion.equals("logout")) {
                usuarioLogin = null;
                sesion.setAttribute("usuarioLogin", usuarioLogin);
                request.setAttribute("usuarioLogin", usuarioLogin);
                RequestDispatcher rd = request.getRequestDispatcher("/ServletProducto");
                rd.forward(request, response);
        }
        else if (accion.equals("eliminar")) {
            System.out.println("elimina y muestra la lista de nuevo");
            cliente.eliminar(Integer.parseInt(request.getParameter("id")));
            //verificar que se elimino
            response.sendRedirect("/ComprasOnLine/ServletCliente?redireccion=" + redireccion);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        ModeloCliente cliente = new ModeloCliente();
        String accion = request.getParameter("accion");
        String redireccion = request.getParameter("redireccion");        
        String nombre = request.getParameter("nombre_cliente");
        String apellido = request.getParameter("apellido_cliente");
        String correo = request.getParameter("correo_cliente");
        String nickname = request.getParameter("nick_cliente");
        String password = request.getParameter("password_cliente");
        int tipo = Integer.parseInt(request.getParameter("tipo_cliente"));            

        User usuario = new User(0, nombre, apellido, correo, nickname, password, tipo);
        
        
        System.out.println("accion:" + accion);
        if (accion.equals("grabar")) {
            System.out.println("se graba u nuevo registro");           
            try {
                cliente.agregar(usuario);
            } catch (JSONException ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/ComprasOnLine/ServletCliente?redireccion=" + redireccion);

            
            
        } else if(accion.equals("actualizar")) {//se actualiza
            System.out.println("se actualiza u nuevo registro");
            int id = Integer.parseInt(request.getParameter("id_cliente"));            
            usuario.setId(id);
            
            try {
                cliente.actualizar(usuario);
            } catch (Exception ex) {
                Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect("/ComprasOnLine/ServletCliente?redireccion=" + redireccion);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
