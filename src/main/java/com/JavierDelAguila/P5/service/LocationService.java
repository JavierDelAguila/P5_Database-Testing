package com.JavierDelAguila.P5.service;

import com.JavierDelAguila.P5.model.LocationModel;

public interface LocationService {
    public Iterable<LocationModel> retrieveAll();
    public void save(String username, float lat, float lon, String comunidad, String provincia, int habitantes);


}



