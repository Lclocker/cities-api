package com.github.lclocker.citiesapi.countries;

import com.github.lclocker.citiesapi.countries.repository.CountryRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository repo;

    public CountryService(CountryRepository repo) {
        this.repo = repo;
    }

    public Country findByName(String name) throws ObjectNotFoundException {
        Optional<Country> obj = repo.findByName(name);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Estado não encontrado: " + name));
    }

}
