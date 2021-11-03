package ComprasOnLine.modelosClases;

/**
 *
 * @author Joseiba
 */
public class Producto  {

    private int id;
    private String descripcion;
    private int id_categoria;
    private int precio_unit;
    private int cantidad;
    private String descripcion_categoria;

    public Producto() {
 
    }

    public Producto(int id, String descripcion, int id_categoria, int precio_unit, int cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.precio_unit = precio_unit;
        this.cantidad = cantidad;
    }
    
       public Producto(int id, String descripcion, int id_categoria, int precio_unit, int cantidad, String descripcion_categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.precio_unit = precio_unit;
        this.cantidad = cantidad;
        this.descripcion_categoria = descripcion_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }
    
    

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", descripcion=" + descripcion + ", id_categoria=" + id_categoria + ", precio_unit=" + precio_unit + ", cantidad=" + cantidad + '}';
    } 
}
