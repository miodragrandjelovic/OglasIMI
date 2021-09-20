package com.example.Projekat.Kontroleri;

import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.ObicneKlase.Korisnik;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class PocetnaController {

    @GetMapping("/")
    public String pocetna() throws SQLException {
        return "index";
    }

}
