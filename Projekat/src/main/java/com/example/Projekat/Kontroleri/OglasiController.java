package com.example.Projekat.Kontroleri;


import com.example.Projekat.Baza.KorisnikBaza;
import com.example.Projekat.Baza.OglasBaza;
import com.example.Projekat.ObicneKlase.Korisnik;
import com.example.Projekat.ObicneKlase.Oglas;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class OglasiController {

    @GetMapping("/prikazoglasa")
    public String PrikaziOglase(@RequestParam(value="minPlata", defaultValue = "") String minPlata,
                                @RequestParam(value="maxPlata", defaultValue = "") String maxPlata,
                                @RequestParam(value="mesto", defaultValue = "") String mesto,
                                @RequestParam(value="kategorija", defaultValue = "") String kategorija,
                                @RequestParam(value="radOdKuce", required = false) String radOdKuce,
                                Model model)
            throws SQLException, UnsupportedEncodingException {

        Authentication logovanKorisnik = SecurityContextHolder.getContext().getAuthentication();
        if (!(logovanKorisnik instanceof AnonymousAuthenticationToken)){
            Korisnik trenutniKorisnik = new KorisnikBaza().korisnikPoImenu(logovanKorisnik.getName());
            model.addAttribute("logovanKorisnik", trenutniKorisnik);
        }


        OglasBaza oglasDatabase = new OglasBaza();

        ArrayList<Oglas> listaOglasa;
        listaOglasa = oglasDatabase.sviOglasi();

        if(!minPlata.equals(""))
            for(int i = 0; i < listaOglasa.size(); i++)
                if(listaOglasa.get(i).getPlata() < Double.parseDouble(minPlata))
                    listaOglasa.remove(i);

        if(!maxPlata.equals(""))
            for(int i = 0; i < listaOglasa.size(); i++)
                if(listaOglasa.get(i).getPlata() > Double.parseDouble(maxPlata))
                    listaOglasa.remove(i);

        if(!mesto.equals(""))
            for(int i = 0; i < listaOglasa.size(); i++)
                if(!listaOglasa.get(i).getMesto().equalsIgnoreCase(mesto))
                    listaOglasa.remove(i);

        if(!kategorija.equals(""))
            for(int i = 0; i < listaOglasa.size(); i++)
                if(!listaOglasa.get(i).getKategorija().equalsIgnoreCase(kategorija))
                    listaOglasa.remove(i);

        if(radOdKuce != null)
            for(int i = 0; i < listaOglasa.size(); i++)
                if(!listaOglasa.get(i).isRadOdKuce())
                    listaOglasa.remove(i);

        model.addAttribute("oglasi", listaOglasa);

        oglasDatabase.zatvoriVezu();
        return "prikazoglasa";
    }



}






