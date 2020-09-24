package ru.cityinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cityinfo.model.CityInfo;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface CityInfoRepository extends JpaRepository<CityInfo, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM CityInfo ci WHERE ci.id=:cityInfoId AND ci.city.id=:cityId")
    int delete(@Param("cityId") int cityId, @Param("cityInfoId") int cityInfoId);

    @Query("SELECT ci FROM CityInfo ci WHERE ci.id=:cityInfoId AND ci.city.id=:cityId")
    CityInfo get(@Param("cityId") int cityId, @Param("cityInfoId") int cityInfoId);

    @Query("SELECT ci FROM CityInfo ci WHERE ci.city.id=:cityId")
    List<CityInfo> getAll(@Param("cityId") int cityId);
}
