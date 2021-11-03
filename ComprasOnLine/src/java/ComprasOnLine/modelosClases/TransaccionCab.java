package ComprasOnLine.modelosClases;

/**
 *
 * @author Joseiba
 */
public class TransaccionCab  {
    private int id;
    private String fecha;
    private int id_cliente;
    private int total;
    private String direccion_envio;
    private int id_medio_pago;
    private int nro_tarjeta;
    private String estado;

    public TransaccionCab() {
    }

    public TransaccionCab(Integer id,String fecha, int id_cliente, int total, String direccion_envio, int id_medio_pago, int nro_tarjeta, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.total = total;
        this.direccion_envio = direccion_envio;
        this.id_medio_pago = id_medio_pago;
        this.nro_tarjeta = nro_tarjeta;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }

    public int getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setNro_tarjeta(int nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Transaccion_cab{" + "fecha=" + fecha + ", id_cliente=" + id_cliente + ", total=" + total + ", direccion_envio=" + direccion_envio + ", id_medio_pago=" + id_medio_pago + ", nro_tarjeta=" + nro_tarjeta + ", estado=" + estado + '}';
    }    
}
