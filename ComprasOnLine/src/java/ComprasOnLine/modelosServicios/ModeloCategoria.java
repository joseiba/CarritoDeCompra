/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprasOnLine.modelosServicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ComprasOnLine.modelosClases.Categoria;

/**
 *
 * @author Joseiba
 */
public class ModeloCategoria {

    public ArrayList<Categoria> listar() throws IOException, JSONException {

        ArrayList<Categoria> listaCategoria;
        listaCategoria = new ArrayList();
        String url = "http://localhost:8080/parprdmcs/rest-categoria/categoriasapi/categorias/";
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
        JSONArray categoriasJSON = new JSONArray(response2.toString());
        Categoria categoria;

        for (int i = 0; i < categoriasJSON.length(); i++) {
            categoria = new Categoria(categoriasJSON.getJSONObject(i).getInt("id_categoria"),
                    categoriasJSON.getJSONObject(i).getString("descripcion"));

            listaCategoria.add(categoria);

        }

        return listaCategoria;
    }

    public Categoria listar(int id) throws MalformedURLException, IOException, JSONException {
        String url = "http://localhost:8080/parprdmcs/rest-categoria/categoriasapi/categorias/" + id;
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

        JSONObject categoriasJSON = new JSONObject(response2.toString());

        Categoria categoria;

        categoria = new Categoria(categoriasJSON.getInt("id_categoria"),
                    categoriasJSON.getString("descripcion"));

        System.out.println(categoria);

        return categoria;
    }
}
