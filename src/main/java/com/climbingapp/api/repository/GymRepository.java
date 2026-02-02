package com.climbingapp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.climbingapp.api.entity.Gym;
import com.climbingapp.api.entity.GymType;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

    @Query("SELECT g FROM Gym g WHERE "
            + "(:country IS NULL OR :country = '' OR g.country = :country) AND "
            + "(:city IS NULL OR :city = '' OR g.city = :city) AND "
            + "(:type IS NULL OR g.type = :type) AND "
            + "(:name IS NULL OR :name = '' OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Gym> findByFilters(
            @Param("country") String country,
            @Param("city") String city,
            @Param("type") GymType type,
            @Param("name") String name
    );
}
