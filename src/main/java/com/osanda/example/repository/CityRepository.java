package com.osanda.example.repository;

import com.osanda.example.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    City findByNameIgnoreCase(String name);
}
