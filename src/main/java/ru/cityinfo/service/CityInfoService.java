package ru.cityinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.cityinfo.model.CityInfo;
import ru.cityinfo.repository.CityInfoRepository;

import static ru.cityinfo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CityInfoService {

    private final CityInfoRepository repository;

    public CityInfoService(CityInfoRepository repository) {
        this.repository = repository;
    }

    public CityInfo create(CityInfo cityInfo) {
        Assert.notNull(cityInfo, "Info must not be null");
        return repository.save(cityInfo);
    }

    public void update(CityInfo cityInfo) {
        Assert.notNull(cityInfo, "Info must not be null");
        repository.save(cityInfo);
    }

    public CityInfo get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
