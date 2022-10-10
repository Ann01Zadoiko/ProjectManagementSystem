package ua.goit.jdbc.service;

import java.util.List;
import java.util.Optional;

public interface Service<T>{
    T save(T entity);

    T update(T entity);

    void delete(T entity);

    Optional<T> findById(Integer id);

    List<T> findAll();

}
