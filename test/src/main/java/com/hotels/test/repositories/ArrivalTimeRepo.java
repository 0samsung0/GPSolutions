package com.hotels.test.repositories;

import com.hotels.test.entities.ArrivalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArrivalTimeRepo extends JpaRepository<ArrivalTime, Integer> {
}
