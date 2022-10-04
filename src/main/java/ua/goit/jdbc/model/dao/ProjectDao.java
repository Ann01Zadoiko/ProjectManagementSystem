package ua.goit.jdbc.model.dao;

import java.sql.Date;
import java.time.LocalDate;

public class ProjectDao {
    Integer id;
    String name;
    Integer cost;
    LocalDate date_create;

    public ProjectDao(){}

    public ProjectDao(Integer id, String name, Integer cost, LocalDate date_create) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date_create = date_create;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public LocalDate getDateOfCreation() {
        return date_create;
    }

    public void setDateOfCreation(LocalDate date_create) {
        this.date_create = date_create;
    }

    @Override
    public String toString() {
        return "ProjectDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", date_create=" + date_create +
                '}';
    }

}
