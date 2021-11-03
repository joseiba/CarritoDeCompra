/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parprdmcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joseiba
 */
public class Conexion {
        //atributos
    public static String driver = "org.postgresql.Driver";
    public static String servidor = "localhost";
    public static String puerto = "5432";
    public static String BaseDato = "paronline";
    public static String usuario = "postgres"; //usuario por defecto (cambiar)
    public static String clave = "postgres"; //constraseña por defecto (cambiar)
    public static Connection conn;
    public static Statement st;
    
    /*METODO PARA REALIZAR UNA CONEXION CON LA BD*/
    public static boolean conectar() {
        
        //variable de control
        boolean valor = false;
        
        try{
            Class.forName(driver);
            String url = "jdbc:postgresql://"+servidor+":"+puerto+"/"+BaseDato;
            conn = DriverManager.getConnection(url, usuario, clave);
            st = conn.createStatement();
            valor = true;           
        }catch(ClassNotFoundException ex){
            //se imprime un mensaje de excepcion por consola
            System.err.println("Error: "+ ex);
        }catch(SQLException ex){
            //se imprime un mensaje de excepcion por consola            
            System.err.println("Error: "+ ex);
        }
        
        //se retorna un valor booleano
        return valor;
        
    }//fin de el metodo conectar
    
    /*METODO PARA CERRAR LA CONEXION CON LA BD*/
    public static boolean cerrar(){
        boolean valor = false;
        
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error: "+ ex);
        }
        
        return valor;
       
    }//fin del metodo cerrar

    public static Connection getConn() {
        return conn;
    }

    public static Statement getSt() {
        return st;
    }
}
