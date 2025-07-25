package com.hotels.test.repositories;

import com.hotels.test.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepo extends JpaRepository<Hotel,Integer> {

}
