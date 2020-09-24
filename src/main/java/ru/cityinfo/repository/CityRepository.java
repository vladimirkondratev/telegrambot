package ru.cityinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cityinfo.model.City;

@Repository
@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM City c WHERE c.id=:id")
    int delete(@Param("id") int id);
}
