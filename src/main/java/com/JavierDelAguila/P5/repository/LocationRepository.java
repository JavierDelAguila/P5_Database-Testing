package com.JavierDelAguila.P5.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.JavierDelAguila.P5.model.LocationModel;

//LocationModel es el modelo de objeto con el que va a trabajar mi CRUD, y String porque es el tipo de dato que usa la clave principal de su tabla
public interface LocationRepository extends CrudRepository<LocationModel, String>{

    @Query("SELECT u FROM LocationModel u WHERE u.name_location like :location")
    public LocationModel findByName_Location(@Param("location") String location);    

    @Modifying  //indica a Spring que estoy actualizando info de la base
    @Query("INSERT INTO LOCATIONS_SPAIN (NAME_LOCATION, LAT, LON, COMUNIDAD, PROVINCIA, HABITANTES) " +
    "VALUES (:nameLocation, :lat, :lon, :comunidad, :provincia, :habitantes)")
void save(@Param("nameLocation") String nameLocation,
       @Param("lat") Float lat,
       @Param("lon") Float lon,
       @Param("comunidad") String comunidad,
       @Param("provincia") String provincia,
       @Param("habitantes") Integer habitantes);

//SELECT * FROM LOCATIONS_SPAIN WHERE NAME_LOCATION LIKE 'Abla%'




}
