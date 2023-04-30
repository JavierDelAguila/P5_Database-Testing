package com.JavierDelAguila.P5.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import lombok.Builder;
//import lombok.Data;

@Table("USUARIOS") //conecta mi clase con la tabla X creada
//@Builder //Genera el constructor de la clase solo. Gracias al paquete añadido Lombok
//@Data //Getters y setters, por Lombok
public class UserModel {
    //Ahora indicamos las columnas de mi clase
    @Id //clave principal
    @Column("USER_ID")
    private Long userId;

    @Column("USERNAME")
    private String username;

    @Column("PASSWORD_ENCRYPTED")
    private String password;

    @Column("NAME_LOCATION")
    private String nameLocation;

    public UserModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }



    
    
    @Override
    public String toString()  //ToString solo quiero enseñar la info que le importa al user
    {
        
        return "Username= " + username + "localidad= "+ nameLocation;
    }
    
    

    
}
