package parusrmcs.user.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import parusrmcs.util.DBUtils;
import parusrmcs.user.domain.model.entity.Entity;
import parusrmcs.user.domain.model.entity.User;

/**
 *
 * @author Joseiba
 */
public class JdbcUserRepository implements UserRepository<User, Integer> {

    private Map<String, User> entities;

    /**
     * Check if given user name already exist.
     *
     * @param nombre
     * @param apellido
     * @return true if already exist, else false
     */
    @Override
    public boolean containsNombreApellido(String nombre, String apellido) {
        try {
            return this.findByNombreApellido(nombre, apellido).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void add(User entity) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO cliente (nombre, apellido, email, login_name, password, tipo_cliente) values (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getLoginName());
            pstmt.setString(5, entity.getPasswd());
            pstmt.setInt(6, entity.getTipoCliente());

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

    /**
     *
     * @param id
     */
    @Override
    public void remove(Integer id) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");

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

    /**
     *
     * @param entity
     */
    @Override
    public void update(User entity) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE cliente SET nombre = ?, apellido = ?, email = ?, login_name = ?, password = ?, tipo_cliente = ? WHERE id_cliente = ?");

            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getLoginName());
            pstmt.setString(5, entity.getPasswd());
            pstmt.setInt(6, entity.getTipoCliente());
            pstmt.setInt(7, entity.getId());

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

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Entity get(Integer id) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("password"), rs.getInt("tipo_cliente"));
            } else {
                retValue = new User(null,null,null,null,null,null,0);
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

    /**
     *
     * @return
     */
    @Override
    public Collection<User> getAll() {
        Collection<User> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cliente");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("password"), rs.getInt("tipo_cliente")));
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

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    @Override
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception {
        Collection<User> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cliente WHERE nombre = ? and apellido = ?");

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("password"), rs.getInt("tipo_cliente")));
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
    public boolean containsLoginName(String loginName) {
        try {
            return this.findByLoginName(loginName).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    @Override
    public Collection<User> findByLoginName(String loginName) throws Exception {
        Collection<User> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cliente WHERE login_name = ?");

            pstmt.setString(1, loginName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("password"), rs.getInt("tipo_cliente")));
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
    public User login(String loginName) throws Exception {
        User retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM cliente WHERE login_name = ?");

            pstmt.setString(1, loginName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("password"), rs.getInt("tipo_cliente"));
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
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println(retValue);
        return retValue;
    }

}
