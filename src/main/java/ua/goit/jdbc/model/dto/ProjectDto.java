package ua.goit.jdbc.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectDto {
    Integer id;
    String name;
    Integer cost;
    LocalDate date_create;

    public ProjectDto(String name, Integer cost, LocalDate date_create){
        this.name = name;
        this.cost = cost;
        this.date_create = date_create;
    }

    public ProjectDto(Integer id, String name, Integer cost, LocalDate date_create) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date_create = date_create;
    }

    public ProjectDto(){}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(date_create, that.date_create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, date_create);
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", date_create=" + date_create +
                '}';
    }
}
