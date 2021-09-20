package com.example.Projekat.ObicneKlase;

import java.sql.Date;

public class Oglas {
    private long id;
    private Korisnik poslodavac;
    private String naslov;
    private String tekst;
    private String mesto;
    private Date datumPostavljanja;
    private Date datumZavrsetka;
    private double plata;
    private String kategorija;
    private String podkategorija;
    private boolean radOdKuce;
    private boolean aktiviran;
    private String slika;

    public Oglas(){}

    public Oglas(long id, Korisnik poslodavac, String naslov, String tekst, String mesto, Date datumPostavljanja, Date datumZavrsetka, double plata, String kategorija, String podkategorija, boolean radOdKuce, boolean aktiviran, String slika) {
        this.id = id; this.poslodavac = poslodavac; this.naslov = naslov; this.tekst = tekst; this.mesto = mesto; this.datumPostavljanja = datumPostavljanja; this.datumZavrsetka = datumZavrsetka; this.plata = plata; this.kategorija = kategorija; this.podkategorija = podkategorija; this.radOdKuce = radOdKuce; this.aktiviran = aktiviran; this.slika = slika;
    }

    public long getIdOglasa() {
        return id;
    }

    public void setIdOglasa(long id) {
        this.id = id;
    }

    public Korisnik getPoslodavac() {
        return poslodavac;
    }

    public void setPoslodavac(Korisnik poslodavac) {
        this.poslodavac = poslodavac;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getPodkategorija() {
        return podkategorija;
    }

    public void setPodkategorija(String podkategorija) {
        this.podkategorija = podkategorija;
    }

    public boolean isRadOdKuce() {
        return radOdKuce;
    }

    public void setRadOdKuce(boolean radOdKuce) {
        this.radOdKuce = radOdKuce;
    }

    public boolean isAktiviran() {
        return aktiviran;
    }

    public void setAktiviran(boolean aktiviran) {
        this.aktiviran = aktiviran;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
