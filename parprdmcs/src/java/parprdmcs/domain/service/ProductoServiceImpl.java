package parprdmcs.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import parprdmcs.domain.model.entity.Entity;
import parprdmcs.domain.model.entity.Producto;
import parprdmcs.domain.repository.ProductoRepository;

/**
 *
 * @author Joseiba
 */
public class ProductoServiceImpl extends BaseService<Producto, Integer>
        implements ProductoService {

    private ProductoRepository<Producto, Integer> productoRepository;

    /**
     *
     * @param productoRepository
     */
    //@Autowired
    public ProductoServiceImpl(ProductoRepository<Producto, Integer> productoRepository) {
        super(productoRepository);
        this.productoRepository = productoRepository;
    }

    @Override
    public void add(Producto producto) throws Exception {
        if (productoRepository.containsDescripcion(producto.getDescripcion())){
            throw new Exception(String.format("Ya existe un producto con la descripcion %s ", producto.getDescripcion()));
        }
        
        if (producto.getDescripcion()== null || "".equals(producto.getDescripcion())) {
            throw new Exception("La descripcion del producto no puede ser nula o cadena vacia.");
        }
               
        super.add(producto);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Producto> findByDescripcion(String descripcion) throws Exception {
        return productoRepository.findByDescripcion(descripcion);
    }
    
    @Override
    public Collection<Producto> findByCategoria(Integer id) throws Exception {
        return productoRepository.findByCategoria(id);
    }

    /**
     *
     * @param producto
     * @throws Exception
     */
    @Override
    public void update(Producto producto) throws Exception {
        productoRepository.update(producto);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        productoRepository.remove(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(Integer id) throws Exception {
        return productoRepository.get(id);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Producto> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
