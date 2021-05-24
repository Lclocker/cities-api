package com.github.lclocker.citiesapi.cities;

import com.github.lclocker.citiesapi.cities.repository.CityRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    private final CityRepository repo;

    public CityService(final CityRepository repo) {
        this.repo = repo;
    }

    public City findByName(String name) throws ObjectNotFoundException {
        Optional<City> obj = repo.findByName(name);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada: " + name));
    }

}
