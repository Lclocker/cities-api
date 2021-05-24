package com.github.lclocker.citiesapi.countries;

import com.github.lclocker.citiesapi.countries.repository.CountryRepository;
import com.github.lclocker.citiesapi.states.State;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    private CountryRepository repository;
    private CountryService countryService;


    public CountryResource(CountryRepository repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    //Retorna a lista completa de paises
    //Exemplo: /countries
    @GetMapping
    public Page<Country> countries(Pageable page) {
        return repository.findAll(page);
    }

    //Retorna um pais pelo id
    //Exemplo: /countries/10
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<Country> optional = repository.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Retorna um pais pelo nome
    //Exemplo: /countries/by_name?name=Brazil
    @GetMapping(value = "/by_name")
    public ResponseEntity findByName(final String name) throws ObjectNotFoundException {
        Country obj = countryService.findByName(name);
        return ResponseEntity.ok().body(obj);
    };
}
