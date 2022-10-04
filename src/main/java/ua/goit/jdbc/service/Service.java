package ua.goit.jdbc.service;

import java.util.List;

public interface Service<T>{
    T save(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(Integer id);

    List<T> findAll();

}
