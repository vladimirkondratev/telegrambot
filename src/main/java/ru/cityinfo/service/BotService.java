package ru.cityinfo.service;

import org.springframework.stereotype.Service;
import ru.cityinfo.model.CityInfo;
import ru.cityinfo.repository.CityInfoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BotService {

    private final CityInfoRepository repository;

    public BotService(CityInfoRepository repository) {
        this.repository = repository;
    }

    public String retrieveCityInfos(String cityName) {
        cityName = cityName.strip().toLowerCase();
        List<CityInfo> cityInfos = repository.getAllByCity_Name(cityName);
        if (!cityInfos.isEmpty()) {
            return cityInfos.stream().map(CityInfo::getInfo).collect(Collectors.joining("\n"));
        } else {
            return "The city is not found.";
        }
    }
}
