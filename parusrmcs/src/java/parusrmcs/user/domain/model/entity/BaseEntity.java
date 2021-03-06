package parusrmcs.user.domain.model.entity;

/**
 *
 * @author Joseiba
 * @param <T>
 */
public abstract class BaseEntity<T> extends Entity<T> {

    private boolean isModified;

    /**
     *
     * @param id
     * @param nombre
     */
    public BaseEntity(T id, String nombre) {
        super.id = id;
        super.nombre = nombre;
        isModified = false;
    }

    /**
     *
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }

}
