package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AuthenticationRequest;
import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AuthenticationResponse;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.SzemelyRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JWTUserDetailsService;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class JwtAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    SzemelyRepository szemelyRepository;

    @PostMapping("/bejelentkezes")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authRequest){
        if(szemelyRepository.findByFelhasznalonev(authRequest.getFelhasznalonev()) == null)
        {
            return ResponseEntity.badRequest().body("Hiba: Nincs ilyen felhasználónév!");
        }
        try{
            authenticate(authRequest.getFelhasznalonev(),authRequest.getJelszo());
        }catch (BadCredentialsException ex){
            return ResponseEntity.badRequest().body("Hiba: Hibás jelszó!");
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(authRequest.getFelhasznalonev());
        String token = jwtUtil.generateToken(userdetails);
        List<String> roles = userdetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new AuthenticationResponse(token, roles.get(0)));
    }

    private void authenticate(String felhasznalonev, String jelszo) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(felhasznalonev, jelszo));
    }

}
