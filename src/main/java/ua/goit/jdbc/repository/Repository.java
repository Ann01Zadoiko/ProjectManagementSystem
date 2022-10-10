package ua.goit.jdbc.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T>{
    T save(T entity);
    T update(T entity);
    Optional<T> findById(Integer id);
    List<T> findAll();
    void delete(T entity);
}
