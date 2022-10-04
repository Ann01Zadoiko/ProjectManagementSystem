package ua.goit.jdbc.repository;

import java.util.List;

public interface Repository <T>{
    T save(T entity);
    T update(T entity);
    T findById(Integer id);
    List<T> findAll();
    void delete(T entity);
}
