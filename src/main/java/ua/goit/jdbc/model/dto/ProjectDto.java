package ua.goit.jdbc.model.dto;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProjectDto {
    Integer id;
    String name;
    Integer cost;
    LocalDateTime dateCreate;

    public ProjectDto(String name, Integer cost, LocalDateTime dateCreate){
        this.name = name;
        this.cost = cost;
        this.dateCreate = dateCreate;
    }

    public ProjectDto(Integer id, String name, Integer cost, LocalDateTime dateCreate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.dateCreate = dateCreate;
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

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(dateCreate, that.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, dateCreate);
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
