package hu.inf.unideb.NSdeIK_RestaurantWeb.security;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.SzemelyEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.SzemelyRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JWTUserDetailsService;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JwtAuthenticationEntryPoint;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private SzemelyRepository szemelyRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(jwtUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic().and().cors().disable().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/api/*").hasRole("TULAJDONOS")
                .antMatchers("/bejelentkezes").permitAll().anyRequest().authenticated()
                .and().formLogin().disable();

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
        configuration.setExposedHeaders(Collections.singletonList("X-Get-Header"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        SzemelyEntity existsUser = szemelyRepository.findByFelhasznalonev("admin");
        if(existsUser == null) {
            User.UserBuilder users = User.builder();
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(users.username("admin").password("admin_pass").roles("TULAJDONOS").build());

            SzemelyEntity ujSzemely = SzemelyEntity.builder()
                    .szemely_nev("XYZ tulajdonos")
                    .szemely_poszt(SzemelyPosztok.TULAJDONOS)
                    .felhasznalonev("admin")
                    .jelszo(passwordEncoder().encode("admin_pass"))
                    .build();

            szemelyRepository.save(ujSzemely);
            return manager;
        }else{
            return null;
        }
    }



}
