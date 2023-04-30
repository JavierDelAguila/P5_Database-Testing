package com.JavierDelAguila.P5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;

import com.JavierDelAguila.P5.DTO.UserDTO;

import com.JavierDelAguila.P5.service.implementations.UserServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //se genera un servidor de prueba donde hare las llamadas, en un puerto aleatorio
public class UserControllerE2ETest {  //E2E = End to End
    @LocalServerPort
    private int port; //como no se en que puerto va a estar, le pido que me lo de

    //las funciones de test son todas void

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TestRestTemplate restTemplate; //te permite hacer llamadas al server de pruebas y recoge la respuesta
    //Util para hacer E2E testing



    //los triangulos que hay en la izq son para ejecutar directamente el test sin hacer mvn ...


    
    @Test
    void getUserListByUserNameTest()
    {
        //Caso de busqueda correcto

        //Expected
        String busqueda_user="user";
        ArrayList<UserDTO> expected=userService.getUsersLikeUsername(busqueda_user);
        ArrayList<String> lista_resp= new ArrayList<String>();
        for(UserDTO s: expected)
        {
            lista_resp.add(s.toString());
        }


        //WHEN
        String url = "http://localhost:"+Integer.toString(port) + "/user_search/"+busqueda_user;
        HttpHeaders header= new HttpHeaders();
        //Añadiria header si tengo que meter cosas como Authorizaation
        HttpEntity<String> entity = new HttpEntity<>(header); //si tuviera body, (String body, header)


        //Given
        ResponseEntity<ArrayList<String>> resul = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<ArrayList<String>>(){});

        //CHECK
        then(resul.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(resul.getBody()).isEqualTo(lista_resp);


        //Caso de busqueda incorrecto en el que se ha dado un username que contiene caracteres especiales
        

        //Expected - null

        //WHEN
        String user_erroneo="&<>in23";
        url = "http://localhost:"+Integer.toString(port) + "/user_search/"+user_erroneo;
        //el resto es igual y ya esta instanciado antes

        //Given
        ResponseEntity<ArrayList<String>> resul2 = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<ArrayList<String>>(){});

        //Then
        then(resul2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(resul2.getBody()).isEqualTo(null);
    }







