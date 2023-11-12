package com.example.demo.securityCofig;


import com.example.demo.entities.UserDetailsImplement;
import com.example.demo.service.serviceImple.ServiceImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {



    @Bean
  public UserDetailsService userDetailsService(){


      //  UserDetails user = User.withUsername("polo").password(passwordEncoder.encode("movies"))
               // .roles("USER").build();

        //UserDetails admin = User.withUsername("paul").password(passwordEncoder.encode("movies1"))
            //    .roles("ADMIN").build();

            return  new ServiceImplement();



}



                    @Bean
                    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            return  http.csrf().disable()
                    .authorizeHttpRequests().requestMatchers("/api/create").permitAll()
                    .and()
                    .authorizeHttpRequests().requestMatchers("/api/**").authenticated()
                    .and()
                    .httpBasic()
                    .and().build();

                    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}


@Bean
public  AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
}

}





