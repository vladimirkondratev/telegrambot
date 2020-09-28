package ru.cityinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cityinfo.model.CityInfo;

import java.util.List;


@Repository
@RepositoryRestResource(path = "cityinfos", collectionResourceRel = "cityinfos")
@Transactional(readOnly = true)
public interface CityInfoRepository extends JpaRepository<CityInfo, Integer> {
    List<CityInfo> getAllByCityNameIgnoreCase(String name);
}
