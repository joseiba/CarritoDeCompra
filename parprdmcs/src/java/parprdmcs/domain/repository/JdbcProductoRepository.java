package parprdmcs.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import parprdmcs.util.DBUtils;
import parprdmcs.domain.model.entity.Entity;
import parprdmcs.domain.model.entity.Producto;

/**
 *
 * @author Joseiba
 */
public class JdbcProductoRepository implements ProductoRepository<Producto, Integer> {

    @Override
    public boolean containsDescripcion(String descripcion) {
        try {
            return this.findByDescripcion(descripcion).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;   
    }

    @Override
    public Collection<Producto> findByDescripcion(String descripcion) throws Exception {
        
        Collection<Producto> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        System.out.println(descripcion);
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto WHERE UPPER(descripcion) LIKE UPPER(?)");
            pstmt.setString(1, descripcion+"%");
            rs = pstmt.executeQuery();
            
            
            

            while (rs.next()) {
                retValue.add(new Producto(rs.getInt("id_categoria"), rs.getInt("precio_unit"), rs.getInt("cantidad"), rs.getInt("id_producto"), rs.getString("descripcion")));
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

    @Override
    public void add(Producto entity) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO producto (descripcion, id_categoria, precio_unit, cantidad) values (?, ?, ?, ?)");

            pstmt.setString(1, entity.getDescripcion());
            pstmt.setInt(2, entity.getId_categoria());
            pstmt.setInt(3, entity.getPrecio_unit());
            pstmt.setInt(4, entity.getCantidad());

            pstmt.execute();
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

    @Override
    public void remove(Integer id) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("DELETE FROM producto WHERE id_producto = ?");

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

    @Override
    public void update(Producto entity) {
        System.out.println("estoy aca");
        System.out.println(entity.toString());
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE producto SET descripcion = ?,"
                    + "id_categoria = ?, "
                    + "precio_unit = ?, "
                    + "cantidad = ? "
                    + "WHERE id_producto = ?");

            pstmt.setString(1, entity.getDescripcion());
            pstmt.setInt(2, entity.getId_categoria());
            pstmt.setInt(3, entity.getPrecio_unit());
            pstmt.setInt(4, entity.getCantidad());
            pstmt.setInt(5, entity.getId());

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

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity get(Integer id) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto WHERE id_producto = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
               // int id_categoria, int precio_unit, int cantidad, Integer id, String descripcion
                retValue = new Producto(rs.getInt("id_categoria"), rs.getInt("precio_unit"), rs.getInt("cantidad"), rs.getInt("id_producto"), rs.getString("descripcion"));
            } else {
                retValue = new Producto(0,0,0,0,null);
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

    @Override
    public Collection<Producto> getAll() {
       Collection<Producto> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto ORDER BY descripcion ASC");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Producto(rs.getInt("id_categoria"), rs.getInt("precio_unit"), rs.getInt("cantidad"), rs.getInt("id_producto"), rs.getString("descripcion")));
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

    @Override
    public Collection<Producto> findByCategoria(Integer id) throws Exception {
        Collection<Producto> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        System.out.println(id);
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto WHERE id_categoria = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retValue.add(new Producto(rs.getInt("id_categoria"), rs.getInt("precio_unit"), rs.getInt("cantidad"), rs.getInt("id_producto"), rs.getString("descripcion")));
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
    
}
