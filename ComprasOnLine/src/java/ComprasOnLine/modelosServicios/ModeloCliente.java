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
import ComprasOnLine.modelosClases.User;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Joseiba
 */
public class ModeloCliente {

    public void agregar(User usuario) throws JSONException {

        String url = "http://localhost:8080/parusrmcs/rest/userapi/users/";

        JSONObject clienteNuevo = new JSONObject();

        clienteNuevo.put("nombre", usuario.getNombre());
        clienteNuevo.put("apellido", usuario.getApellido());
        clienteNuevo.put("tipoCliente", usuario.getTipoCliente());
        clienteNuevo.put("email", usuario.getEmail());
        clienteNuevo.put("loginName", usuario.getLoginName());
        clienteNuevo.put("passwd", usuario.getPasswd());
        System.out.println(clienteNuevo.toString());

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            OutputStream os = con.getOutputStream();
            os.write(clienteNuevo.toString().getBytes("UTF-8"));
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

    public void actualizar(User usuario) throws JSONException {
        String url = "http://localhost:8080/parusrmcs/rest/userapi/users/";

        JSONObject clienteNuevo = new JSONObject();

        clienteNuevo.put("id", usuario.getId());
        clienteNuevo.put("nombre", usuario.getNombre());
        clienteNuevo.put("apellido", usuario.getApellido());
        clienteNuevo.put("tipoCliente", usuario.getTipoCliente());
        clienteNuevo.put("email", usuario.getEmail());
        clienteNuevo.put("loginName", usuario.getLoginName());
        clienteNuevo.put("passwd", usuario.getPasswd());
        System.out.println(clienteNuevo.toString());

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("PUT");
            OutputStream os = con.getOutputStream();
            os.write(clienteNuevo.toString().getBytes("UTF-8"));
            os.close();
            InputStream in = new BufferedInputStream(con.getInputStream());
            in.close();
            con.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public void eliminar(int id) throws IOException {

        String url = "http://localhost:8080/parusrmcs/rest/userapi/users/" + id;
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

    public ArrayList<User> listar() throws IOException, JSONException {

        ArrayList<User> listaUsuarios;
        listaUsuarios = new ArrayList();
        String url = "http://localhost:8080/parusrmcs/rest/userapi/users/";
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
        JSONArray clientesJSON = new JSONArray(response2.toString());
        User usuario;

        for (int i = 0; i < clientesJSON.length(); i++) {
            usuario = new User(clientesJSON.getJSONObject(i).getInt("id"),
                    clientesJSON.getJSONObject(i).getString("nombre"),
                    clientesJSON.getJSONObject(i).getString("apellido"),
                    clientesJSON.getJSONObject(i).getString("email"),
                    clientesJSON.getJSONObject(i).getString("loginName"),
                    clientesJSON.getJSONObject(i).getString("passwd"),
                    clientesJSON.getJSONObject(i).getInt("tipoCliente"));

            listaUsuarios.add(usuario);

        }

        return listaUsuarios;
    }

    public User listar(int id) throws MalformedURLException, IOException, JSONException {
        String url = "http://localhost:8080/parusrmcs/rest/userapi/users/" + id;
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

        JSONObject clientesJSON = new JSONObject(response2.toString());

        User usuario;

        usuario = new User(clientesJSON.getInt("id"),
                clientesJSON.getString("nombre"),
                clientesJSON.getString("apellido"),
                clientesJSON.getString("email"),
                clientesJSON.getString("loginName"),
                clientesJSON.getString("passwd"),
                clientesJSON.getInt("tipoCliente"));

        System.out.println(usuario);

        return usuario;
    }
    
    public User login(String loginName, String password) throws MalformedURLException, IOException, JSONException {
        String url = "http://localhost:8080/parusrmcs/rest/userapi/login/" + loginName;
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

        JSONObject clientesJSON = new JSONObject(response2.toString());

        User usuario;

        usuario = new User(clientesJSON.getInt("id"),
                clientesJSON.getString("nombre"),
                clientesJSON.getString("apellido"),
                clientesJSON.getString("email"),
                clientesJSON.getString("loginName"),
                clientesJSON.getString("passwd"),
                clientesJSON.getInt("tipoCliente"));

        System.out.println(usuario);
        if(usuario.getPasswd().equals(password)) {
            return usuario;
        }else{
            usuario = null;
            return usuario;
        }
        
    }
}
