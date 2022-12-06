package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String role;

}