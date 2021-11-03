/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parprdmcs.domain.categoria;


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

/**
 *
 * @author Joseiba
 */
@Path("/categoriasapi")
public class CategoriaRestService {
    CategoriaRepo repo = new CategoriaRepo();
    
    @GET
    @Path("/categorias")
    @Produces("application/json")
    public ArrayList<Categoria> getCategorias() {
        
        ArrayList<Categoria> categorias = (ArrayList) repo.ListarCategorias();
        return categorias;
    }

    @GET
    @Path("/categorias/{id}")
    @Produces("application/json")
    public Categoria getCategoria(@PathParam("id") Integer id) {
       Categoria entity = null;
        try {
            Categoria cate = new Categoria();
            cate.setId_categoria(id);
            entity = (Categoria) repo.getCategoria(id);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @POST
    @Path("/categorias")
    @Consumes("application/json")
    @Produces("application/json")
    public Categoria addCategoria(Categoria entity) {
        repo.insertarCategoria(entity);
        return entity;
    }

    @PUT
    @Path("/categorias")
    @Consumes("application/json")
    public void updateCategoria(Categoria entity) {
        try {
            repo.insertarCategoria(entity);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/categorias/{id}")
    public void removeCategoria(@PathParam("id") Integer id) {
       try {
           Categoria cate = new Categoria();
           cate.setId_categoria(id);
           repo.eliminarCategoria(cate);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
