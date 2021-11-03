/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parprdmcs.domain.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import parprdmcs.domain.model.entity.Producto;
import parprdmcs.domain.repository.JdbcProductoRepository;
import parprdmcs.domain.service.ProductoServiceImpl;

/**
 *
 * @author Joseiba
 */
@Path("/productoapi")
public class ProductoRestService {

    private final ProductoServiceImpl productoService = new ProductoServiceImpl(new JdbcProductoRepository());

    @GET
    @Path("/productos")
    @Produces("application/json")
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos = (ArrayList) productoService.getAll();
        return productos;
    }

    @GET
    @Path("/productos/{id}")
    @Produces("application/json")
    public Producto getProducto(@PathParam("id") Integer id) {
        Producto entity = null;
        try {
            entity = (Producto) productoService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    @GET
    @Path("/productos-des/{descripcion}")
    @Produces("application/json")
    public ArrayList<Producto> getProductoPorNombre(@PathParam("descripcion") String descripcion) {
         ArrayList<Producto> productos = null;
        try {
            productos = (ArrayList) productoService.findByDescripcion(descripcion);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    @GET
    @Path("/productos-cat/{id}")
    @Produces("application/json")
    public ArrayList<Producto> getProductoPorCategoria(@PathParam("id") Integer id) {
         ArrayList<Producto> productos = null;
        try {
            productos = (ArrayList) productoService.findByCategoria(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    @POST
    @Path("/productos")
    @Consumes("application/json")
    @Produces("application/json")
    public Producto addProducto(Producto entity) {
        try {
            productoService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/productos")
    @Consumes("application/json")
    public void updateProducto(Producto entity) {
        try {
            productoService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/productos/{id}")
    public void removeProducto(@PathParam("id") Integer id) {
        try {
            productoService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
