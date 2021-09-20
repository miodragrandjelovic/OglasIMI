package com.example.Projekat.Baza;

import com.example.Projekat.ObicneKlase.Korisnik;
import com.example.Projekat.ObicneKlase.Oglas;
import com.example.Projekat.ObicneKlase.Prijava;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrijavaNaOglasBaza extends baza {

    public PrijavaNaOglasBaza() throws SQLException { }

    public ArrayList<Prijava> getPrijaveZaOglas(long idOglasa) throws SQLException {
        ArrayList<Prijava> lista = new ArrayList<Prijava>();
        sql = "call getPrijaveZaOglas(" + idOglasa + ");";
        resultset = statement.executeQuery(sql);
        while (resultset.next()) {
            lista.add(
                    new Prijava(
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
                                    resultset.getString("korisnik.pol")
                            ),
                            new Oglas(
                                    resultset.getLong("oglas.id"),
                                    null,
                                    resultset.getString("oglas.naslov"),
                                    resultset.getString("oglas.tekst"),
                                    resultset.getString("oglaso.mesto"),
                                    resultset.getDate("oglas.datumPostavljanja"),
                                    resultset.getDate("oglas.datumZavrsetka"),
                                    resultset.getDouble("oglas.plata"),
                                    resultset.getString("oglas.kategorija"),
                                    resultset.getString("oglas.podkategorija"),
                                    resultset.getBoolean("oglas.radOdKuce"),
                                    resultset.getBoolean("oglas.aktiviran"),
                                    null
                            ),
                            resultset.getDate("prijava.datumPrijave")
                    )
            );
        }
        return lista;
    }

}
