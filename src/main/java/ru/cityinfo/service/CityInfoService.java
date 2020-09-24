package ru.cityinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.cityinfo.model.City;
import ru.cityinfo.model.CityInfo;
import ru.cityinfo.repository.CityInfoRepository;
import ru.cityinfo.repository.CityRepository;

import java.util.List;

import static ru.cityinfo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CityInfoService {

    private final CityInfoRepository cityInfoRepository;

    private final CityRepository cityRepository;

    public CityInfoService(CityInfoRepository cityInfoRepository, CityRepository cityRepository) {
        this.cityInfoRepository = cityInfoRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public CityInfo create(CityInfo cityInfo, int cityId) {
        Assert.notNull(cityInfo, "Info must not be null");
        City city = checkNotFoundWithId(cityRepository.getOne(cityId), cityId);
        cityInfo.setCity(city);
        return cityInfoRepository.save(cityInfo);
    }

    public void update(CityInfo cityInfo, int cityId) {
        Assert.notNull(cityInfo, "Info must not be null");
        cityInfo.setCity(cityRepository.getOne(cityId));
        checkNotFoundWithId(cityInfoRepository.save(cityInfo), cityInfo.getId());
    }

    public CityInfo get(int cityId, int cityInfoId) {
        return checkNotFoundWithId(cityInfoRepository.get(cityId, cityInfoId), cityInfoId);
    }

    public List<CityInfo> getAll(int cityId) {
        return cityInfoRepository.getAll(cityId);
    }

    public void delete(int cityId, int cityInfoId) {
        checkNotFoundWithId(cityInfoRepository.delete(cityId, cityInfoId) != 0, cityInfoId);
    }
}
