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
import ComprasOnLine.modelosClases.Categoria;
import ComprasOnLine.modelosClases.Producto;
import ComprasOnLine.modelosServicios.ModeloCategoria;
import ComprasOnLine.modelosServicios.ModeloProducto;
import org.json.JSONException;

/**
 *
 * @author Joseiba
 */
@WebServlet(name = "ServletProducto", urlPatterns = {"/ServletProducto"})
public class ServletProducto extends HttpServlet {

        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();   
    
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
        String accion = request.getParameter("accion");
        String redireccion = request.getParameter("redireccion");
        String descripcion = request.getParameter("producto_descripcion_buscar");
        System.out.println(descripcion);
        ModeloProducto modeloProducto = new ModeloProducto();
        ModeloCategoria modeloCategoria = new ModeloCategoria();
        System.out.println("accion:" + accion);

        
        if (accion == null || accion.equals("agregarCarrito") || accion.equals("login") || accion.equals("logout") || accion.equals("comprar")) {
            System.out.println("muestra el listado");
            try {
                System.out.println("accion listar: " + accion);
                listaProductos = modeloProducto.listar();
                listaCategorias = modeloCategoria.listar();

                System.out.println(listaProductos.toString());
                request.setAttribute("listaCategorias", listaCategorias);
                request.setAttribute("listaProductos", listaProductos);
                request.setAttribute("longitud", listaProductos.size());
                RequestDispatcher rd = request.getRequestDispatcher(redireccion);
                rd.forward(request, response);

                if (accion.equals("comprar") ) {
                    
                }

            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (accion.equals("buscar")) {
            try {
                listaProductos = modeloProducto.buscar(descripcion);
                listaCategorias = modeloCategoria.listar();

                request.setAttribute("listaCategorias", listaCategorias);   
                request.setAttribute("listaProductos", listaProductos);
                request.setAttribute("longitud", listaProductos.size());
                RequestDispatcher rd = request.getRequestDispatcher(redireccion);
                rd.forward(request, response);

            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals("filtrar")) {
            int id_categoria = Integer.parseInt(request.getParameter("producto_categoria_fil"));
            System.out.println("se filtra");
            System.out.println(id_categoria);
            try {
                listaProductos = modeloProducto.filtrarProducto(id_categoria);
                listaCategorias = modeloCategoria.listar();

                request.setAttribute("listaCategorias", listaCategorias);                
                System.out.println(listaProductos);
                request.setAttribute("listaCategorias", listaCategorias);
                request.setAttribute("listaProductos", listaProductos);
                request.setAttribute("longitud", listaProductos.size());
                RequestDispatcher rd = request.getRequestDispatcher(redireccion);
                rd.forward(request, response);

            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (accion.equals("agregar")) {
            System.out.println("se tiene que mostrar el formulario de agregar");
            try {
                listaCategorias = modeloCategoria.listar();

                request.setAttribute("listaCategorias", listaCategorias);
                RequestDispatcher rd = request.getRequestDispatcher("agregarProducto.jsp");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals("editar")) {
            System.out.println("se tiene que mostrar el formulario de editar");
            try {
                Producto producto = modeloProducto.listar(Integer.parseInt(request.getParameter("id")));
                listaCategorias = modeloCategoria.listar();
                request.setAttribute("listaCategorias", listaCategorias);
                request.setAttribute("producto", producto);
                RequestDispatcher rd = request.getRequestDispatcher("modificarProducto.jsp");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals("eliminar")) {
            System.out.println("elimina y muestra la lista de nuevo");
            modeloProducto.eliminar(Integer.parseInt(request.getParameter("id")));
            //verificar que se elimino
            response.sendRedirect("/ComprasOnLine/ServletProducto?redireccion="+redireccion);

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
        System.out.println("entro al dopost");
        ModeloProducto modeloProducto = new ModeloProducto();
        String accion = request.getParameter("accion"); 
        String redireccion = request.getParameter("redireccion");
        if (accion.equals("comprar")) {//se va al home
            System.out.println("se va al home");
            request.setAttribute("listaCategorias", listaCategorias);   
            request.setAttribute("listaProductos", listaProductos);
            request.setAttribute("longitud", listaProductos.size());
            RequestDispatcher rd = request.getRequestDispatcher(redireccion);
            rd.forward(request, response);

        }
        String descripcion = request.getParameter("producto_descripcion");
        int precio = Integer.parseInt(request.getParameter("producto_precio"));
        int categoria = Integer.parseInt(request.getParameter("producto_categoria"));
        int stock = Integer.parseInt(request.getParameter("producto_cantidad"));

        Producto producto = new Producto(0, descripcion, categoria, precio, stock);

        System.out.println("accion:" + accion);
        if (accion.equals("grabar")) {
            System.out.println("se graba u nuevo registro");
            try {
                modeloProducto.agregar(producto);
            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/ComprasOnLine/ServletProducto?redireccion="+redireccion);

        } 
        else if (accion.equals("actualizar")) {//se actualiza
            System.out.println("se actualiza u nuevo registro");
            int id = Integer.parseInt(request.getParameter("id_producto"));
            producto.setId(id);

            try {
                modeloProducto.actualizar(producto);
            } catch (Exception ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.sendRedirect("/ComprasOnLine/ServletProducto?redireccion="+redireccion);

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
