package com.climbingapp.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.climbingapp.api.dto.GymDTO;
import com.climbingapp.api.entity.Gym;
import com.climbingapp.api.entity.GymType;
import com.climbingapp.api.exception.ResourceNotFoundException;
import com.climbingapp.api.repository.GymRepository;
import com.climbingapp.api.service.GymService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;

    @Override
    public List<GymDTO> getAllGyms(String country, String city, GymType type, String name) {
        // Convertir strings vacíos a null para la query
        String finalCountry = (country != null && country.isEmpty()) ? null : country;
        String finalCity = (city != null && city.isEmpty()) ? null : city;
        String finalName = (name != null && name.isEmpty()) ? null : name;

        List<Gym> gyms = gymRepository.findByFilters(finalCountry, finalCity, type, finalName);
        return gyms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GymDTO getGymById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gym not found with id: " + id));
        return convertToDTO(gym);
    }

    private GymDTO convertToDTO(Gym gym) {
        GymDTO dto = new GymDTO();
        dto.setId(gym.getId());
        dto.setName(gym.getName());
        dto.setCity(gym.getCity());
        dto.setCountry(gym.getCountry());
        dto.setAddress(gym.getAddress());
        dto.setType(gym.getType());
        dto.setWebsite(gym.getWebsite());
        dto.setLatitude(gym.getLatitude());
        dto.setLongitude(gym.getLongitude());
        return dto;
    }
}