    @Test
    void putUserUpdateTest(){
        //Caso bueno
        String localidad_nueva="nueva";
        String mensaje_esperado="Operacion realizada con exito, tu nueva localidad es: " + localidad_nueva;


        //WHEN
        String usuario="user0";
        String password="user0";
        String password_repeat="user0";
        String httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        String url="http://localhost:"+Integer.toString(port)+"/user_update";

        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> order= new HttpEntity<String>(httpBody,header);

        //Given
        ResponseEntity<String> resp_buena= restTemplate.exchange(
            url,
            HttpMethod.PUT,
            order,
            new ParameterizedTypeReference<String>(){});

        //Then
        then(resp_buena.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(resp_buena.getBody()).isEqualTo(mensaje_esperado);


        //Usuario con caracteres especiales
        mensaje_esperado="\nError, el nombre de usuario no puede contener caracteres especiales, solo puede estar formado por letras y numeros."; 
 
        //WHEN
        usuario="user0%%%";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/user_update";
        order= new HttpEntity<String>(httpBody,header);
 
         //Given
         ResponseEntity<String> resp= restTemplate.exchange(
             url,
             HttpMethod.PUT,
             order,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(mensaje_esperado);
 


        //Contraseñas no son iguales
        mensaje_esperado="\nError, las contraseñas no son iguales.";
 
        //WHEN
        usuario="user0";
        password="2";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/user_update";
        order= new HttpEntity<String>(httpBody,header);
 
         //Given
        resp= restTemplate.exchange(
             url,
             HttpMethod.PUT,
             order,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(mensaje_esperado);


          
        //Localidad formada por caract raros
        mensaje_esperado="\nError, la localidad del usuario solo puede estar formada por letras.";
 
        //WHEN
        password="user0";
        localidad_nueva="A/(/A(SD/A)SD/()";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/user_update";
        order= new HttpEntity<String>(httpBody,header);
 
         //Given
        resp= restTemplate.exchange(
             url,
             HttpMethod.PUT,
             order,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(mensaje_esperado);
 

        //usuario no existe

        mensaje_esperado="Error, usuario y/o contraseña incorrectos";
        //WHEN
        usuario="usuarioNoExiste";
        localidad_nueva="Nueva";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/user_update";
        order= new HttpEntity<String>(httpBody,header);
 
         //Given
        resp= restTemplate.exchange(
             url,
             HttpMethod.PUT,
             order,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(mensaje_esperado);
 


        //Contrañsea incorrecta para el usuario
        mensaje_esperado="Error, usuario y/o contraseña incorrectos";
        //WHEN
        usuario="usuario0";
        password="9";
        password_repeat="9";
        localidad_nueva="Nueva";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/user_update";
        order= new HttpEntity<String>(httpBody,header);
 
         //Given
        resp= restTemplate.exchange(
             url,
             HttpMethod.PUT,
             order,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(mensaje_esperado);

    }







    @Test
    void deleteUserTest()
    {
        //Operacion correcta
        String  message="Operacion realizada con exito, usuario eliminado";

        //WHEN
        String usuario="user0";
        String password="user0";
        String password_repeat="user0";
        String httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        String url="http://localhost:"+Integer.toString(port)+"/delete_user";
        HttpEntity<String> entity= new HttpEntity<>(httpBody);

        //Given
        ResponseEntity<String> resul= restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<String>(){});

        //then

        then(resul.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(resul.getBody()).isEqualTo(message);


        //Usuario con caracteres especiales
        message="\nError, el nombre de usuario no puede contener caracteres especiales, solo puede estar formado por letras y numeros."; 

        //WHEN
        usuario="user0%%%";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
            ResponseEntity<String> resp= restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);
    


        //Contraseñas no son iguales
        message="\nError, las contraseñas no son iguales.";
    
        //WHEN
        usuario="user0";
        password="2";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
        resp= restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);


            

        //usuario no existe

        message="Error, usuario y/o contraseña incorrectos";
        //WHEN
        usuario="usuarioNoExiste";
        password="user0";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
        resp= restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);
    


        //Contrañsea incorrecta para el usuario
        message="Error, usuario y/o contraseña incorrectos";
        //WHEN
        usuario="usuario0";
        password="9";
        password_repeat="9";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
        resp= restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);

    }








    
    @Test
    void createUserTest()
    {
        //Operacion correcta
        String message="Su cuenta ha sido creada con éxito!";

        //WHEN
        String usuario="nuevoUser";
        String password="nuevoUser";
        String password_repeat="nuevoUser";
        String localidad_nueva="Nueva";
        String httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva; 
        String url="http://localhost:"+Integer.toString(port)+"/create_user";
        HttpEntity<String> entity= new HttpEntity<>(httpBody);

        //Given
        ResponseEntity<String> resul= restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<String>(){});

        //then

        then(resul.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        then(resul.getBody()).isEqualTo(message);


        //Usuario con caracteres especiales
        message="\nError, el nombre de usuario no puede contener caracteres especiales, solo puede estar formado por letras y numeros."; 

        //WHEN
        usuario="user0))()()(";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva; 
        entity= new HttpEntity<String>(httpBody);
    
            //Given
            ResponseEntity<String> resp= restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);
    
        //Devolvemos a la normalidad
        usuario="nuevoUser";

        //Contraseñas no son iguales
        message="\nError, las contraseñas no son iguales.";
    
        //WHEN
        password="2";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
        resp= restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resp.getBody()).isEqualTo(message);

        //Devolvemos a la normalidad para el siguiente caso
        password="nuevoUser";

        //Localidad formada por caract raros
        message="\nError, la localidad del usuario solo puede estar formada por letras.";
 
        //WHEN
        localidad_nueva="A/(/A(SD/A)SD/()";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad_nueva;
        url="http://localhost:"+Integer.toString(port)+"/create_user";
        entity= new HttpEntity<String>(httpBody);
 
         //Given
        resp= restTemplate.exchange(
             url,
             HttpMethod.POST,
             entity,
             new ParameterizedTypeReference<String>(){});
 
         //Then
         then(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
         then(resp.getBody()).isEqualTo(message);
 


            
        //Devolvemos a la normalidad para el siguiente caso
        localidad_nueva="Madrid";

        //usuario ya existe
        message="\nError, ya hay una cuenta creada con este username. Por favor, seleccione otro username.";
        //WHEN
        usuario="user0";
        httpBody="username="+usuario+"&password="+password+"&password_repeat="+password_repeat;
        entity= new HttpEntity<String>(httpBody);
    
            //Given
        resp= restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>(){});
    
            //Then
            then(resp.getStatusCode()).isEqualTo(HttpStatus.IM_USED);
            then(resp.getBody()).isEqualTo(message);
    
        //Devolvemos a la normalidad para el siguiente caso
        usuario="nuevoUser";
        

    }



}


    /*
    @Test
    public void GetAllUsersTest()
    {
        Iterable<UserModel> users=userRepository.findAll();

        //When  -> aqui ponemos las condiciones de nuestra prueba
        String url = "http://localhost:"+Integer.toString(port) + "/users";
        HttpHeaders headers= new HttpHeaders(); //no tengo header en este
        HttpEntity<String> entity= new HttpEntity<>(headers); //esto es como lo que meto en postman
        //de esta llamada de postman, yo espero que me llegue la lista completa de usuarios que tengo



        //Given -> Ejecuto la prueba
        ResponseEntity<Iterable<UserModel>> results = restTemplate.exchange(
            url,  //a que URL llamo
            HttpMethod.GET, //Que metodo uso
            entity, //Cual es el HTTP que le envio
            new ParameterizedTypeReference<Iterable<UserModel>>(){} ); //Que formato quiero para mi respuesta



        //Check results
        then(results.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(results.getBody()).isEqualTo(users);
    }
*/