package ua.goit.jdbc.model.dto;

import java.util.Objects;

public class CustomerDto {
    Integer id;
    String name;
    String country;

    public CustomerDto(String name, String country){
        this.name = name;
        this.country = country;
    }

    public CustomerDto(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public CustomerDto(){}

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "CustomerDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
