package com.climbingapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbingapp.api.dto.GymDTO;
import com.climbingapp.api.entity.GymType;
import com.climbingapp.api.service.GymService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gyms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GymController {

    private final GymService gymService;

    @GetMapping
    public ResponseEntity<List<GymDTO>> getAllGyms(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name
    ) {
        System.out.println("========== LLAMADA A /api/gyms ==========");
        System.out.println("country: " + country);
        System.out.println("city: " + city);
        System.out.println("type: " + type);
        System.out.println("name: " + name);

        GymType gymType = null;
        if (type != null && !type.isEmpty()) {
            try {
                gymType = GymType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                gymType = null;
            }
        }

        List<GymDTO> gyms = gymService.getAllGyms(country, city, gymType, name);
        System.out.println("Gyms encontrados: " + gyms.size());
        System.out.println("=========================================");

        return ResponseEntity.ok(gyms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymDTO> getGymById(@PathVariable Long id) {
        GymDTO gym = gymService.getGymById(id);
        return ResponseEntity.ok(gym);
    }
}
