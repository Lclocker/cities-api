package com.github.lclocker.citiesapi.countries.repository;

import com.github.lclocker.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
