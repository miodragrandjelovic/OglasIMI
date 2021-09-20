package com.example.Projekat.Baza;

import com.example.Projekat.ObicneKlase.*;
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


    public Oglas jedanOglas(long id) throws SQLException, UnsupportedEncodingException {
        sql = "call DajJedanOglas(" + id + ");";
        resultset = statement.executeQuery(sql);
        if (resultset.first()) {
            if (resultset.getBytes("k.slika") != null) {
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
                                resultset.getString("k.pol"),
                                "data:image/;base64," + new String(resultset.getBytes("k.slika"), "UTF-8")
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
            else {
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
                                resultset.getString("k.pol"),
                                null
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
        }
        return null;
    }

    public ArrayList<Prijava> svePrijaveZaOglas(long id) throws SQLException, UnsupportedEncodingException {
        ArrayList<Prijava> prijave = new ArrayList<Prijava>();
        sql = "call SvePrijaveZaOglas(" + id + ");";
        resultset = statement.executeQuery(sql);
        while(resultset.next()){
            if (resultset.getBytes("korisnik.slika") != null) {
                Prijava prijava = new Prijava(
                        resultset.getLong("prijava.id"),
                        new Korisnik(
                                resultset.getLong("korisnik.id"),
                                resultset.getString("korisnik.korisnickoIme"),
                                resultset.getString("korisnik.lozinka"),
                                resultset.getString("korisnik.eMail"),
                                resultset.getBoolean("korisnik.aktivan"),
                                resultset.getString("korisnik.ime"),
                                resultset.getString("korisnik.prezime"),
                                resultset.getDate("korisnik.datumRodjenja"),
                                resultset.getString("korisnik.drzava"),
                                resultset.getString("korisnik.grad"),
                                resultset.getString("korisnik.pol"),
                                "data:image/;base64," + new String(resultset.getBytes("korisnik.slika"), "UTF-8")
                        ),
                        null,
                        resultset.getDate("prijava.datumPrijave")
                );
                prijave.add(prijava);
            }
            else {
                    Prijava prijava = new Prijava(
                    resultset.getLong("prijava.id"),
                    new Korisnik(
                            resultset.getLong("korisnik.id"),
                            resultset.getString("korisnik.korisnickoIme"),
                            resultset.getString("korisnik.lozinka"),
                            resultset.getString("korisnik.eMail"),
                            resultset.getBoolean("korisnik.aktivan"),
                            resultset.getString("korisnik.ime"),
                            resultset.getString("korisnik.prezime"),
                            resultset.getDate("korisnik.datumRodjenja"),
                            resultset.getString("korisnik.drzava"),
                            resultset.getString("korisnik.grad"),
                            resultset.getString("korisnik.pol"),
                            null
                    ),
                    null,
                    resultset.getDate("prijava.datumPrijave")
                );
                    prijave.add(prijava);
            }
        }
        return prijave;
    }

    public boolean postaviOglas(Oglas oglas, Korisnik korisnik) throws SQLException {
        sql = "call UnesiOglas(" + oglas.getPoslodavac().getId() + ", '" + oglas.getNaslov() + "', '" + oglas.getTekst() + "', '"
                                    + oglas.getMesto() + "', " + oglas.getPlata() + ", '" + oglas.getKategorija() + "', '" + oglas.getPodkategorija() + "', " + oglas.isRadOdKuce() + ");";
        int izvresnoOglas = statement.executeUpdate(sql);
        if(izvresnoOglas > 0)
            return true;
        return false;
    }

    public boolean iskljuciOglas(long id) throws SQLException {
        sql = "call IskljuciOglas(" + id + ");";
        int izvresnoOglas = statement.executeUpdate(sql);
        if(izvresnoOglas > 0)
            return true;
        return false;
    }

    public boolean unesiPrijavu(Korisnik korisnik, Oglas oglas) throws SQLException {
        sql = "call UnesiPrijavu(" + korisnik.getId() + ", " + oglas.getId() + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        return false;
    }




}
