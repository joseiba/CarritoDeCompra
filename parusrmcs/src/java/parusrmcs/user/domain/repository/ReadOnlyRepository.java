package parusrmcs.user.domain.repository;

import java.util.Collection;
import parusrmcs.user.domain.model.entity.Entity;

/**
 *
 * @author Joseiba
 * @param <TE>
 * @param <T>
 */
public interface ReadOnlyRepository<TE, T> {

    //long Count;
    /**
     *
     * @param id
     * @return
     */
    boolean contains(T id);

    /**
     *
     * @param id
     * @return
     */
    Entity get(T id);

    /**
     *
     * @return
     */
    Collection<TE> getAll();
}
