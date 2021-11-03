/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprasOnLine.servlets;

import java.io.IOException;
import java.net.MalformedURLException;
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
import ComprasOnLine.modelosClases.Producto;
import ComprasOnLine.modelosClases.TransaccionCab;
import ComprasOnLine.modelosClases.TransaccionDet;
import ComprasOnLine.modelosServicios.ModeloProducto;
import ComprasOnLine.modelosServicios.ModeloTransaccion;
import org.json.JSONException;

/**
 *
 * @author Joseiba
 */
@WebServlet(name = "ServletCarrito", urlPatterns = {"/ServletCarrito"})
public class ServletCarrito extends HttpServlet {

    ArrayList<Producto> listaCarrito = new ArrayList<Producto>();
    ArrayList<TransaccionDet> listaTransaccionDet = new ArrayList<TransaccionDet>();
    int total = 0;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MalformedURLException {
        ModeloProducto modeloProducto = new ModeloProducto();
        String accion = request.getParameter("accion");

        if (accion.equals("agregarCarrito")) {
            try {
                Producto producto = modeloProducto.listar(Integer.parseInt(request.getParameter("id")));
                int cantidadCompra = Integer.parseInt(request.getParameter("cantidadCompra"));
                TransaccionDet transaccionDet = new TransaccionDet(0, producto.getId(), cantidadCompra, producto.getPrecio_unit(), cantidadCompra * producto.getPrecio_unit(), 0);
                listaCarrito.add(producto);
                listaTransaccionDet.add(transaccionDet);
                total = total + transaccionDet.getSub_total();
                request.setAttribute("productoCarrito", producto);               
                request.setAttribute("listaTransaccionDet", listaTransaccionDet);
                request.setAttribute("listaCarrito", listaCarrito);
                request.setAttribute("longitudCarrito", listaCarrito.size());
                RequestDispatcher rd = request.getRequestDispatcher("/ServletProducto");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR:" + ex.getMessage());
            }
        } else if (accion.equals("verCarrito")) {
            System.out.println(listaCarrito);
            request.setAttribute("listaTransaccionDet", listaTransaccionDet);
            request.setAttribute("listaCarrito", listaCarrito);
            request.setAttribute("longitudCarrito", listaCarrito.size());
            RequestDispatcher rd = request.getRequestDispatcher("listaCarrito.jsp");
            request.setAttribute("totalCompra", total);
            rd.forward(request, response);
        } else if (accion.equals("sacarDelCarrito")) {
            System.out.println("sacar del carrito");
            try {
                //Producto producto = modeloProducto.listar(Integer.parseInt(request.getParameter("id_producto")));
                int indice = Integer.parseInt(request.getParameter("indice"));
                listaCarrito.remove(indice);
                total = total - listaTransaccionDet.get(indice).getSub_total();
                listaTransaccionDet.remove(indice);
                System.out.println(listaCarrito);
                request.setAttribute("totalCompra", total);
                request.setAttribute("listaCarrito", listaCarrito);
                request.setAttribute("listaTransaccionDet", listaTransaccionDet);
                request.setAttribute("longitudCarrito", listaCarrito.size());
                RequestDispatcher rd = request.getRequestDispatcher("listaCarrito.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (accion.equals("confirmarCompra") || accion.equals("login")  ) {
            System.out.println("confirma compra");
            request.setAttribute("totalCompra", total);

            try {
                HttpSession sesion  = request.getSession();
                if(sesion.getAttribute("usuarioLogin") == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("confirmarCompra.jsp");
                    rd.forward(request, response);
                }
               
                
            } catch (Exception ex) {
                Logger.getLogger(ServletCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        TransaccionCab transaccionCab = new TransaccionCab(0,
                request.getParameter("fecha"),
                Integer.parseInt(request.getParameter("id_cliente")),
                Integer.parseInt(request.getParameter("total")),
                request.getParameter("direccion_envio"),
                Integer.parseInt(request.getParameter("id_medio_pago")),
                Integer.parseInt(request.getParameter("nro_tarjeta")),
                "P");
        System.out.println(transaccionCab);

        String accion = request.getParameter("accion");
        ModeloTransaccion modeloTransaccion = new ModeloTransaccion();
        if (accion.equals("comprar")) {
            try {
                modeloTransaccion.agregar(transaccionCab);
                modeloTransaccion.agregarDet(listaTransaccionDet);
                listaCarrito = new ArrayList<Producto>();
                listaTransaccionDet = new ArrayList<TransaccionDet>();
                total = 0;
                request.setAttribute("totalCompra", total);
                request.setAttribute("listaCarrito", listaCarrito);
                request.setAttribute("listaTransaccionDet", listaTransaccionDet);                
                RequestDispatcher rd = request.getRequestDispatcher("/ServletProducto");
                rd.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ServletProducto.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR:" + ex.getMessage());
            }
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
