package parprdmcs.domain.model.entity;

/**
 *
 * @author Joseiba
 */
public class Producto extends BaseEntity<Integer> {

    private int id_categoria;
    private int precio_unit;
    private int cantidad;

    public Producto() {
        super(0, "");
    }

    public Producto(int id_categoria, int precio_unit, int cantidad, Integer id, String descripcion) {
        super(id, descripcion);
        this.id_categoria = id_categoria;
        this.precio_unit = precio_unit;
        this.cantidad = cantidad;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(int precio_unit) {
        this.precio_unit = precio_unit;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  super.toString() + "Producto{" + "id_categoria=" + id_categoria + ", precio_unit=" + precio_unit + ", cantidad=" + cantidad + '}';
    }
    
    
}
