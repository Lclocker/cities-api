package com.github.lclocker.citiesapi.states;

import com.github.lclocker.citiesapi.states.repository.StateRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {

    private final StateRepository repo;

    public StateService(final StateRepository repo) {
        this.repo = repo;
    }

    public State findByName(String name) throws ObjectNotFoundException {
        Optional<State> obj = repo.findByName(name);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Estado não encontrado: " + name));
    }
}
