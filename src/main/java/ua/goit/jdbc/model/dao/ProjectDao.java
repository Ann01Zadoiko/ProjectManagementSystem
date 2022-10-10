package ua.goit.jdbc.model.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectDao {
    Integer id;
    String name;
    Integer cost;
    LocalDateTime dateCreate;

    public ProjectDao(){}

    public ProjectDao(Integer id, String name, Integer cost, LocalDateTime dateCreate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.dateCreate = dateCreate;
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

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "ProjectDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", dateCreate=" + dateCreate +
                '}';
    }

}
