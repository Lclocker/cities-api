package com.github.lclocker.citiesapi.states;

import com.github.lclocker.citiesapi.cities.City;
import com.github.lclocker.citiesapi.states.repository.StateRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateResource {

    private final StateRepository repository;
    private StateService stateService;


    public StateResource(final StateRepository repository, StateService stateService) {
        this.repository = repository;
        this.stateService = stateService;
    }

    //Retorna lista completa de estados
    // Ex: /states
    @GetMapping
    public List<State> states(){
        return repository.findAll();
    }

    //Retorna um estado pelo seu id
    //Exemplo: /states/16
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<State> optional = repository.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Retorna um estado pelo nome
    //Exemplo: /states/by_name?name=Pernambuco
    @GetMapping(value = "/by_name")
    public ResponseEntity findByName(final String name) throws ObjectNotFoundException {
        State obj = stateService.findByName(name);
        return ResponseEntity.ok().body(obj);
    };


}
