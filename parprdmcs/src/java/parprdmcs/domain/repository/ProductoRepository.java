package parprdmcs.domain.repository;

import java.util.Collection;
import parprdmcs.domain.model.entity.Producto;

/**
 *
 * @author Joseiba
 * @param <Producto>
 * @param <Integer>
 */
public interface ProductoRepository<Producto, Integer> extends Repository<Producto, Integer> {

    /**
     * Se busca si ya existe productos con la misma descripcion
     * @param descripcion
     * @return
     */
    boolean containsDescripcion(String descripcion);

    /**
     * Se busca los producto por descripcion
     * @param descripcion
     * @return
     * @throws Exception
     */
    public Collection<Producto> findByDescripcion(String descripcion) throws Exception;   
    /**
     * Se busca los producto por categoria
     * @param id
     * @return
     * @throws Exception
     */
    public Collection<Producto> findByCategoria(Integer id) throws Exception;
}
