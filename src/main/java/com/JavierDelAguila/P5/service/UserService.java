package com.JavierDelAguila.P5.service;


import java.util.ArrayList;

import com.JavierDelAguila.P5.DTO.UserDTO;
import com.JavierDelAguila.P5.model.UserModel;

public interface UserService{
    Iterable<UserModel> retrieveAll();

    void insertUser(String username, String password);
    void insertUser(String username, String password, String location);

    UserModel getUserByUsername(String username);
    ArrayList<UserDTO> getUsersLikeUsername(String username);

    boolean checkCredencialesUser(String username, String password_encrypted);
    boolean updateUser(String username, String password_encrypted,String location);
    boolean deleteUser(String username, String password_encrypted);
    
}
