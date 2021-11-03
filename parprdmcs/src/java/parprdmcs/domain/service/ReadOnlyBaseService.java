package parprdmcs.domain.service;

import parprdmcs.domain.repository.Repository;

/**
 *
 * @author Joseiba
 * @param <TE>
 * @param <T>
 */
public abstract class ReadOnlyBaseService<TE, T> {

    private Repository<TE, T> repository;

    ReadOnlyBaseService(Repository<TE, T> repository) {
        this.repository = repository;
    }
}
