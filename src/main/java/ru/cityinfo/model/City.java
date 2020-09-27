package ru.cityinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "cities_idx")})
public class City extends AbstractNamedEntity{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    @JsonManagedReference
    @JsonIgnore
    private List<CityInfo> infoList;

    public City(){
    }

    public City(String name) {
        this(null, name);
    }

    public City(Integer id, String name) {
        super(id, name);
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
