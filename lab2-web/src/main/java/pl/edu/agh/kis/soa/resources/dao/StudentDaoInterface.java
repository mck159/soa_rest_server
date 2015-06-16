package pl.edu.agh.kis.soa.resources.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by maciek on 16.06.15.
 */
public interface StudentDaoInterface<T, Id extends Serializable> {
    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<T> findAll();
}
