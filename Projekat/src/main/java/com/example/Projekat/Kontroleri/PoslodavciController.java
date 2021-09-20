package com.example.Projekat.Kontroleri;

import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.ObicneKlase.Korisnik;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PoslodavciController {

    @GetMapping("SpisakPoslodavaca")
    public String spisakPoslodavaca(Model model){

        try {

            Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
            if (!(logovanKorisnik instanceof AnonymousAuthenticationToken)) {
                Korisnik trenutniKorisnik = new KorisnikBaza().korisnikPoImenu(logovanKorisnik.getName());
                model.addAttribute("logovanKorisnik", trenutniKorisnik);
            }

            KorisnikBaza korisnikBaza = new KorisnikBaza();
            List<Korisnik> listaKorisnika;
            listaKorisnika = korisnikBaza.sviPoslodavci();

            model.addAttribute("listaKorisnika", listaKorisnika);

            korisnikBaza.zatvoriVezu();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "SpisakPoslodavaca";
    }

}
