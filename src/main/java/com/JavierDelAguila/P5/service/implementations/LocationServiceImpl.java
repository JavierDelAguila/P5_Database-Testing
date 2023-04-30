package com.JavierDelAguila.P5.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavierDelAguila.P5.model.LocationModel;
import com.JavierDelAguila.P5.repository.LocationRepository;
import com.JavierDelAguila.P5.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
    
    @Autowired
    LocationRepository locationRepository;

    @Override
    public Iterable<LocationModel> retrieveAll()
    {
        return locationRepository.findAll();
    }


    @Override
    public void save(String username, float lat, float lon, String comunidad, String provincia, int habitantes)
    {
        locationRepository.save(username,lat,lon,comunidad,provincia,habitantes);
    }

}
