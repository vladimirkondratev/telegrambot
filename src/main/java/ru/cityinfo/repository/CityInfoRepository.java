package ru.cityinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cityinfo.model.CityInfo;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM CityInfo ci WHERE ci.id=:id")
    int delete(@Param("id") int id);
}
