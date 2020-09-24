package ru.cityinfo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "cityinfo")
public class CityInfo extends AbstractBaseEntity{

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private City city;

    public CityInfo() {
    }

    public CityInfo(String info) {
        this(null, info);
    }

    public CityInfo(Integer id, String info) {
        super(id);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
