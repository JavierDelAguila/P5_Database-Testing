package com.JavierDelAguila.P5.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table("LOCATIONS_SPAIN") //conecta mi clase con la tabla X creada
@Builder //Genera el constructor de la clase solo. Gracias al paquete añadido Lombok
@Data //Getters y setters, por Lombok
public class LocationModel {
    //Ahora indicamos las columnas de mi clase
    @Id //clave principal
    @Column("NAME_LOCATION")
    private String name_location;

    @Column("LAT")
    private Float lat;

    @Column("LON")
    private Float lon;

    @Column("COMUNIDAD")
    private String comunidad;

    @Column("PROVINCIA")
    private String provincia;

    @Column("HABITANTES")
    private Integer habitantes;
    
}

