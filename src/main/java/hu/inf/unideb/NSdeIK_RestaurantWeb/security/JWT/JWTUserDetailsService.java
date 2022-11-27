package hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.SzemelyEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.SzemelyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private SzemelyRepository szemelyRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String felhasznalonev){
        List<SimpleGrantedAuthority> roles = null;
        SzemelyEntity szemely = szemelyRepository.findByFelhasznalonev(felhasznalonev);
        if (szemely == null) {
            throw new UsernameNotFoundException("User not found with username: " + felhasznalonev);
        }else{
            roles = Arrays.asList(new SimpleGrantedAuthority(szemely.getSzemely_poszt().toString()));
            return new User(szemely.getFelhasznalonev(), szemely.getJelszo(), roles);
        }
    }

    public SzemelyEntity save(SzemelyDto szemely){
        SzemelyEntity ujSzemely = new SzemelyEntity();
        ujSzemely.setSzemely_nev(szemely.getSzemely_nev());
        ujSzemely.setSzemely_poszt(szemely.getSzemely_poszt());
        ujSzemely.setFelhasznalonev(szemely.getFelhasznalonev());
        ujSzemely.setJelszo(bcryptEncoder.encode(szemely.getJelszo()));
        return szemelyRepository.save(ujSzemely);
    }

}
