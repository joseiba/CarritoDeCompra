package parprdmcs.domain.model.entity;

/**
 *
 * @author Joseiba
 * @param <T>
 */
public abstract class Entity<T> {

    T id;
    String descripcion;

    /**
     *
     * @return
     */
    public T getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(T id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }

}
