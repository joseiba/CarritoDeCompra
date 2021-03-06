package parusrmcs.user.domain.repository;

import java.util.Collection;
import parusrmcs.user.domain.model.entity.User;

/**
 *
 * @author Joseiba
 * @param <User>
 * @param <Integer>
 */
public interface UserRepository<User, Integer> extends Repository<User, Integer> {

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     */
    boolean containsNombreApellido(String nombre, String apellido);

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception;
    
    /**
     *
     * @param loginName
     * @return
     */
    boolean containsLoginName(String loginName);

    /**
     *
     * @param loginName
     * @return
     * @throws Exception
     */
    public Collection<User> findByLoginName(String loginName) throws Exception;
     
    /**
     *
     * @param loginName
     * @return User
     * @throws Exception
     */
    public User login(String loginName) throws Exception;
}
