package com.codegym.exam.repository;

import com.codegym.exam.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
}
