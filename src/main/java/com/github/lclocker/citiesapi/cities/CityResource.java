package com.github.lclocker.citiesapi.cities;


import com.github.lclocker.citiesapi.cities.repository.CityRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("cities")
public class CityResource {

    private final CityRepository repository;
    private CityService cityService;

    public CityResource(final CityRepository repository, final CityService cityService) {
        this.repository = repository;
        this.cityService = cityService;
    }

    // Retorna a lista de todas as cidades se for chamada assim:
    // /cities
    // Retorna uma lista de cidades de um estado se for passado o id do estado
    // Exemplo: /cities?uf=16
    @GetMapping
    public Page<City> citiesByUf(Long uf, Pageable page) {
        if (uf != null) {
            return repository.getCitiesByUf(uf, page);
        }
        return repository.findAll(page);

    }

    // Retorna uma cidade pelo id
    // Exemplo: /cities/25
   @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<City> optional = repository.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    };

    //Retorna uma cidade pelo nome
    //Exemplo: /cities/by_name?name=Camaragibe
    @GetMapping(value = "/by_name")
    public ResponseEntity findByName(final String name) throws ObjectNotFoundException {
        City obj = cityService.findByName(name);
        return ResponseEntity.ok().body(obj);
    };




}