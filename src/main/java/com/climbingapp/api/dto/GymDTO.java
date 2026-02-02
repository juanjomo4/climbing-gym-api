package com.climbingapp.api.dto;

import com.climbingapp.api.entity.GymType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymDTO {

    private Long id;
    private String name;
    private String city;
    private String country;
    private String address;
    private GymType type;
    private String website;
    private Double latitude;
    private Double longitude;
}
