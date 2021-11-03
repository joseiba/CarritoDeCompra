
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import parusrmcs.user.domain.model.entity.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class RESTEasyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User st = new User(0, "Joaquin", "Machuca", "j.machuca@pol.una.py", "j.machuca", "12345", 0);
        
        try {
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client.target("http://localhost:8080/parusrmcs/rest/userapi/userAdd");

            Response response = target.request().post(Entity.entity(st, "application/json"));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Server response : \n");
            System.out.println(response.readEntity(String.class));

            response.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
