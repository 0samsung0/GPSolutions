package com.hotels.test.services;

import com.hotels.test.repositories.ArrivalTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrivalTimeService {
    ArrivalTimeRepo arrivalTimeRepo;

    @Autowired
    public ArrivalTimeService(ArrivalTimeRepo arrivalTimeRepo){
        this.arrivalTimeRepo = arrivalTimeRepo;
    }
} 