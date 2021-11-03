/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprasOnLine.modelosServicios;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ComprasOnLine.modelosClases.Producto;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Joseiba
 */
public class ModeloProducto {
    ModeloCategoria modeloCategoria = new ModeloCategoria();
    
    public void agregar(Producto producto) throws JSONException {

        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos/";

        JSONObject productoNuevo = new JSONObject();

        productoNuevo.put("descripcion", producto.getDescripcion());
        productoNuevo.put("id_categoria", producto.getId_categoria());
        productoNuevo.put("precio_unit", producto.getPrecio_unit());
        productoNuevo.put("cantidad", producto.getCantidad());
        System.out.println(productoNuevo.toString());

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            OutputStream os = con.getOutputStream();
            os.write(productoNuevo.toString().getBytes("UTF-8"));
            os.close();

            //ver la respuesta 
            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            JSONObject myResponse = new JSONObject(result);
            System.out.println("La respuesta es: \n" + myResponse.toString());
            in.close();
            con.disconnect();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public void actualizar(Producto producto) throws JSONException {
        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos/";

        JSONObject productoNuevo = new JSONObject();

        productoNuevo.put("id", producto.getId());
        productoNuevo.put("descripcion", producto.getDescripcion());
        productoNuevo.put("id_categoria", producto.getId_categoria());
        productoNuevo.put("precio_unit", producto.getPrecio_unit());
        productoNuevo.put("cantidad", producto.getCantidad());
        System.out.println(productoNuevo.toString());

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("PUT");
            OutputStream os = con.getOutputStream();
            os.write(productoNuevo.toString().getBytes("UTF-8"));
            os.close();
            InputStream in = new BufferedInputStream(con.getInputStream());
            in.close();
            con.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public void eliminar(int id) throws IOException {

        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos/" + id;
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("DELETE");

        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'DELETE' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            in.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }

    public ArrayList<Producto> buscar(String descripcion) throws IOException, JSONException {

        ArrayList<Producto> listaProductos;
        listaProductos = new ArrayList();
        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos-des/"+ descripcion;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();

        //se genera el contenido de la tabla de clientes
        JSONArray productosJSON = new JSONArray(response2.toString());
        Producto producto;

        for (int i = 0; i < productosJSON.length(); i++) {
            producto = new Producto(productosJSON.getJSONObject(i).getInt("id"),
                    productosJSON.getJSONObject(i).getString("descripcion"),
                    productosJSON.getJSONObject(i).getInt("id_categoria"),
                    productosJSON.getJSONObject(i).getInt("precio_unit"),
                    productosJSON.getJSONObject(i).getInt("cantidad"),
                    modeloCategoria.listar(productosJSON.getJSONObject(i).getInt("id_categoria")).getDescripcion());

            listaProductos.add(producto);

        }

        return listaProductos;
    }
   
     public ArrayList<Producto> filtrarProducto(int id) throws IOException, JSONException {

        ArrayList<Producto> listaProductos;
        listaProductos = new ArrayList();
        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos-cat/"+ id;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();

        //se genera el contenido de la tabla de clientes
        JSONArray productosJSON = new JSONArray(response2.toString());
        Producto producto;

        for (int i = 0; i < productosJSON.length(); i++) {
            producto = new Producto(productosJSON.getJSONObject(i).getInt("id"),
                    productosJSON.getJSONObject(i).getString("descripcion"),
                    productosJSON.getJSONObject(i).getInt("id_categoria"),
                    productosJSON.getJSONObject(i).getInt("precio_unit"),
                    productosJSON.getJSONObject(i).getInt("cantidad"),
                    modeloCategoria.listar(productosJSON.getJSONObject(i).getInt("id_categoria")).getDescripcion());

            listaProductos.add(producto);

        }

        return listaProductos;
    }
     
    public ArrayList<Producto> listar() throws IOException, JSONException {

        ArrayList<Producto> listaProductos;
        listaProductos = new ArrayList();
        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();

        //se genera el contenido de la tabla de clientes
        JSONArray productosJSON = new JSONArray(response2.toString());
        Producto producto;

        for (int i = 0; i < productosJSON.length(); i++) {
            producto = new Producto(productosJSON.getJSONObject(i).getInt("id"),
                    productosJSON.getJSONObject(i).getString("descripcion"),
                    productosJSON.getJSONObject(i).getInt("id_categoria"),
                    productosJSON.getJSONObject(i).getInt("precio_unit"),
                    productosJSON.getJSONObject(i).getInt("cantidad"),
                    modeloCategoria.listar(productosJSON.getJSONObject(i).getInt("id_categoria")).getDescripcion());

            listaProductos.add(producto);

        }

        return listaProductos;
    }

    public Producto listar(int id) throws MalformedURLException, IOException, JSONException {
        String url = "http://localhost:8080/parprdmcs/rest-producto/productoapi/productos/" + id;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();

        JSONObject productosJSON = new JSONObject(response2.toString());

        Producto producto;

        producto = new Producto(productosJSON.getInt("id"),
                    productosJSON.getString("descripcion"),
                    productosJSON.getInt("id_categoria"),
                    productosJSON.getInt("precio_unit"),
                    productosJSON.getInt("cantidad"));

        System.out.println(producto);

        return producto;
    }

}
