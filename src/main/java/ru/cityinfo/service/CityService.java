package ru.cityinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.cityinfo.model.City;
import ru.cityinfo.repository.CityRepository;

import java.util.List;

import static ru.cityinfo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CityService {

    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public City create(City city) {
        Assert.notNull(city, "City must not be null");
        return repository.save(city);
    }

    public void update(City city) {
        Assert.notNull(city, "City must not be null");
        repository.save(city);
    }

    public City get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<City> getAll() {
        return repository.findAll();
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
