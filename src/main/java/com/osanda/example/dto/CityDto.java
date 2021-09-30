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

    private Long id;

    private String name;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}


