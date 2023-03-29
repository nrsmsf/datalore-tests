package com.jetbrains.api.requestDTO;

import lombok.*;

@Data
@Getter
@Setter
public class UserCredentialsRequest {
    public String email;
    public String password;
    public UserCredentialsRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
