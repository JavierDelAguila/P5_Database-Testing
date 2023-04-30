package com.JavierDelAguila.P5.repository;

import java.util.ArrayList;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.JavierDelAguila.P5.model.UserModel;

//UserModel es el modelo de objeto con el que va a trabajar mi CRUD, y Long porque es el tipo de dato que usa la clave principal de su tabla
public interface UserRepository extends CrudRepository<UserModel, Long>{
    
    @Query("SELECT * FROM USUARIOS WHERE username= :searched_username")
    public UserModel getUserByUsername(@Param("searched_username") String searched_username);

    //@Query("SELECT USERNAME,LOCATION FROM USUARIOS WHERE username LIKE :searched_username%)")
    @Query("SELECT * FROM USUARIOS WHERE username LIKE CONCAT(:searched_username, '%')")
    public ArrayList<UserModel> getUsersLikeUsername(@Param("searched_username") String searched_username);

    @Modifying
    @Query("INSERT INTO USUARIOS (USERNAME, PASSWORD_ENCRYPTED) " + 
    "VALUES (:username, :password)" )
    public void insertUser(
        @Param("username") String username,
        @Param("password") String password);


    @Modifying
    @Query("INSERT INTO USUARIOS (USERNAME, PASSWORD_ENCRYPTED, NAME_LOCATION) "
    + "VALUES (:username, :password, :location)" )
        public void insertUser(
        @Param("username") String username,
        @Param("password") String password,
        @Param("location") String location);

    
    @Modifying
    @Transactional  
    @Query("UPDATE USUARIOS SET NAME_LOCATION = :location WHERE USERNAME = :username")
    public void updateUser(
        @Param("username") String username,
        @Param("location") String location);
    //IMPORTANTE - Transactional es porque como estamos leyendo y escribiendo, si se produce un error durante la ejecución del método, 
    //Spring revierte la transacción y deshace todos los cambios realizados en la base de datos durante la transacción.

    

    @Modifying
    @Transactional
    @Query("DELETE FROM USUARIOS WHERE USERNAME = :username")
    public void deleteUser(@Param("username") String username);
     //Delete borra filas, no columnas. Por ello ponemos DELETE FROM... y |no| DELETE * FROM ...


}