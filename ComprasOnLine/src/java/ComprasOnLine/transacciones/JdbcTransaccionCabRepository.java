package ComprasOnLine.transacciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import ComprasOnLine.modelosClases.TransaccionCab;
import ComprasOnLine.modelosClases.TransaccionDet;

/**
 *
 * @author Joseiba
 */
public class JdbcTransaccionCabRepository {

    
    public boolean containsDescripcion(String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

    
    public Collection<TransaccionCab> findByDescripcion(String descripcion) throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void add(TransaccionCab entity ) {
        System.out.println("entro aca");
        Connection c = null;
        PreparedStatement pstmt = null;

        try {            
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO transacciones_cab (fecha, id_cliente, total, direccion_envio, id_medio_pago, nro_tarjeta, estado) values (to_date(?,'YYYYMMDD'), ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, entity.getFecha());
            pstmt.setInt(2, entity.getId_cliente());
            pstmt.setInt(3, entity.getTotal());
            pstmt.setString(4, entity.getDireccion_envio());
            pstmt.setInt(5, entity.getId_medio_pago());
            pstmt.setInt(6, entity.getNro_tarjeta());
            pstmt.setString(7, entity.getEstado());

            pstmt.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                DBUtils.closeConnection(c);
                }
            } catch (SQLException ex) {
               // Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        //se obtiene el id de la ultima transaccion
        
        //se inserta en la tabla transaccion detalle
        
    }

    
    public void remove(Integer id) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("DELETE FROM transacciones_cab WHERE id_transaccion = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public void update(TransaccionCab entity) {
        System.out.println("estoy aca");
        System.out.println(entity.toString());
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE transacciones_cab SET fecha = ?,"
                    + "id_cliente = ?, "
                    + "total = ?, "
                    + "direccion_envio = ? "
                    + "id_medio_pago = ? "
                    + "nro_tarjeta = ? "
                    + "estado = ? "
                    + "WHERE id_transaccion = ?");

            pstmt.setString(1, entity.getFecha());
            pstmt.setInt(2, entity.getId_cliente());
            pstmt.setInt(3, entity.getTotal());
            pstmt.setString(4, entity.getDireccion_envio());
            pstmt.setInt(5, entity.getId_medio_pago());
            pstmt.setInt(6, entity.getNro_tarjeta());
            pstmt.setString(7, entity.getEstado());
        
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error: " + ex.getMessage());
            }
        }
    }

    
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public TransaccionCab get(Integer id) {
        TransaccionCab retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM transacciones_cab WHERE id_transaccion = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
               // int id_categoria, int precio_unit, int cantidad, Integer id, String descripcion
                retValue = new TransaccionCab(rs.getInt("id_transaccion"),rs.getString("fecha"), rs.getInt("id_cliente"), rs.getInt("total"), rs.getString("direccion_envio"), rs.getInt("id_medio_pago"), rs.getInt("nro_tarjeta"), rs.getString("estado"));
            } else {
                retValue = new TransaccionCab(0,null, 0, 0, null, 0, 0, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retValue;
    }

    
    public Collection<TransaccionCab> getAll() {
       Collection<TransaccionCab> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM transacciones_cab");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new TransaccionCab(rs.getInt("id_transaccion"),rs.getString("fecha"), rs.getInt("id_cliente"), rs.getInt("total"), rs.getString("direccion_envio"), rs.getInt("id_medio_pago"), rs.getInt("nro_tarjeta"), rs.getString("estado")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retValue;
    }

    
    public void addDetalle(ArrayList<TransaccionDet> carrito) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        // Se recuera la ultima transaccion cab
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT MAX(id_transaccion) id_transaccion FROM transacciones_cab");
            rs = pstmt.executeQuery();
                    
            
            if (rs.next()) {
                System.out.println("entro");
                id = rs.getInt("id_transaccion");
            } else {
                id = 0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // se ingresa el detalle
        try {
            
            //se inserta en la tabla transasccion
            c = DBUtils.getConnection();
            int contadorItem = 1;
            for(TransaccionDet detalle : carrito) {  
                pstmt = c.prepareStatement("INSERT INTO transacciones_det "
                        + "(id_transaccion, item, id_producto, cantidad, precio, sub_total) values (?, ?, ?, ?, ?, ?)");

                pstmt.setInt(1, id);
                pstmt.setInt(2, contadorItem);
                pstmt.setInt(3, detalle.getId_producto());
                pstmt.setInt(4, detalle.getCantidad());
                pstmt.setInt(5, detalle.getPrecio());
                pstmt.setInt(6, detalle.getSub_total());
                
                pstmt.execute();
                contadorItem++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                DBUtils.closeConnection(c);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
