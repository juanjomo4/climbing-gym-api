package com.climbingapp.api.service;

import java.util.List;

import com.climbingapp.api.dto.GymDTO;
import com.climbingapp.api.entity.GymType;

public interface GymService {

    List<GymDTO> getAllGyms(String country, String city, GymType type, String name);

    GymDTO getGymById(Long id);
}
