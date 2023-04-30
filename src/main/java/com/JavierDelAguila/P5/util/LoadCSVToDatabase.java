package com.JavierDelAguila.P5.util;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.JavierDelAguila.P5.service.implementations.LocationServiceImpl;


@Component
public class LoadCSVToDatabase {

    //@Autowired
    private LocationServiceImpl locationService;

    
    public LoadCSVToDatabase(LocationServiceImpl locationService)
    {
        this.locationService=locationService;
        //this.cargarLocationsSpain();
    }


    private final static String DIR_LOCATION="src/main/resources/databases/listado-longitud-latitud-municipios-espana.csv";
    private final static int NAME_LOCATION=2;
    private final static int LAT=3;
    private final static int LON=4;
    private final static int COMUNIDAD=0;
    private final static int PROVINCIA=1;
    private final static int HABITANTES=6;



    public void cargarLocationsSpain() {
        String csvFile = DIR_LOCATION;
        String delimiter = ";";
    
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
    
            
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                //System.out.println(fields);
                locationService.save(fields[NAME_LOCATION],  Float.parseFloat(fields[LAT]), Float.parseFloat(fields[LON]), fields[COMUNIDAD], fields[PROVINCIA], Integer.parseInt(fields[HABITANTES]));
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}


