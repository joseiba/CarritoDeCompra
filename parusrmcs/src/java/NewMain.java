
import parusrmcs.user.domain.model.entity.User;
import parusrmcs.user.domain.repository.JdbcUserRepository;
import parusrmcs.user.domain.service.UserServiceImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        UserServiceImpl usi = new UserServiceImpl(new JdbcUserRepository());
//        User user = new User(0, "Mauricio2", "Machuca2", "m.machuca@pol.una.py2", "m.machuca2", "mauricio20192", 0);
        User user1 = new User(22, "Jose", "Ibañez", "test@hotmail.com", "joseiba", "joseiba", 0);
//        usi.add(user);
          usi.add(user1);
    }
    
}
