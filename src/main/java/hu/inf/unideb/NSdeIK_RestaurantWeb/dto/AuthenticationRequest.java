package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    private String felhasznalonev;
    private String jelszo;
}
