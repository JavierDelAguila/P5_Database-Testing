package com.JavierDelAguila.P5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavierDelAguila.P5.model.LocationModel;
import com.JavierDelAguila.P5.service.implementations.LocationServiceImpl;
import com.JavierDelAguila.P5.util.LoadCSVToDatabase;

@RestController //indica a Spring que esta clase recibe peticiones http
@RequestMapping("/api/v1") //antes de mi URL debe estar lo que indique entre ()
public class LocationController{
    @Autowired
    LocationServiceImpl locationService;

    private static int n=0;


    @GetMapping("/locations")
    public ResponseEntity<Iterable<LocationModel>> retrieveAll()
    {
        if (n==0)
        {
            LoadCSVToDatabase load = new LoadCSVToDatabase(locationService);
            load.cargarLocationsSpain();
            n++;
        }
        //return new ResponseEntity<Iterable<LocationModel>>(locationService.retrieveAll(), HttpStatus.OK);  
        return ResponseEntity.ok().body(locationService.retrieveAll());
    }

}
