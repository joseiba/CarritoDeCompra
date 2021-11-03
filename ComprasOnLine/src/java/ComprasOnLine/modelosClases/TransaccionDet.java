package ComprasOnLine.modelosClases;

/**
 *
 * @author Joseiba
 */
public class TransaccionDet  {
    
    private int item;
    private int id_producto;
    private int cantidad;
    private int precio;
    private int sub_total;

    public TransaccionDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }

    
    public TransaccionDet(Integer id) {
    
    }

    public TransaccionDet(int item, int id_producto, int cantidad, int precio, int sub_total, Integer id) {
        this.item = item;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.sub_total = sub_total;
    }

    @Override
    public String toString() {
        return "TransaccionDet{" + "item=" + item 
                + ", id_producto=" + id_producto 
                + ", cantidad=" + cantidad 
                + ", precio=" + precio 
                + ", sub_total=" + sub_total + '}';
    }


  
}
