package com.codegym.exam.service;

import com.codegym.exam.model.City;
import com.codegym.exam.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {
    Page<Country> findAll(Pageable pageable);

    Optional<Country> findById(Long id);

    void save(Country country);

    void remove(Long id);
}
