package ru.cityinfo.web;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cityinfo.model.City;
import ru.cityinfo.model.CityInfo;
import ru.cityinfo.service.CityInfoService;
import ru.cityinfo.service.CityService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.cityinfo.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = CityRestController.CITY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CityRestController {
    private static final Logger log = getLogger(CityRestController.class);

    public static final String CITY_URL = "/cities";

    public static final String CITYINFO_URL = "/{cityId}/cityinfos";

    private final CityService cityService;

    private final CityInfoService cityInfoService;

    public CityRestController(CityService cityService, CityInfoService cityInfoService) {
        this.cityService = cityService;
        this.cityInfoService = cityInfoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> createCity(@Valid @RequestBody City city) {
        log.info("create {}", city);
        City created = cityService.create(city);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CITY_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCity(@Valid @RequestBody City city, @PathVariable int id) {
        log.info("update {}", city);
        assureIdConsistent(city, id);
        cityService.update(city);
    }

    @DeleteMapping({"/id"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable int id) {
        log.info("delete {}", id);
        cityService.delete(id);
    }

    @GetMapping({"/id"})
    public City getCity(@PathVariable int id) {
        log.info("get {}", id);
        return cityService.get(id);
    }

    @GetMapping
    public List<City> getAllCities() {
        log.info("getAll");
        return cityService.getAll();
    }

    @PostMapping(value = CITYINFO_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityInfo> createCityInfo(@Valid @RequestBody CityInfo cityInfo, @PathVariable int cityId) {
        log.info("create {} for city {}", cityInfo, cityId);
        CityInfo created = cityInfoService.create(cityInfo, cityId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CITY_URL + CITYINFO_URL + "/{id}")
                .buildAndExpand(cityId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = CITYINFO_URL + "/{cityInfoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCityInfo(@Valid @RequestBody CityInfo cityInfo, @PathVariable int cityId, @PathVariable int cityInfoId) {
        log.info("update cityInfo {}", cityInfo);
        assureIdConsistent(cityInfo, cityInfoId);
        cityInfoService.update(cityInfo, cityId);
    }

    @DeleteMapping(value = CITYINFO_URL + "/{cityInfoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCityInfo(@PathVariable int cityId, @PathVariable int cityInfoId) {
        log.info("delete cityInfo {} for city {}", cityInfoId, cityId);
        cityInfoService.delete(cityId, cityInfoId);
    }

    @GetMapping(value = CITYINFO_URL + "/{cityInfoId}")
    public CityInfo getCityInfo(@PathVariable int cityId, @PathVariable int cityInfoId) {
        return cityInfoService.get(cityId, cityInfoId);
    }

    @GetMapping(value = CITYINFO_URL)
    public List<CityInfo> getAllCityInfoForCity(@PathVariable int cityId) {
        return cityInfoService.getAll(cityId);
    }
}
