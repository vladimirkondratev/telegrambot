package ru.cityinfo.web;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cityinfo.model.City;
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

    private final CityService service;

    public CityRestController(CityService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> create(@Valid @RequestBody City city) {
        log.info("create {}", city);
        City created = service.create(city);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CITY_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody City city, @PathVariable int id) {
        log.info("update {}", city);
        assureIdConsistent(city, id);
        service.update(city);
    }

    @DeleteMapping({"/id"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @GetMapping({"/id"})
    public City get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @GetMapping
    public List<City> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}
