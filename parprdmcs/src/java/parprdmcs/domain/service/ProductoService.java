package parprdmcs.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import parprdmcs.domain.model.entity.Entity;
import parprdmcs.domain.model.entity.Producto;

/**
 *
 * @author Joseiba
 */
public interface ProductoService {

    /**
     *
     * @param producto
     * @throws Exception
     */
    public void add(Producto producto) throws Exception;

    /**
     *
     * @param producto
     * @throws Exception
     */
    public void update(Producto producto) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;

    /**
     *
     * @param descripcion
     * @return
     * @throws Exception
     */
    public Collection<Producto> findByDescripcion(String descripcion) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Collection<Producto> findByCategoria(Integer id) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Producto> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
