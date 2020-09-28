package ru.cityinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cities", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "cities_idx")})
public class City extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    protected String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    @JsonManagedReference
    @JsonIgnore
    private List<CityInfo> infoList;

    protected City(){
    }

    public City(String name) {
        this.name = name;
    }

    public City(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<CityInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<CityInfo> infoList) {
        this.infoList = infoList;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
