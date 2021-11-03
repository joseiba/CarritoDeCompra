package parprdmcs.domain.model.entity;

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
     * @param descripcion
     */
    public BaseEntity(T id, String descripcion) {
        super.id = id;
        super.descripcion = descripcion;
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
