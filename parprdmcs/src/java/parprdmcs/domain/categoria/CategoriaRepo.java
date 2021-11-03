/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parprdmcs.domain.categoria;

import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import parprdmcs.util.Conexion;

/**
 *
 * @author Joseiba
 */
public class CategoriaRepo {

    public boolean insertarCategoria(Categoria categoria) {
        // Variable de control
        boolean valor = false;
        // Se realiza una coxeion con la bd
        if (Conexion.conectar()) {
            String sql = "INSERT INTO categoria (descripcion) VALUES ('"
                    + categoria.getDescripcion() + ");";
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException e) {
                System.err.println("Error: " + e);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public boolean eliminarCategoria(Categoria categoria) {
        // Variable de control
        boolean valor = false;
        // Se realiza una coxeion con la bd
        if (Conexion.conectar()) {
            String sql = "DELETE FROM categoria WHERE id_categoria = " + categoria.getId_categoria() + ";";
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException e) {
                System.err.println("Error: " + e);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public boolean modificarCategoria(Categoria categoria) {
        // Variable de control
        boolean valor = false;
        // Se realiza una coxeion con la bd
        if (Conexion.conectar()) {
            String sql = "UPDATE categoria SET "
                    + "descripcion = '" + categoria.getDescripcion() + "'"
                    + "WHERE id_categoria = " + categoria.getId_categoria();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException e) {
                System.err.println("Error: " + e);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public ArrayList<Categoria> ListarCategorias() {
        ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();

        if (Conexion.conectar()) {
            try {
                String sql = "SELECT * FROM categoria;";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Categoria categoriaAux = new Categoria(parseInt(rs.getString("id_categoria")),
                                rs.getString("descripcion"));
                        listaCategoria.add(categoriaAux);
                    }
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error: " + e);
                }
                Conexion.cerrar();
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
        Conexion.cerrar();
        return listaCategoria;
    }

    public Categoria getCategoria(int id_categoria) {
        
        Categoria categoria = new Categoria();
        
        if (Conexion.conectar()) {
            try {
                String sql = "SELECT * FROM categoria WHERE id_categoria= "+id_categoria;
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        categoria.setId_categoria(rs.getInt("id_categoria"));
                        categoria.setDescripcion(rs.getString("descripcion"));
                    }
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error: " + e);
                }
                Conexion.cerrar();
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
        Conexion.cerrar();
        return categoria;
    }
}
