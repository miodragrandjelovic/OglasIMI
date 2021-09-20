package com.example.Projekat.Kontroleri;

import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.ObicneKlase.Korisnik;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class MojProfilController {

    @GetMapping("/MojProfil")
    public String prikaziProfil(Model model){
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        try {
            if(!(logovanKorisnik instanceof AnonymousAuthenticationToken)){
                KorisnikBaza korisnikBaza = new KorisnikBaza();
                model.addAttribute("logovanKorisnik", korisnikBaza.korisnikPoImenu(logovanKorisnik.getName()));
                korisnikBaza.zatvoriVezu();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return "mojprofil";
    }

    @GetMapping("/MojProfil/IzmenaPodataka")
    public String prikaziIzmenuPodataka(@ModelAttribute("unetiKorisnik") Korisnik unetiKorisnik, Model model) {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        try {
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            Korisnik trenutniKorisnik = korisnikBaza.korisnikPoImenu(logovanKorisnik.getName());
            model.addAttribute("logovanKorisnik", trenutniKorisnik);

            unetiKorisnik.setIme(trenutniKorisnik.getIme());         unetiKorisnik.setPrezime(trenutniKorisnik.getPrezime());
            unetiKorisnik.seteMail(trenutniKorisnik.geteMail());     unetiKorisnik.setDatumRodjenja(trenutniKorisnik.getDatumRodjenja());
            unetiKorisnik.setDrzava(trenutniKorisnik.getDrzava());   unetiKorisnik.setGrad(trenutniKorisnik.getGrad());
            unetiKorisnik.setPol(trenutniKorisnik.getPol());         korisnikBaza.zatvoriVezu();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "izmenaprofila";
    }

    @PostMapping("/MojProfil/IzmenaPodataka")
    public String izmeniPodatke(@ModelAttribute("unetiKorisnik")Korisnik unetiKorisnik, Model model){
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        try {
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            Korisnik trenutniKorisnik = korisnikBaza.korisnikPoImenu(logovanKorisnik.getName());
            model.addAttribute("logovanKorisnik", korisnikBaza.korisnikPoImenu(logovanKorisnik.getName()));
            unetiKorisnik.setId(trenutniKorisnik.getId());

            korisnikBaza.promeniPodatkeKorisnika(unetiKorisnik);
            korisnikBaza.zatvoriVezu();

            return "redirect:/MojProfil";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/MojProfil/IzmenaPodataka";
        }
    }



}
