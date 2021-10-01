package com.osanda.example.dto;

import com.osanda.example.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto implements Serializable {
    private static final long serialVersionUID = 8906721819192533477L;

    private String name;

    public CityDto(City city) {
        this.name = city.getName();
    }
}


