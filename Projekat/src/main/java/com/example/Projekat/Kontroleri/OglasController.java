package com.example.Projekat.Kontroleri;

import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.Baza.OglasBaza;
import com.example.Projekat.Baza.PrijavaNaOglasBaza;
import com.example.Projekat.ObicneKlase.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class OglasController {

    @GetMapping("/oglas/{idOglasa}")
    public String prikaziOglas(@PathVariable("idOglasa") long idOglasa, Model model) throws SQLException, UnsupportedEncodingException {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        // LOGOVAN
        if (!(logovanKorisnik instanceof AnonymousAuthenticationToken)){

            try{
                KorisnikBaza korisnikDatabase = new KorisnikBaza();
                OglasBaza oglasDatabase = new OglasBaza();

                Korisnik trenutniKorisnik = korisnikDatabase.korisnikPoImenu(logovanKorisnik.getName());
                model.addAttribute("logovanKorisnik", trenutniKorisnik);

                Oglas trenutniOglas = oglasDatabase.jedanOglas(idOglasa);
                model.addAttribute("oglas", trenutniOglas);

                korisnikDatabase.zatvoriVezu();
                oglasDatabase.zatvoriVezu();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        // NIJE LOGOVAN
        else{

        }
        try{
            OglasBaza oglasBaza = new OglasBaza();

            Oglas trenutniOglas = oglasBaza.jedanOglas(idOglasa);
            Korisnik vlasnikOglasa = trenutniOglas.getPoslodavac();

            model.addAttribute("trenutniOglas", trenutniOglas);
            model.addAttribute("vlasnikOglasa", vlasnikOglasa);

            oglasBaza.zatvoriVezu();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        OglasBaza oglasBaza = new OglasBaza();

        ArrayList<Prijava> prijave = null;
        prijave = oglasBaza.svePrijaveZaOglas(idOglasa);
        model.addAttribute("prijave", prijave);

        oglasBaza.zatvoriVezu();

        return "posebanoglas";
    }


    @PostMapping("/oglas/{idOglasa}")
    public String prijaviSeNaOglas(@PathVariable("idOglasa") long idOglasa){
        try {
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            OglasBaza oglasBaza = new OglasBaza();

            Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
            Korisnik korisnik = korisnikBaza.korisnikPoImenu(logovanKorisnik.getName());

            Oglas oglas = oglasBaza.jedanOglas(idOglasa);
            oglasBaza.unesiPrijavu(korisnik, oglas);

            oglasBaza.zatvoriVezu();
            korisnikBaza.zatvoriVezu();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/oglas/" + idOglasa;
    }

    @GetMapping("/oglas/postaviOglas")
    public String prikaziPostavljanjeOglasa(@ModelAttribute("oglas") Oglas oglas, Model model) {
        try {
            Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            model.addAttribute("trenutniKorisnik", korisnikBaza.korisnikPoImenu(logovanKorisnik.getName()));
            korisnikBaza.zatvoriVezu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "kreirajoglas";
    }

    @PostMapping("/oglas/postaviOglas")
    public String postaviOglas(@ModelAttribute("oglas") Oglas oglas) {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        try {
            OglasBaza oglasBaza = new OglasBaza();
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            Korisnik korisnik = korisnikBaza.korisnikPoImenu(logovanKorisnik.getName());
            oglas.setPoslodavac(korisnik);
            oglasBaza.postaviOglas(oglas, korisnik);

            oglasBaza.zatvoriVezu();
            korisnikBaza.zatvoriVezu();
            return "redirect:/prikazoglasa";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/oglas/postaviOglas";
    }

    @PostMapping("/obrisiOglas/{idOglasa}")
    public String postaviOglas(@PathVariable("idOglasa") long idOglasa) {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        try {
            OglasBaza oglasBaza = new OglasBaza();
            oglasBaza.iskljuciOglas(idOglasa);
            oglasBaza.zatvoriVezu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/prikazoglasa";
    }

}


















