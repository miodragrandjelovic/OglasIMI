package com.example.Projekat.ObicneKlase;

public class Lajk {
    private long id;
    private Korisnik korisnik;
    private Oglas oglas;

    public Lajk(){}

    public Lajk(long id, Korisnik korisnik, Oglas oglas){
        this.id = id;
        this.korisnik = korisnik;
        this.oglas = oglas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisni(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }
}
