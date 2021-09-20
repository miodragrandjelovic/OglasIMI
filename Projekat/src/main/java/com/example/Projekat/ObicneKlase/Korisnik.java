package com.example.Projekat.ObicneKlase;

import java.sql.Date;

public class Korisnik {
    private long id;
    private String korisnickoIme;
    private String lozinka;
    private String eMail;
    private boolean aktivan;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String drzava;
    private String grad;
    private String pol;
    private String slika;

    public Korisnik(){}

    public Korisnik(long id, String korisnickoIme, String lozinka, String eMail, boolean aktivan, String ime, String prezime, Date datumRodjenja, String drzava, String grad, String pol) {
        this.id = id; this.korisnickoIme = korisnickoIme; this.lozinka = lozinka; this.eMail = eMail; this.aktivan = aktivan; this.ime = ime; this.prezime = prezime; this.datumRodjenja = datumRodjenja; this.drzava = drzava; this.grad = grad; this.pol = pol;
    }

    public Korisnik(long id, String korisnickoIme, String lozinka, String eMail, boolean aktivan, String ime, String prezime, Date datumRodjenja, String drzava, String grad, String pol, String slika) {
        this.id = id; this.korisnickoIme = korisnickoIme; this.lozinka = lozinka; this.eMail = eMail; this.aktivan = aktivan; this.ime = ime; this.prezime = prezime; this.datumRodjenja = datumRodjenja; this.drzava = drzava; this.grad = grad; this.pol = pol; this.slika = slika;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
