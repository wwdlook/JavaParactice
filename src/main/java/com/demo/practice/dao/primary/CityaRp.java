package com.demo.practice.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityaRp extends JpaRepository<Citya,Long> {

    @Query(nativeQuery = true,value =
            "select id, name from city limit 1")
    Citya findByNameLike(@Param("name") String name);
}

