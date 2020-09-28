package ru.cityinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cityinfo.model.City;

@Repository
@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Integer> {
}
