package com.example.Projekat.Baza;

import com.example.Projekat.ObicneKlase.Korisnik;
import com.example.Projekat.ObicneKlase.Lajk;
import com.example.Projekat.ObicneKlase.Oglas;
import com.example.Projekat.ObicneKlase.Pregled;
import com.sun.mail.imap.Utility;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OglasBaza extends baza {

    public OglasBaza() throws SQLException{}

    public ArrayList<Oglas> sviOglasi() throws SQLException {
        ArrayList<Oglas> oglasList = new ArrayList<Oglas>();

        sql = "call SviOglasi();";
        resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            oglasList.add(new Oglas(
                    resultset.getLong("o.id"),
                    new Korisnik(
                            resultset.getLong("k.id"),
                            resultset.getString("k.korisnickoIme"),
                            resultset.getString("k.lozinka"),
                            resultset.getString("k.eMail"),
                            resultset.getBoolean("k.aktivan"),
                            resultset.getString("k.ime"),
                            resultset.getString("k.prezime"),
                            resultset.getDate("k.datumRodjenja"),
                            resultset.getString("k.drzava"),
                            resultset.getString("k.grad"),
                            resultset.getString("k.pol")
                    ),
                    resultset.getString("o.naslov"),
                    resultset.getString("o.tekst"),
                    resultset.getString("o.mesto"),
                    resultset.getDate("o.datumPostavljanja"),
                    resultset.getDate("o.datumZavrsetka"),
                    resultset.getDouble("o.plata"),
                    resultset.getString("o.kategorija"),
                    resultset.getString("o.podkategorija"),
                    resultset.getBoolean("o.radOdKuce"),
                    resultset.getBoolean("o.aktiviran"),
                    null
                )
            );
        }

        return oglasList;
    }

    public boolean obrisiOglas(long id) throws SQLException {
        sql = "call obrisiOglas(" + id + ");";
        int izvresno = statement.executeUpdate(sql);
        if (izvresno > 0)
            return true;
        else return false;
    }


    public Oglas jedanOglas(long id) throws SQLException{
        sql = "call DajOglas(" + id + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first()) {
            return new Oglas(
                    resultset.getLong("o.id"),
                    new Korisnik(
                            resultset.getLong("k.id"),
                            resultset.getString("k.korisnickoIme"),
                            resultset.getString("k.lozinka"),
                            resultset.getString("k.eMail"),
                            resultset.getBoolean("k.aktivan"),
                            resultset.getString("k.ime"),
                            resultset.getString("k.prezime"),
                            resultset.getDate("k.datumRodjenja"),
                            resultset.getString("k.drzava"),
                            resultset.getString("k.grad"),
                            resultset.getString("k.pol")
                    ),
                    resultset.getString("o.naslov"),
                    resultset.getString("o.tekst"),
                    resultset.getString("o.mesto"),
                    resultset.getDate("o.datumPostavljanja"),
                    resultset.getDate("o.datumZavrsetka"),
                    resultset.getDouble("o.plata"),
                    resultset.getString("o.kategorija"),
                    resultset.getString("o.podkategorija"),
                    resultset.getBoolean("o.radOdKuce"),
                    resultset.getBoolean("o.aktiviran"),
                    null
            );
        }

        return null;
    }

    public boolean postaviOglas(Oglas oglas, Korisnik korisnik) throws SQLException {
        sql = "call postaviOglas(" + oglas.getPoslodavac().getId() + ", '" + oglas.getNaslov() + "', '" + oglas.getTekst() + "', '"
                                    + oglas.getMesto() + "', '" + oglas.getDatumPostavljanja() + "', '" + oglas.getDatumZavrsetka()
                                    + "', " + oglas.getPlata() + ", " + oglas.getKategorija() + ", " + oglas.getPodkategorija() + ", " + oglas.isRadOdKuce() + ");";
        int izvresnoOglas = statement.executeUpdate(sql);
        if(izvresnoOglas > 0)
            return true;
        return false;
    }


    public boolean unesiLajk(Lajk lajk) throws SQLException {
        sql = "call UnesiLajk(" + lajk.getKorisnik().getId() + ", " + lajk.getOglas().getIdOglasa() + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public boolean dodajPregled(Pregled pregled) throws SQLException{
        sql = "call UnesiPregled(" + pregled.getKorisnik().getId() + ", " + pregled.getOglas().getIdOglasa() + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public boolean ukloniLajk(Lajk lajk) throws SQLException {
        sql = "call UkloniLajk(" + lajk.getKorisnik().getId() + lajk.getOglas().getIdOglasa() + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public boolean unesiPrijavu(Korisnik korisnik, Oglas oglas) throws SQLException {
        sql = "call UnesiPrijavu(" + korisnik.getId() + ", " + oglas.getIdOglasa() + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        return false;
    }

    public long dajBrojPregledaOglasa(long idOglasa) throws SQLException{
        sql = "call DajBrojPregledaOglasa(" + idOglasa + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            return resultset.getLong("count(id)");
        return 0;
    }

    public long dajBrojLajkovaOglasa(long idOglasa) throws SQLException{
        sql = "call DajBrojLajkovaOglasa(" + idOglasa + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            return resultset.getLong("count(id)");
        return 0;
    }

    public boolean pregledaoOglas(long idOglasa, long idOsobe) throws SQLException{
        sql = "call pregledaoOglas(" + idOglasa + ", " + idOsobe + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            return true;
        else
            return false;
    }

    public boolean lajkovoOglas(long idOglasa, long idOsobe) throws SQLException{
        sql = "call lajkovoOglas(" + idOglasa + ", " + idOsobe + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            return true;
        else
            return false;
    }




}
