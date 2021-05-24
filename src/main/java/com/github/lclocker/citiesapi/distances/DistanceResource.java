package com.github.lclocker.citiesapi.distances;

import com.github.lclocker.citiesapi.distances.service.DistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distances")
public class DistanceResource {

    private final DistanceService service;
    Logger log = LoggerFactory.getLogger(DistanceResource.class);

    public DistanceResource(DistanceService service) {
        this.service = service;
    }

    //Retorna a distancia entre duas cidades em milhas passando como parametro o id de cada cidade
    //Exemplo: /distances/by-points?from=3219&to=1314
    @GetMapping("/by-points")
    public ResponseEntity byPoints(@RequestParam(name = "from") final Long city1,
                                   @RequestParam(name = "to") final Long city2) {
        log.info("byPoints");
        return ResponseEntity.ok().body(service.distanceByPointsInMiles(city1, city2));
    }

    //Retorna a distancia entre duas cidades em Quilometros passando como parametro o id de cada cidade
    //Exemplo: /distances/by-cube?from=3219&to=1314
    @GetMapping("/by-cube")
    public ResponseEntity byCube(@RequestParam(name = "from") final Long city1,
                         @RequestParam(name = "to") final Long city2) {
        log.info("byCube");
        return ResponseEntity.ok().body(service.distanceByCubeInMeters(city1, city2)/1000);
    }
}