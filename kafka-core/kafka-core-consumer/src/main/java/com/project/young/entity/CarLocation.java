package com.project.young.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CarLocation {

    private String carId;
    private long timestamp;
    private int distance;
}
