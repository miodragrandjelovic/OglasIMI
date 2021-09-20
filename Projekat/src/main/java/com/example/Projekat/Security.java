package com.example.Projekat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource baza;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(baza)
                .usersByUsernameQuery("select korisnicko_ime,lozinka,omogucen from korisnik where korisnickoIme = ?")
                .authoritiesByUsernameQuery("select korisnickoIme,roleModel from rolemodel where korisnickoIme = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    // UKOLIKO CSS NE RADI NAKON STO PODESITE PRISTUP DOLE ZOVITE ME
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("{stranice kojima podesavamo pristup}").{funkcije koje podesavaju ko ima pristup hasRole(),hasAnyRole(),authenticated()}
                .anyRequest().permitAll() // SVIM OSTALIM STRANICAMA MOZE DA PRISTUPI SVAKO
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?greska")
                .defaultSuccessUrl("/")
                // IZMENI IMENA INPUTA ZA IME I SIFRU NA LOGIN STRANI PRIMER:
                // <input type="text" name="{korisnickoIme}>
                // <input type="lozinka" name="{lozinka}>
                .usernameParameter("korisnickoIme")
                .passwordParameter("lozinka")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }
}