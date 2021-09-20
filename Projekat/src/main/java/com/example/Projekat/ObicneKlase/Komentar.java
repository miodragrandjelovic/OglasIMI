package com.example.Projekat.ObicneKlase;

import java.sql.Date;

public class Komentar {
    private long id;
    private Oglas oglas;
    private Korisnik korisnik;
    private String tekst;
    private Date datumPostavljanja;

    public Komentar(){}

    public Komentar(long id, Oglas oglas, Korisnik korisnik, String tekst, Date datumPostavljanja) {
        this.id = id; this.oglas = oglas; this.korisnik = korisnik; this.tekst = tekst; this.datumPostavljanja = datumPostavljanja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }
}
