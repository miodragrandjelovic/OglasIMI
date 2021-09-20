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
    public String prikaziOglas(@PathVariable("idOglasa") long idOglasa, Model model) throws SQLException {
        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        // LOGOVAN
        if (!(logovanKorisnik instanceof AnonymousAuthenticationToken)){

            try{
                KorisnikBaza korisnikDatabase = new KorisnikBaza();
                OglasBaza oglasDatabase = new OglasBaza();

                Korisnik trenutniKorisnik = korisnikDatabase.korisnikPoImenu(logovanKorisnik.getName());
                model.addAttribute("logovanKorisnik", trenutniKorisnik);

                Oglas trenutniOglas = oglasDatabase.jedanOglas(idOglasa);

                // PRIKAZ RADNIKA KOJI SU SE PRIJAVILI NA OGLAS
                if(trenutniKorisnik.getId() == trenutniOglas.getPoslodavac().getId()){
                    PrijavaNaOglasBaza prijaveDatabase = new PrijavaNaOglasBaza();
                    ArrayList<Prijava> prijave = prijaveDatabase.getPrijaveZaOglas(idOglasa);
                    model.addAttribute("prijave", prijave);
                    prijaveDatabase.zatvoriVezu();
                }

                // DODAJ PREGLED AKO VIDI OGLAS PRVI PUT
                if( !(oglasDatabase.pregledaoOglas(idOglasa, trenutniKorisnik.getId())) )
                    oglasDatabase.dodajPregled(new Pregled(0, trenutniKorisnik, trenutniOglas));


                // PRIKAZ DA LI JE LAJKOVO
                if(oglasDatabase.lajkovoOglas(idOglasa, trenutniKorisnik.getId()))
                    model.addAttribute("lajkovo", "/images/like_blue.png");
                else
                    model.addAttribute("lajkovo", "/images/like_white.png");

                model.addAttribute("brojPregleda", oglasDatabase.dajBrojPregledaOglasa(idOglasa));
                model.addAttribute("brojLajkova", oglasDatabase.dajBrojLajkovaOglasa(idOglasa));

                korisnikDatabase.zatvoriVezu();
                oglasDatabase.zatvoriVezu();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        // NIJE LOGOVAN
        else{
            OglasBaza oglasDatabase = new OglasBaza();
            model.addAttribute("brPregleda", oglasDatabase.dajBrojPregledaOglasa(idOglasa));
            model.addAttribute("brLajkova", oglasDatabase.dajBrojLajkovaOglasa(idOglasa));
            oglasDatabase.zatvoriVezu();
        }
        try{
            OglasBaza oglasDatabase = new OglasBaza();

            Oglas trenutniOglas = oglasDatabase.jedanOglas(idOglasa);
            Korisnik vlasnikOglasa = trenutniOglas.getPoslodavac();

            model.addAttribute("trenutniOglas", trenutniOglas);
            model.addAttribute("vlasnikOglasa", vlasnikOglasa);

            oglasDatabase.zatvoriVezu();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "oglas";
    }


    @PostMapping("/oglas/{idOglasa}/prijava")
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

    @PostMapping(value = "/oglas/{idOglasa}/like")
    public String dodajUkloniLajk(@PathVariable("idOglasa") long idOglasa) {
        try {
            Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();

            OglasBaza oglasBaza = new OglasBaza();
            KorisnikBaza korisnikBaza = new KorisnikBaza();

            Korisnik trenutniKorisnik = korisnikBaza.korisnikPoImenu(logovanKorisnik.getName());
            Oglas trenutniOglas = oglasBaza.jedanOglas(idOglasa);

            if(!(oglasBaza.lajkovoOglas(idOglasa, trenutniKorisnik.getId())))
                oglasBaza.unesiLajk(new Lajk(0, trenutniKorisnik, trenutniOglas));
            else
                oglasBaza.ukloniLajk(new Lajk(0, trenutniKorisnik, trenutniOglas));

            korisnikBaza.zatvoriVezu();
            oglasBaza.zatvoriVezu();
        }
        catch(SQLException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "redirect:/oglas/" + idOglasa;
    }

    @GetMapping("/oglas/postaviOglas")
    public String prikaziPostavljanjeOglsa(Model model) {
        try {
            Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            model.addAttribute("trenutniKorisnik", korisnikBaza.korisnikPoImenu(logovanKorisnik.getName()));
            korisnikBaza.zatvoriVezu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "postaviOglas";
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
            return "redirect:/oglasi";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/oglas/postaviOglas";
    }

}


















