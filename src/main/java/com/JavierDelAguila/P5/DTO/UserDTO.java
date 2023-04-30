package com.JavierDelAguila.P5.DTO;

//import org.json.JSONObject;


public class UserDTO {
    Long userId;
    String username;
    String localidad;
    //no incluimos password por seguridad

    
    public String getUsername() {
        return username;
    }
    
    public UserDTO(Long userId, String username, String localidad) {
        this.userId = userId;
        this.username = username;
        this.localidad = localidad;
    }

    public UserDTO(String username, String localidad) {
        this.username = username;
        this.localidad = localidad;
    }


    public UserDTO(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        String loc="";
        if(localidad==null)
        {
            loc="Desconocida";
        }
        else{
            loc=localidad;
        }
        return "Username: " + username + ", localidad: " + loc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        UserDTO other = (UserDTO) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    


/*
    public JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("localidad", localidad);
        return obj;
    }
*/



    
}
