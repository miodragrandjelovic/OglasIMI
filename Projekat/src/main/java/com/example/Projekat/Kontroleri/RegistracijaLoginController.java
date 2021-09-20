package com.example.Projekat.Kontroleri;

import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.ObicneKlase.Korisnik;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@Controller
@RequestMapping("/")
public class RegistracijaLoginController {

    @GetMapping("/login")
    public String login() {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        if (logovanKorisnik == null || logovanKorisnik instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/";
    }

    @GetMapping("/registracija/greska/korisnickoIme")
    public String korisnickoImePostoji() { return "greskaKorisnickoIme"; }

    @GetMapping("/registracija/greska/email")
    public String lozinkaPostoji() { return "greskaEmail"; }

    @GetMapping("/registracija")
    public String registracija(@ModelAttribute("noviKorisnik") Korisnik noviKorisnik) {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        if (logovanKorisnik == null || logovanKorisnik instanceof AnonymousAuthenticationToken) { return "registracija"; }
        return "redirect:/";
    }

    @PostMapping("/registracija/poslodavac")
    public String registrujSePoslodavac(@ModelAttribute("noviKorisnik") Korisnik noviKorisnik, @RequestParam("file") MultipartFile slika){
        try{
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            noviKorisnik.setLozinka( (new BCryptPasswordEncoder()).encode(noviKorisnik.getLozinka()) );
            if(slika == null || slika.isEmpty())
                korisnikBaza.unesiKorisnika(noviKorisnik, "POSLODAVAC", null);
            else
                korisnikBaza.unesiKorisnika(noviKorisnik, "POSLODAVAC", slika.getBytes());
            korisnikBaza.zatvoriVezu();
        }
        catch(Exception e){
            if(e.equals("greskaKorisnickoIme")) return "redirect:/registracija/greska/korisnickoIme";
            else if(e.equals("greskaEmail")) return "redirect:/registracija/greska/email";
            else e.printStackTrace();
        }
        return "redirect:/login";
    }

    @PostMapping("/registracija/radnik")
    public String registrujSeRadnik(@ModelAttribute("noviKorisnik") Korisnik noviKorisnik, @RequestParam("file") MultipartFile slika){
        try{
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            noviKorisnik.setLozinka( (new BCryptPasswordEncoder()).encode(noviKorisnik.getLozinka()) );
            if(slika == null || slika.isEmpty())
                korisnikBaza.unesiKorisnika(noviKorisnik, "RADNIK", null);
            else
                korisnikBaza.unesiKorisnika(noviKorisnik, "RADNIK", slika.getBytes());
            korisnikBaza.zatvoriVezu();
        }
        catch(Exception e){
            if(e.equals("greskaKorisnickoIme")) return "redirect:/registracija/greska/korisnickoIme";
            else if(e.equals("greskaEmail")) return "redirect:/registracija/greska/email";
            else e.printStackTrace();
        }
        return "redirect:/login";
    }

}
